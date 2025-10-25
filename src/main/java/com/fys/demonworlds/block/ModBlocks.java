package com.fys.demonworlds.block;

import com.fys.demonworlds.DemonWorlds;
import com.fys.demonworlds.block.customer.SimpleBlock;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 模组方块注册类
 * @author fys
 * @since 2025-10-22
 */
public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ModConstants.MOD_ID);

    public static final DeferredBlock<Block> SUN_BLOCK = register("sun_block", Block::new, BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.5F, 5.0F));
    public static final DeferredBlock<Block> SUN_ORE = register("sun_ore", Block::new, BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.5F, 5.0F));
    public static final DeferredBlock<Block> END_BLOCK = register("end_block", Block::new, BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.5F, 5.0F));
    public static final DeferredBlock<Block> END_ORE = register("end_ore", Block::new, BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(5.5F, 5.0F));
    public static final DeferredBlock<Block> SIMPLE_BLOCK = register("simple_block", SimpleBlock::new, BlockBehaviour.Properties.of().strength(1.5F, 5.0F));

    public static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties block){
        DeferredBlock<Block> sunBlock = BLOCKS.registerBlock(name, func, block);
        ModItems.ITEMS.register(name, ()-> new BlockItem(sunBlock.get(), new Item.Properties()));
        return sunBlock;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
