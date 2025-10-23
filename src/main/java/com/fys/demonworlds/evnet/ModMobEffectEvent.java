package com.fys.demonworlds.evnet;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModMobEffects;
import com.fys.demonworlds.util.Utils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/**
 * @author fys
 * @since 2025-10-23
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModMobEffectEvent {

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event){
        if(event.getEffectInstance() == null){
            return;
        }

        if(ModConstants.UN_CLEAR_EFFECT_LIST.contains(event.getEffectInstance().getEffect())){
            event.setCanceled(true);
        }
    }

    /**
     *
     * @param event
     */
    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event){
        if(event.getEffectInstance() == null){
            return;
        }

        if(ModConstants.UN_CLEAR_EFFECT_LIST.contains(event.getEffectInstance().getEffect())){
            event.setCanceled(true);
        }
    }

    /**
     * 左手攻击时，如果有闪电果实效果，则触发闪电
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
