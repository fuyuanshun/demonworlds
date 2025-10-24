package com.fys.demonworlds.block.customer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

/**
 * @author fys
 * @date 2025/10/23
 * @description
 */
public class SimpleBlock extends Block {

    public static final MapCodec<SimpleBlock> CODEC = simpleCodec(SimpleBlock::new);

    private static final VoxelShape SHAPE_BY_AGE = Stream.of(
            Block.box(1, 2, 1, 16, 14, 16),
            Block.box(0, 0, 1, 16, 2, 16),
            Block.box(0, 14, 1, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SimpleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE;
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }
}
