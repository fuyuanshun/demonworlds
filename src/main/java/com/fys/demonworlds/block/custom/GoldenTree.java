package com.fys.demonworlds.block.custom;

import com.fys.demonworlds.block.ModBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author fys
 * @since 2025-11-01
 */
public class GoldenTree extends RotatedPillarBlock {

    protected static final Map<DeferredBlock<Block>, DeferredBlock<Block>> STRIPPABLES = new ImmutableMap.Builder<DeferredBlock<Block>, DeferredBlock<Block>>()
            .put(ModBlocks.GOLDEN_LOG, ModBlocks.STRIPPED_GOLDEN_LOG)
            .put(ModBlocks.GOLDEN_WOOD, ModBlocks.STRIPPED_GOLDEN_WOOD)
            .build();
    public GoldenTree(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        //斧头
        if(context.getItemInHand().getItem() instanceof AxeItem){
            for(DeferredBlock<Block> deferredBlock : STRIPPABLES.keySet()){
                Block block = deferredBlock.get();
                if(state.is(block)){
                    return block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                }
            }
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
