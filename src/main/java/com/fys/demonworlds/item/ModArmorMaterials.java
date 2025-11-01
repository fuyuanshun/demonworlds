package com.fys.demonworlds.item;

import com.google.common.collect.Maps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

/**
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModArmorMaterials {

    public static final ResourceKey<EquipmentAsset> END = EquipmentAssets.createId("end");

    public static final ArmorMaterial END_ARMOR_MATERIAL =
            new ArmorMaterial(37, makeDefense(4, 7, 9, 4, 12), 17, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.13F, ItemTags.REPAIRS_NETHERITE_ARMOR, END);


    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, leggings, ArmorType.CHESTPLATE, chestplate, ArmorType.HELMET, helmet, ArmorType.BODY, body));
    }
}
