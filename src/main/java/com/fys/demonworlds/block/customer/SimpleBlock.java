package com.fys.demonworlds.block.customer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
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

    private static final VoxelShape SHAPE_BY_AGE = Stream.of(
            Shapes.box(2, 2, 3, 9, 8, 9),
            Shapes.box(2, 1, 2, 9, 2, 9),
            Shapes.box(2, 8, 2, 9, 9, 9)
    ).reduce(Shapes::or).get();

    public SimpleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE;
    }
}
