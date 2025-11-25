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

        //黄金树
        logBlock((RotatedPillarBlock) ModBlocks.GOLDEN_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_GOLDEN_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.GOLDEN_WOOD.get(), blockTexture(ModBlocks.GOLDEN_LOG.get()), blockTexture(ModBlocks.GOLDEN_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_GOLDEN_WOOD.get(), blockTexture(ModBlocks.STRIPPED_GOLDEN_LOG.get()), blockTexture(ModBlocks.STRIPPED_GOLDEN_LOG.get()));

        blockWithItem(ModBlocks.GOLDEN_LOG);
        blockWithItem(ModBlocks.STRIPPED_GOLDEN_LOG);
        blockWithItem(ModBlocks.GOLDEN_WOOD);
        blockWithItem(ModBlocks.STRIPPED_GOLDEN_WOOD);

        simpleBlockWithItem(ModBlocks.GOLDEN_PLANK.get(), cubeAll(ModBlocks.GOLDEN_PLANK.get()));

        leavesBlock(ModBlocks.GOLDEN_LEAVES);

        saplingBlock(ModBlocks.GOLDEN_SAPLING);
        //钻石树
        logBlock((RotatedPillarBlock) ModBlocks.DIAMOND_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DIAMOND_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.DIAMOND_WOOD.get(), blockTexture(ModBlocks.DIAMOND_LOG.get()), blockTexture(ModBlocks.DIAMOND_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DIAMOND_WOOD.get(), blockTexture(ModBlocks.STRIPPED_DIAMOND_LOG.get()), blockTexture(ModBlocks.STRIPPED_DIAMOND_LOG.get()));

        blockWithItem(ModBlocks.DIAMOND_LOG);
        blockWithItem(ModBlocks.STRIPPED_DIAMOND_LOG);
        blockWithItem(ModBlocks.DIAMOND_WOOD);
        blockWithItem(ModBlocks.STRIPPED_DIAMOND_WOOD);

        simpleBlockWithItem(ModBlocks.DIAMOND_PLANK.get(), cubeAll(ModBlocks.DIAMOND_PLANK.get()));

        leavesBlock(ModBlocks.DIAMOND_LEAVES);

        saplingBlock(ModBlocks.DIAMOND_SAPLING);
    }

    public void blockWithItem(DeferredBlock<Block> block){
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile("demonworlds:block/"+block.getId().getPath()));
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
