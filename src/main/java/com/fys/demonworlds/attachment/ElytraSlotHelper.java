package com.fys.demonworlds.attachment;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.attachment.AttachmentType;

import java.util.function.Supplier;

/**
 * 玩家鞘翅栏工具类 —— 封装 Attachment 读写，
 * 提供获取 / 设置 / 检测 / 装备 / 卸除等便捷方法。
 *
 * @author fys
 * @since 2026-05-28
 */
public final class ElytraSlotHelper {

    private ElytraSlotHelper() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    private static AttachmentType<ItemStack> attachmentType() {
        return ModAttachments.ELYTRA_SLOT.get();
    }

    /** 获取玩家鞘翅栏中的物品 */
    public static ItemStack get(Player player) {
        return player.getData(attachmentType());
    }

    /** 设置玩家鞘翅栏中的物品（自动同步） */
    public static void set(Player player, ItemStack stack) {
        player.setData(attachmentType(), stack.copy());
    }

    /** 返回 true 表示鞘翅栏中存放的是鞘翅 */
    public static boolean hasElytra(Player player) {
        return get(player).is(Items.ELYTRA);
    }

    /** 尝试将物品放入鞘翅栏 */
    public static boolean equip(Player player, ItemStack stack) {
        if (stack.is(Items.ELYTRA)) {
            set(player, stack);
            return true;
        }
        return false;
    }

    /** 卸除鞘翅栏中的物品，返回被移除的 ItemStack */
    public static ItemStack unequip(Player player) {
        ItemStack removed = get(player).copy();
        set(player, ItemStack.EMPTY);
        return removed;
    }

    /** 返回鞘翅栏物品的耐久（用于飞行判定等） */
    public static int getRemainingDurability(Player player) {
        ItemStack stack = get(player);
        if (stack.is(Items.ELYTRA)) {
            return stack.getMaxDamage() - stack.getDamageValue();
        }
        return 0;
    }
}
