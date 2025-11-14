package com.fys.demonworlds.datagen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.tags.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * 钻石挖末地石， 末地工具挖太阳石
 * 太阳工具等级最高
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ModConstants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SUN_BLOCK.get())
                .add(ModBlocks.SUN_ORE.get())
                .add(ModBlocks.END_BLOCK.get())
                .add(ModBlocks.END_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(ModBlocks.SUN_BLOCK.get())
                .add(ModBlocks.SUN_ORE.get())
        ;

        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .add(ModBlocks.SUN_BLOCK.get())
                .add(ModBlocks.SUN_ORE.get())
        ;

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.END_BLOCK.get())
                .add(ModBlocks.END_ORE.get())
        ;

        tag(ModBlockTags.NEEDS_END_TOOL)
                .add(ModBlocks.SUN_BLOCK.get())
                .add(ModBlocks.SUN_ORE.get())
        ;

        tag(ModBlockTags.NEEDS_SUN_TOOL)
//                .add(ModBlocks.SUN_BLOCK.get())
//                .add(ModBlocks.SUN_ORE.get())
        ;

        tag(ModBlockTags.INCORRECT_FOR_END_TOOL)
//                .addTag(ModBlockTags.NEEDS_SUN_TOOL)
//                .add(ModBlocks.SUN_BLOCK.get())
//                .add(ModBlocks.SUN_ORE.get())
        ;

        tag(ModBlockTags.INCORRECT_FOR_SUN_TOOL)
//                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
        ;

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.GOLDEN_LOG.get())
                .add(ModBlocks.STRIPPED_GOLDEN_LOG.get())
                .add(ModBlocks.GOLDEN_WOOD.get())
                .add(ModBlocks.STRIPPED_GOLDEN_WOOD.get())
                ;

        tag(BlockTags.PLANKS)
                .add(ModBlocks.GOLDEN_PLANK.get())
                ;
    }
}
