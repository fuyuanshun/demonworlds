package com.fys.demonworlds.enchantment;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.enchantment.custom.LightningEnchantment;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
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
 * @since 2025-11-16
 */
public class ModEnchantments {

    public static final ResourceKey<Enchantment> LIGHTNING_ENCHANTMENT = create("lightning_enchantment");

    public static void bootstrap(BootstrapContext<Enchantment> context) {

        HolderGetter<Item> itemHolderGetter = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantmentHolderGetter = context.lookup(Registries.ENCHANTMENT);

        register(
                context,
                LIGHTNING_ENCHANTMENT,
                Enchantment.enchantment(
                        Enchantment.definition(
                                itemHolderGetter.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                itemHolderGetter.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                10,
                                1,
                                Enchantment.dynamicCost(10, 10),
                                Enchantment.dynamicCost(25, 25),
                                10,
                                EquipmentSlotGroup.MAINHAND
                        )
                )
                //与攻击附魔冲突
                .exclusiveWith(enchantmentHolderGetter.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                //附魔的效果
                    .withEffect(
                            EnchantmentEffectComponents.POST_ATTACK,
                            EnchantmentTarget.ATTACKER,
                            EnchantmentTarget.VICTIM,
                            new LightningEnchantment()
                    )
        );
    }


    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }


    private static ResourceKey<Enchantment> create(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }
}
