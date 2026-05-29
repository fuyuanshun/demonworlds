package com.fys.demonworlds.packet;

import com.fys.demonworlds.attachment.ElytraSlotHelper;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * 鞘翅栏同步数据包。
 * 使用 FriendlyByteBuf 兼容 playToServer 注册接口，
 * 内部通过 cast 到 RegistryFriendlyByteBuf 以调用 ItemStack.STREAM_CODEC。
 */
public record ElytraSlotPacket(Action action, ItemStack elytra) implements CustomPacketPayload {

    public static final Type<ElytraSlotPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "elytra_slot"));

    public static final StreamCodec<FriendlyByteBuf, ElytraSlotPacket> STREAM_CODEC =
            StreamCodec.composite(
                    StreamCodec.of(
                            (buf, act) -> buf.writeEnum(act),
                            buf -> buf.readEnum(Action.class)
                    ),
                    ElytraSlotPacket::action,
                    // 适配可为空的 ItemStack codec；空栈表示让服务端自行从背包查找鞘翅。
                    StreamCodec.of(
                            (buf, stack) -> ItemStack.OPTIONAL_STREAM_CODEC.encode(
                                    buf instanceof RegistryFriendlyByteBuf rfb ? rfb : (RegistryFriendlyByteBuf) buf,
                                    stack),
                            buf -> ItemStack.OPTIONAL_STREAM_CODEC.decode(
                                    buf instanceof RegistryFriendlyByteBuf rfb ? rfb : (RegistryFriendlyByteBuf) buf)
                    ),
                    ElytraSlotPacket::elytra,
                    ElytraSlotPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ElytraSlotPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                switch (packet.action) {
                    case EQUIP -> handleEquip(player, packet.elytra);
                    case PICKUP -> handlePickup(player);
                    case UNEQUIP -> handleUnequip(player);
                }
            }
        });
    }

    private static void handleEquip(ServerPlayer player, ItemStack elytra) {
        if (ElytraSlotHelper.hasElytra(player)) {
            return;
        }

        ItemStack carried = player.containerMenu.getCarried();
        if (carried.is(Items.ELYTRA)) {
            ElytraSlotHelper.equip(player, carried.copyWithCount(1));
            if (!player.isCreative()) {
                carried.shrink(1);
                if (carried.isEmpty()) {
                    player.containerMenu.setCarried(ItemStack.EMPTY);
                }
            }
            return;
        }

        if (!elytra.isEmpty() && !elytra.is(Items.ELYTRA)) return;

        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (stack.is(Items.ELYTRA)) {
                ElytraSlotHelper.equip(player, stack.copyWithCount(1));
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                return;
            }
        }
    }

    private static void handleUnequip(ServerPlayer player) {
        if (ElytraSlotHelper.hasElytra(player)) {
            ItemStack removed = ElytraSlotHelper.unequip(player);
            if (!player.getInventory().add(removed)) {
                player.drop(removed, false);
            }
        }
    }

    private static void handlePickup(ServerPlayer player) {
        if (!ElytraSlotHelper.hasElytra(player)) return;

        ItemStack removed = ElytraSlotHelper.unequip(player);
        ItemStack carried = player.containerMenu.getCarried();
        if (carried.isEmpty()) {
            player.containerMenu.setCarried(removed);
            return;
        }

        if (!player.getInventory().add(removed)) {
            player.drop(removed, false);
        }
    }

    public enum Action {
        EQUIP,
        PICKUP,
        UNEQUIP
    }
}
