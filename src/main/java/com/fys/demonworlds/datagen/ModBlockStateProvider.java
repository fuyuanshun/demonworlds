package com.fys.demonworlds.datagen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ModConstants.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.SUN_BLOCK.get(), cubeAll(ModBlocks.SUN_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.SUN_ORE.get(), cubeAll(ModBlocks.SUN_ORE.get()));
        simpleBlockWithItem(ModBlocks.END_BLOCK.get(), cubeAll(ModBlocks.END_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.END_ORE.get(), cubeAll(ModBlocks.END_ORE.get()));
        //自定义3D模型方块，不写数据生成
//        simpleBlockWithItem(ModBlocks.SIMPLE_BLOCK.get(), cubeAll(ModBlocks.SIMPLE_BLOCK.get()));


        logBlock((RotatedPillarBlock) ModBlocks.GOLDEN_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_GOLDEN_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.GOLDEN_WOOD.get(), blockTexture(ModBlocks.GOLDEN_LOG.get()), blockTexture(ModBlocks.GOLDEN_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_GOLDEN_WOOD.get(), blockTexture(ModBlocks.STRIPPED_GOLDEN_LOG.get()), blockTexture(ModBlocks.STRIPPED_GOLDEN_LOG.get()));

        simpleBlockItem(ModBlocks.GOLDEN_LOG.get(), new ModelFile.UncheckedModelFile("demonworlds:block/" + ModBlocks.GOLDEN_LOG.getId().getPath()));
        simpleBlockItem(ModBlocks.STRIPPED_GOLDEN_LOG.get(), new ModelFile.UncheckedModelFile("demonworlds:block/" + ModBlocks.STRIPPED_GOLDEN_LOG.getId().getPath()));
        simpleBlockItem(ModBlocks.GOLDEN_WOOD.get(), new ModelFile.UncheckedModelFile("demonworlds:block/" + ModBlocks.GOLDEN_WOOD.getId().getPath()));
        simpleBlockItem(ModBlocks.STRIPPED_GOLDEN_WOOD.get(), new ModelFile.UncheckedModelFile("demonworlds:block/" + ModBlocks.STRIPPED_GOLDEN_WOOD.getId().getPath()));

        simpleBlockWithItem(ModBlocks.GOLDEN_PLANK.get(), cubeAll(ModBlocks.GOLDEN_PLANK.get()));

        leavesBlock(ModBlocks.GOLDEN_LEAVES);

        saplingBlock(ModBlocks.GOLDEN_SAPLING);
    }

    public void leavesBlock(DeferredBlock<Block> block) {
        simpleBlockWithItem(block.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                                ResourceLocation.parse("minecraft:block/leaves"),
                                "all",
                                blockTexture(block.get()))
                        .renderType("cutout")
        );
    }

    public void saplingBlock(DeferredBlock<Block> block) {
        simpleBlock(block.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                                blockTexture(block.get()))
                        .renderType("cutout")
        );
    }
}
