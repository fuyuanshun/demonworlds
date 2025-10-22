package com.fys.demonworlds.block;

import com.fys.demonworlds.DemonWorlds;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModConstants.MOD_ID);

    public static final DeferredBlock<Block> SUN_BLOCK = register("sun_block", BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F, 5.0F));

    public static DeferredBlock<Block> register(String name, BlockBehaviour.Properties block){
        DeferredBlock<Block> sunBlock = BLOCKS.registerSimpleBlock(name, block);
        ModItems.ITEMS.register(name, ()-> new BlockItem(sunBlock.get(), new Item.Properties()));
        return sunBlock;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
