package com.fys.demonworlds.datagen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModLootTableProvider extends BlockLootSubProvider {

    protected ModLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SUN_BLOCK.get());
        dropSelf(ModBlocks.SUN_ORE.get());
        dropSelf(ModBlocks.END_BLOCK.get());
        dropSelf(ModBlocks.END_ORE.get());
        dropSelf(ModBlocks.SIMPLE_BLOCK.get());

        dropSelf(ModBlocks.GOLDEN_LOG.get());
        dropSelf(ModBlocks.STRIPPED_GOLDEN_LOG.get());
        dropSelf(ModBlocks.GOLDEN_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_GOLDEN_WOOD.get());
        dropSelf(ModBlocks.GOLDEN_PLANK.get());
        dropSelf(ModBlocks.GOLDEN_SAPLING.get());
        add(ModBlocks.GOLDEN_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.GOLDEN_SAPLING.get(), new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F}));

        dropSelf(ModBlocks.DIAMOND_LOG.get());
        dropSelf(ModBlocks.STRIPPED_DIAMOND_LOG.get());
        dropSelf(ModBlocks.DIAMOND_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_DIAMOND_WOOD.get());
        dropSelf(ModBlocks.DIAMOND_PLANK.get());
        dropSelf(ModBlocks.DIAMOND_SAPLING.get());
        add(ModBlocks.DIAMOND_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.DIAMOND_SAPLING.get(), new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F}));

        dropSelf(ModBlocks.LIGHTNING_LOG.get());
        dropSelf(ModBlocks.STRIPPED_LIGHTNING_LOG.get());
        dropSelf(ModBlocks.LIGHTNING_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_LIGHTNING_WOOD.get());
        dropSelf(ModBlocks.LIGHTNING_PLANK.get());
        dropSelf(ModBlocks.LIGHTNING_SAPLING.get());
        //闪电树叶：树苗 + 33%概率掉落1~3个闪电果实
        add(ModBlocks.LIGHTNING_LEAVES.get(), this::createLightningLeaves);
    }

    private LootTable.Builder createLightningLeaves(Block leafBlock) {
        return createLeavesDrops(leafBlock, ModBlocks.LIGHTNING_SAPLING.get(), new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F})
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModItems.DEMON_FRUIT_LIGHTNING.get())
                                .when(LootItemRandomChanceCondition.randomChance(0.33F))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                        )
                );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
