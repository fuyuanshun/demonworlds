package com.fys.demonworlds.util;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * @author fys
 * @since 2025-10-23
 */
public class Utils {

    /**
     *
     * @param player
     */
    public static void drawLightning(Player player){
        if(player.level() instanceof ServerLevel serverLevel){
            Vec3 lookVec = player.getLookAngle();
            Vec3 startPos = player.getEyePosition();
            Vec3 endPos = startPos.add(lookVec.x * ModConstants.LIGHTNING_FRUIT_DISTANCE, lookVec.y * ModConstants.LIGHTNING_FRUIT_DISTANCE, lookVec.z * ModConstants.LIGHTNING_FRUIT_DISTANCE);
            BlockPos lightningPos = new BlockPos((int)endPos.x, (int)endPos.y, (int)endPos.z);

            LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, serverLevel);

            lightningBolt.setPos(lightningPos.getX() + 0.5, lightningPos.getY(), lightningPos.getZ() + 0.5);
            lightningBolt.setVisualOnly(false);

            if(player instanceof ServerPlayer serverPlayer){
                lightningBolt.setCause(serverPlayer);
            }
            serverLevel.addFreshEntity(lightningBolt);
        }
    }

}
