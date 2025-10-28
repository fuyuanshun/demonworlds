package com.fys.demonworlds.enchantment;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.enchantment.custom.LightningEnchantmentEffect;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

/**
 * @author fys
 * @since 2025-10-28
 */
public class ModEnchantments {

    public static final ResourceKey<Enchantment> LIGHTNING_EFFECT = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "lightning_enchantment"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantmentHolderGetter = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> itemHolderGetter = context.lookup(Registries.ITEM);

        register(
                context,
                LIGHTNING_EFFECT,
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                itemHolderGetter.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                5,
                                2,
                                Enchantment.dynamicCost(5, 7),
                                Enchantment.dynamicCost(25, 7),
                                4,
                                EquipmentSlotGroup.MAINHAND
                        )
                )
                        .exclusiveWith(enchantmentHolderGetter.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new LightningEnchantmentEffect()
                        )
                );

    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
