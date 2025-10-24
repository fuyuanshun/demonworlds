package com.fys.demonworlds.tags;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * @author fys
 * @since 2025-10-24
 */
public class ModBlockTags {

    public static final TagKey<Block> NEEDS_END_TOOL = create("needs_end_tool");
    public static final TagKey<Block> NEEDS_SUN_TOOL = create("needs_sun_tool");
    public static final TagKey<Block> INCORRECT_FOR_END_TOOL = create("incorrect_for_end_tool");
    public static final TagKey<Block> INCORRECT_FOR_SUN_TOOL = create("incorrect_for_sun_tool");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }
}
