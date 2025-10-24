package com.fys.demonworlds.tags;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author fys
 * @since 2025-10-24
 */
public class ModItemTags {

    public static final TagKey<Item> CLEAR_CURSE_MATERIAL = bind("clear_curse_material");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }
}
