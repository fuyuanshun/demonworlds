package com.fys.demonworlds.block.custom;

import com.fys.demonworlds.block.ModBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author fys
 * @since 2025-11-25
 * 钻石树
 */
public class DiamondTree extends RotatedPillarBlock {

    protected static final Map<DeferredBlock<Block>, DeferredBlock<Block>> STRIPPABLES = new ImmutableMap.Builder<DeferredBlock<Block>, DeferredBlock<Block>>()
            .put(ModBlocks.DIAMOND_LOG, ModBlocks.STRIPPED_DIAMOND_LOG)
            .put(ModBlocks.DIAMOND_WOOD, ModBlocks.STRIPPED_DIAMOND_WOOD)
            .build();

    public DiamondTree(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if(itemAbility == ItemAbilities.AXE_STRIP && context.getItemInHand().is(ItemTags.AXES)){
            for(DeferredBlock<Block> deferredBlock : STRIPPABLES.keySet()){
                Block block = deferredBlock.get();
                if(state.is(block)){
                    Block strippedBlock = STRIPPABLES.get(deferredBlock).get();
                    return strippedBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS));
                }
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
