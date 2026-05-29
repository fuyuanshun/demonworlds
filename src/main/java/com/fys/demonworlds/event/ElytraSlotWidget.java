package com.fys.demonworlds.event;

import com.fys.demonworlds.attachment.ElytraSlotHelper;
import com.fys.demonworlds.packet.ElytraSlotPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * 背包界面中的独立鞘翅槽位控件。
 * 位于装备栏右侧区域。
 * 支持左右键装卸鞘翅。
 * 所有操作通过 {@link ElytraSlotPacket} 发送到服务端执行，
 * 不在客户端直接修改 Attachment 或物品栏。
 *
 * @author fys
 * @since 2026-05-28
 */
public class ElytraSlotWidget extends AbstractWidget {

    public ElytraSlotWidget(InventoryScreen parentScreen) {
        super(
                // 位置：装备栏右侧，合成结果槽左侧
                parentScreen.getGuiLeft() + 134,
                parentScreen.getGuiTop() + 8,
                18, 18,
                Component.translatable("slot.demonworlds.elytra")
        );
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        // 槽位背景
        RenderSystem.enableBlend();
        guiGraphics.fill(getX() - 1, getY() - 1, getX() + width + 1, getY() + height + 1, 0xFF_000000);
        guiGraphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF_8B8B8B);

        // 绘制槽内物品
        ItemStack elytra = ElytraSlotHelper.get(player);
        if (elytra.isEmpty()) {
            renderEmptyElytraOutline(guiGraphics);
        } else {
            guiGraphics.renderItem(elytra, getX() + 1, getY() + 1);
            // 耐久条（通过 renderItemDecorations 自动渲染）
            guiGraphics.renderItemDecorations(mc.font, elytra, getX() + 1, getY() + 1);
        }

        // 悬停高亮
        if (isHovered) {
            guiGraphics.fill(getX(), getY(), getX() + width, getY() + height, 0x80_FFFFFF);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!isMouseOver(mouseX, mouseY)) return false;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return false;

        ItemStack carried = mc.player.containerMenu.getCarried();

        if (button == 0 || button == 1) {
            boolean hasElytra = ElytraSlotHelper.hasElytra(player);
            if (hasElytra && button == 0) {
                PacketDistributor.sendToServer(new ElytraSlotPacket(ElytraSlotPacket.Action.PICKUP, ItemStack.EMPTY));
                return true;
            }
            if (hasElytra) {
                PacketDistributor.sendToServer(new ElytraSlotPacket(ElytraSlotPacket.Action.UNEQUIP, ItemStack.EMPTY));
                return true;
            }
            if (carried.is(Items.ELYTRA)) {
                PacketDistributor.sendToServer(new ElytraSlotPacket(ElytraSlotPacket.Action.EQUIP, carried.copy()));
                return true;
            }
        }
        return false;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    private void renderEmptyElytraOutline(GuiGraphics guiGraphics) {
        int x = getX();
        int y = getY();
        int shadow = 0x99_5A5A5A;
        int highlight = 0x55_DCDCDC;

        drawDashedPixels(guiGraphics, x, y, shadow, new int[][]{
                {4, 3}, {5, 3}, {12, 3}, {13, 3},
                {3, 5}, {6, 5}, {11, 5}, {14, 5},
                {2, 7}, {6, 7}, {11, 7}, {15, 7},
                {2, 9}, {5, 9}, {12, 9}, {15, 9},
                {3, 11}, {5, 11}, {12, 11}, {14, 11},
                {4, 13}, {13, 13},
                {8, 4}, {9, 4}, {7, 6}, {10, 6}, {7, 8}, {10, 8}, {8, 11}, {9, 11}
        }, 1, 1);
        drawDashedPixels(guiGraphics, x, y, highlight, new int[][]{
                {5, 4}, {12, 4}, {4, 6}, {13, 6}, {3, 8}, {14, 8}, {4, 10}, {13, 10},
                {8, 5}, {9, 5}, {8, 9}, {9, 9}
        }, 1, 1);
    }

    private void drawDashedPixels(GuiGraphics guiGraphics, int x, int y, int color, int[][] pixels, int width, int height) {
        for (int[] pixel : pixels) {
            guiGraphics.fill(x + pixel[0], y + pixel[1], x + pixel[0] + width, y + pixel[1] + height, color);
        }
    }
}
