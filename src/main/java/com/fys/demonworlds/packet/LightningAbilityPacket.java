package com.fys.demonworlds.packet;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 自定义数据包
 *
 */
public record LightningAbilityPacket() implements CustomPacketPayload {

    public static final Type<LightningAbilityPacket> TYPE =
        new Type<>(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "lightning_ability"));
    
    public static final StreamCodec<FriendlyByteBuf, LightningAbilityPacket> STREAM_CODEC =
        StreamCodec.unit(new LightningAbilityPacket());
    
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            // 确保在服务器线程执行
            if (context.player() instanceof ServerPlayer player) {
                // 调用生成闪电的方法
                spawnLightningBolt(player);
            }
        });
    }
    
    private static void spawnLightningBolt(ServerPlayer player) {
        ServerLevel serverLevel = (ServerLevel) player.level();
        Vec3 lookVec = player.getLookAngle();
        Vec3 startPos = player.getEyePosition();
        Vec3 endPos = startPos.add(
            lookVec.x * ModConstants.LIGHTNING_FRUIT_DISTANCE, 
            lookVec.y * ModConstants.LIGHTNING_FRUIT_DISTANCE, 
            lookVec.z * ModConstants.LIGHTNING_FRUIT_DISTANCE
        );
        
        // 使用精确的坐标，避免整数截断
        BlockPos lightningPos = BlockPos.containing(endPos.x, endPos.y, endPos.z);
        
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, serverLevel);
        lightningBolt.setPos(lightningPos.getX() + 0.5, lightningPos.getY(), lightningPos.getZ() + 0.5);
        lightningBolt.setVisualOnly(false);
        lightningBolt.setCause(player);
        
        serverLevel.addFreshEntity(lightningBolt);

        // 可选：添加冷却时间或其他限制
//        addCooldown(player);
    }
//
//    private static void addCooldown(ServerPlayer player) {
//        // 添加5秒冷却时间（100 ticks）
//        player.getCooldowns().addCooldown(Items.AIR, 100); // 使用空气作为标记物品
//    }


}