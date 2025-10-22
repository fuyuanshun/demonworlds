package com.fys.demonworlds.block;

import com.fys.demonworlds.DemonWorlds;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 模组方块注册类
 * @author fys
 * @since 2025-10-22
 */
public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DemonWorlds.MOD_ID);

    public static final DeferredBlock<Block> SUN_BLOCK = BLOCKS.registerSimpleBlock("sun_block", BlockBehaviour.Properties.of()/*.mapColor(MapColor.STONE)*/);

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
