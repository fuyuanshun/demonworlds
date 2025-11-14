package com.fys.demonworlds.event;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModMobEffects;
import com.fys.demonworlds.item.ModItems;
import com.fys.demonworlds.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 事件监听类
 *
 * @author fys
 * @since 2025-10-23
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModMobEffectEventEvents {

    //用于存储玩家最后一个使用的物品，用来判断是否可以清楚特殊buff
    private static final Map<UUID, ItemStack> LAST_USE_ITEM_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Player player && event.getItem().is(ModItems.DARK_DUST)) {
            LAST_USE_ITEM_MAP.put(player.getUUID(), event.getItem());

            if(player.level() instanceof ServerLevel serverLevel) {
                serverLevel.getServer().execute(()->{
                    LAST_USE_ITEM_MAP.remove(player.getUUID());
                });
            }
        }
    }

    /**
     * 监听效果移除，例如牛奶
     * @param event
     */
    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event){
        if(event.getEffectInstance() == null){
            return;
        }
        //取消移除
        if(ModConstants.UN_CLEAR_EFFECT_LIST.contains(event.getEffectInstance().getEffect()) && !LAST_USE_ITEM_MAP.containsKey(event.getEntity().getUUID())){
            event.setCanceled(true);
        }
    }

    /**
     * 监听生物死亡事件
     * @param event
     */
    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event){
        if(event.getEntity() instanceof Player player){

            ListTag effectsList = new ListTag();
            player.getActiveEffects().forEach(effect -> {
                CompoundTag playerTag = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);

                if(ModConstants.UN_CLEAR_EFFECT_LIST.contains(effect.getEffect())){
                    ResourceLocation effectId = BuiltInRegistries.MOB_EFFECT.getKey(effect.getEffect().value());
                    int amplifier = effect.getAmplifier();
                    int duration = effect.getDuration();
                    boolean ambient = effect.isAmbient();
                    boolean visible = effect.isVisible();

                    CompoundTag effectTag = new CompoundTag();
                    effectTag.putString("id", effectId.toString()); // 效果ID
                    effectTag.putInt("amplifier", amplifier);       // 等级
                    effectTag.putInt("duration", duration);         // 剩余时间
                    effectTag.putBoolean("ambient", ambient);       // 是否 ambient
                    effectTag.putBoolean("visible", visible);       // 是否显示粒子

                    effectsList.add(effectTag);
                }
                // 存入持久化 NBT
                playerTag.put(ModConstants.NBT_SAVED_EFFECT_LIST, effectsList);
                player.getPersistentData().put(Player.PERSISTED_NBT_TAG, playerTag);
            });
        }
    }

    /**
     * 监听玩家复活事件
     * @param event
     */
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        Player player = event.getEntity();
        CompoundTag persistentData = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
        ListTag savedEffects = (ListTag) persistentData.get(ModConstants.NBT_SAVED_EFFECT_LIST);
        if(savedEffects!=null){
            for(Tag tag : savedEffects){
                if(tag instanceof CompoundTag ct){
                    MobEffectInstance load = MobEffectInstance.load(ct);
                    // 3. 重新添加效果
                    if(load!=null){
                        player.addEffect(load);
                    }
                }
            }
            // 4. 清除已恢复的 NBT 数据（避免重复恢复）
            persistentData.remove("SavedEffects");
            player.getPersistentData().put(Player.PERSISTED_NBT_TAG, persistentData);
        }
    }

    /**
     * 监听玩家左手攻击空挥事件
     * 如果有闪电果实效果，则触发闪电
     * @param event
     */
    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event){
        Player player = event.getEntity();

        //空手时触发
        if(!player.getMainHandItem().isEmpty()){
            return;
        }
        //是否已经吃过闪电果实
        if(player.hasEffect(ModMobEffects.LIGHTNING_EFFECT)){
            Utils.drawLightning(player);
        }
    }
}
