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
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author fys
 * @since 2025-11-01
 */
public class GoldenTree extends RotatedPillarBlock {

    protected static final Map<Block, Block> STRIPPABLES = new ImmutableMap.Builder<Block, Block>()
            .put(ModBlocks.GOLDEN_LOG.get(), ModBlocks.STRIPPED_GOLDEN_LOG.get())
            .put(ModBlocks.GOLDEN_WOOD.get(), ModBlocks.STRIPPED_GOLDEN_WOOD.get())
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
            Block block = STRIPPABLES.get(state.getBlock());
            return block != null ? block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)) : null;
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
