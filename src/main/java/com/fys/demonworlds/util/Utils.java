package com.fys.demonworlds.util;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.packet.LightningAbilityPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * @author fys
 * @since 2025-10-23
 */
public class Utils {

    public static void subHealthByFruitCount(Player player, String id) {
        // 获取玩家的最大生命值属性实例
        Level level = player.level();
        CompoundTag tag = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
        CompoundTag demonFruit = tag.getCompound(ModConstants.NBT_DEMON_FRUIT);
        boolean eat = demonFruit.getBoolean(id);
        if(eat){
            return;
        }
        if(level.isClientSide){
            return;
        }
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if(maxHealth == null){
            return;
        }
        double currentHealth = maxHealth.getBaseValue();

        int size = demonFruit.size();
        double health = Math.max(currentHealth - ((size+1) * ModConstants.DEMON_FRUIT_SUB_HEALTH), 2);
        maxHealth.setBaseValue(health);
        demonFruit.putBoolean(id, true);
        tag.put(ModConstants.NBT_DEMON_FRUIT, demonFruit);
        player.getPersistentData().put(Player.PERSISTED_NBT_TAG, tag);
    }

    /**
     *
     * @param player
     */
    public static void drawLightning(Player player){
        //客户端发送数据包到服务器
        //PlayerInteractEvent.LeftClickEmpty只在客户端触发，需要发送数据包到服务器端
        if(player.level().isClientSide){
            PacketDistributor.sendToServer(new LightningAbilityPacket());
        }
    }

    /**
     * 获取已经使用的恶魔果实数量
     * @param player
     * @return
     */
    public static int demonFruitCount(Player player) {
        CompoundTag tag = player.getPersistentData().getCompound(Player.PERSISTED_NBT_TAG);
        CompoundTag demonFruit = tag.getCompound(ModConstants.NBT_DEMON_FRUIT);
        return demonFruit.size();
    }
}
