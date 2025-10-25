package com.fys.demonworlds.item;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> END = register("end", Util.make(new EnumMap<>(ArmorItem.Type.class), p_323385_ -> {
        p_323385_.put(ArmorItem.Type.BOOTS, 4);
        p_323385_.put(ArmorItem.Type.LEGGINGS, 7);
        p_323385_.put(ArmorItem.Type.CHESTPLATE, 9);
        p_323385_.put(ArmorItem.Type.HELMET, 4);
        p_323385_.put(ArmorItem.Type.BODY, 12);
    }), 17, SoundEvents.ARMOR_EQUIP_WOLF, 4.0F, 0.13F, () -> Ingredient.of(ModBlocks.END_BLOCK));

    /**
     * 注册
     * @param name
     * @param defense
     * @param enchantmentValue
     * @param equipSound
     * @param toughness
     * @param knockbackResistance
     * @param repairIngridient
     * @return
     */
    public static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngridient
    ) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name)));

        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, defense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name),
                new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance)
        );
    }

}
