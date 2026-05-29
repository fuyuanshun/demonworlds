package com.fys.demonworlds.event;

import com.fys.demonworlds.attachment.ElytraSlotHelper;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 鞘翅栏服务端事件。
 * - 右击鞘翅装备到独立栏位
 * - Pre/Post tick 临时交换让原版接管飞行
 * - 装备冲突哨兵
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModElytraSlotEvents {

    private static final Set<UUID> INTERNAL_CHEST_SWAPS = new HashSet<>();
    private static final Set<UUID> PENDING_ELYTRA_RESTORE = new HashSet<>();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        if (!canEquipFromHand(player, event.getItemStack())) return;

        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (!player.level().isClientSide()) {
            equipFromHand(player, event.getItemStack());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (!canEquipFromHand(player, event.getItemStack())) return;

        // RightClickBlock 取消后默认结果是 PASS，会继续触发 RightClickItem；SUCCESS 才能吃掉原版装备逻辑。
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);

        if (!player.level().isClientSide()) {
            equipFromHand(player, event.getItemStack());
        }
    }

    /** Pre-tick: 下落时将自定义栏位鞘翅临时移入胸甲栏 */
    @SubscribeEvent
    public static void onPlayerTickPre(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (player.onGround()) return;
        if (player.isInWater()) return;
        if (player.getAbilities().flying) return;
        if (!player.isFallFlying() && player.getDeltaMovement().y >= 0.0) return;

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.is(Items.ELYTRA)) return;
        if (!ElytraSlotHelper.hasElytra(player)) return;

        ItemStack elytra = ElytraSlotHelper.get(player);
        if (elytra.getDamageValue() >= elytra.getMaxDamage() - 1) return;

        // 交换：胸甲存自定义栏位，鞘翅放胸甲栏
        runInternalChestSwap(player, () -> {
            ElytraSlotHelper.set(player, chest.isEmpty() ? ItemStack.EMPTY : chest.copy());
            player.setItemSlot(EquipmentSlot.CHEST, elytra);
            PENDING_ELYTRA_RESTORE.add(player.getUUID());
        });
    }

    /** Post-tick: 将鞘翅移回自定义栏位，恢复胸甲 */
    @SubscribeEvent
    public static void onPlayerTickPost(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (!PENDING_ELYTRA_RESTORE.remove(player.getUUID())) return;

        ItemStack chestStack = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack customStack = ElytraSlotHelper.get(player);

        // 恢复：胸甲栏物品存回自定义栏位，自定义栏位物品放回胸甲栏
        runInternalChestSwap(player, () -> {
            ElytraSlotHelper.set(player, chestStack.isEmpty() ? ItemStack.EMPTY : chestStack);
            player.setItemSlot(EquipmentSlot.CHEST, customStack.isEmpty() ? ItemStack.EMPTY : customStack);
        });
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getSlot() != EquipmentSlot.CHEST) return;
        if (player.level().isClientSide()) return;
        if (INTERNAL_CHEST_SWAPS.contains(player.getUUID())) return;
        ItemStack newChest = event.getTo();
        if (newChest.is(Items.ELYTRA) && ElytraSlotHelper.hasElytra(player)) {
            ElytraSlotHelper.equip(player, newChest);
        }
    }

    private static boolean canEquipFromHand(Player player, ItemStack held) {
        return held.is(Items.ELYTRA)
                && !player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA)
                && !ElytraSlotHelper.hasElytra(player);
    }

    private static void equipFromHand(Player player, ItemStack held) {
        ItemStack elytra = held.copyWithCount(1);
        ElytraSlotHelper.equip(player, elytra);
        if (!player.isCreative()) {
            held.shrink(1);
        }
    }

    private static void runInternalChestSwap(Player player, Runnable swap) {
        UUID playerId = player.getUUID();
        INTERNAL_CHEST_SWAPS.add(playerId);
        try {
            swap.run();
        } finally {
            INTERNAL_CHEST_SWAPS.remove(playerId);
        }
    }
}
