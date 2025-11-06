package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.block.custom.GoldenTree;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

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
        axisBlock((RotatedPillarBlock) ModBlocks.GOLDEN_WOOD.get(), blockTexture(ModBlocks.GOLDEN_LOG.get()), blockTexture(ModBlocks.GOLDEN_LOG.get()));

//        simpleBlockItem(ModBlocks.GOLDEN_LOG.get(), new ModelFile.UncheckedModelFile("demonworlds:block/" + ModBlocks.GOLDEN_LOG.getId().getPath()));
//        simpleBlockItem(ModBlocks.GOLDEN_WOOD.get(), new ModelFile.UncheckedModelFile("demonworlds:block/" + ModBlocks.GOLDEN_WOOD.getId().getPath()));

//        simpleBlockWithItem(ModBlocks.GOLDEN_LEAVES.get(), models().singleTexture());
    }
}
