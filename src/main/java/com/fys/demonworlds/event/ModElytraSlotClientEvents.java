package com.fys.demonworlds.event;

import com.fys.demonworlds.attachment.ElytraSlotHelper;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.packet.ElytraFlightPacket;
import com.fys.demonworlds.packet.ElytraSlotPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * 鞘翅栏客户端事件处理（FORGE 总线部分）。
 * 处理需要在 FORGE 总线上注册的事件：
 * <ul>
 *   <li>背包界面中的独立鞘翅槽位（ScreenEvent.Init.Post）</li>
 *   <li>快捷键输入处理（InputEvent.Key）</li>
 * </ul>
 *
 * @author fys
 * @since 2026-05-28
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID, value = Dist.CLIENT)
public class ModElytraSlotClientEvents {

    private static boolean wasJumpDown;
    private static boolean wasOnGround = true;
    private static boolean requestedFlightSinceLeavingGround;

    // ---- 背包界面鞘翅槽位 ----

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof InventoryScreen screen) {
            event.addListener(new ElytraSlotWidget(screen));
        }
    }

    // ---- 快捷键 ----

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        handleCustomElytraFlight(mc, mc.player);

        while (ElytraSlotKeyBindings.ELYTRA_SLOT_KEY.consumeClick()) {
            handleElytraSlotKey(mc.player);
        }
    }

    private static void handleElytraSlotKey(Player player) {
        ItemStack currentElytra = ElytraSlotHelper.get(player);

        if (currentElytra.is(Items.ELYTRA)) {
            // 卸下鞘翅
            PacketDistributor.sendToServer(new ElytraSlotPacket(ElytraSlotPacket.Action.UNEQUIP, ItemStack.EMPTY));
        } else {
            // 装上鞘翅（服务端会搜索背包）
            PacketDistributor.sendToServer(new ElytraSlotPacket(ElytraSlotPacket.Action.EQUIP, ItemStack.EMPTY));
        }
    }

    private static void handleCustomElytraFlight(Minecraft mc, Player player) {
        boolean jumpDown = mc.options.keyJump.isDown();
        boolean jumpPressed = jumpDown && !wasJumpDown;
        boolean startedJumpFromGround = player.onGround() || wasOnGround;

        if (player.onGround() || player.isFallFlying()) {
            requestedFlightSinceLeavingGround = false;
        }

        if (jumpPressed
                && !startedJumpFromGround
                && !requestedFlightSinceLeavingGround
                && canRequestCustomElytraFlight(player)) {
            PacketDistributor.sendToServer(new ElytraFlightPacket());
            requestedFlightSinceLeavingGround = true;
        }
        wasJumpDown = jumpDown;
        wasOnGround = player.onGround();
    }

    private static boolean canRequestCustomElytraFlight(Player player) {
        return !player.onGround()
                && !player.isFallFlying()
                && !player.isInWater()
                && !player.isPassenger()
                && !player.hasEffect(MobEffects.LEVITATION)
                && ElytraSlotHelper.hasElytra(player)
                && ElytraSlotHelper.get(player).canElytraFly(player);
    }
}
