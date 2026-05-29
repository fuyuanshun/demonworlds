package com.fys.demonworlds.packet;

import com.fys.demonworlds.attachment.ElytraSlotHelper;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 请求使用自定义鞘翅栏开始滑翔。
 */
public record ElytraFlightPacket() implements CustomPacketPayload {

    public static final Type<ElytraFlightPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "elytra_flight"));

    public static final StreamCodec<FriendlyByteBuf, ElytraFlightPacket> STREAM_CODEC =
            StreamCodec.unit(new ElytraFlightPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ElytraFlightPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player && canStartCustomElytraFlight(player)) {
                player.startFallFlying();
            }
        });
    }

    private static boolean canStartCustomElytraFlight(ServerPlayer player) {
        return !player.onGround()
                && !player.isFallFlying()
                && !player.isInWater()
                && !player.isPassenger()
                && !player.hasEffect(MobEffects.LEVITATION)
                && ElytraSlotHelper.hasElytra(player)
                && ElytraSlotHelper.get(player).canElytraFly(player);
    }
}
