package com.fys.demonworlds.block;

import com.fys.demonworlds.block.custom.GoldenTree;
import com.fys.demonworlds.block.custom.SimpleBlock;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import com.fys.demonworlds.wordgen.tree.ModTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
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

    public static final DeferredBlock<Block> GOLDEN_LOG = register("golden_log", GoldenTree::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredBlock<Block> GOLDEN_WOOD = register("golden_wood", GoldenTree::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
    public static final DeferredBlock<Block> STRIPPED_GOLDEN_LOG = register("stripped_golden_log", GoldenTree::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static final DeferredBlock<Block> STRIPPED_GOLDEN_WOOD = register("stripped_golden_wood", GoldenTree::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));

    public static final DeferredBlock<Block> GOLDEN_PLANK = register("golden_plank", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> GOLDEN_LEAVES = register("golden_leaves", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> GOLDEN_SAPLING = register("golden_sapling", ()->new SaplingBlock(ModTreeGrowers.GOLDEN_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties block){
        DeferredBlock<Block> deferredBlock = BLOCKS.registerBlock(name, func, block);
        ModItems.ITEMS.register(name, ()-> new BlockItem(deferredBlock.get(), new Item.Properties()));
        return deferredBlock;
    }

    public static DeferredBlock<Block> register(String name, Supplier<? extends Block> block) {
        DeferredBlock<Block> register = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, ()-> new BlockItem(register.get(), new Item.Properties()));
        return register;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
