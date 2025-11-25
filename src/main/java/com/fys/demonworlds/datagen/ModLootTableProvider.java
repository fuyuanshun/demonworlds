package com.fys.demonworlds.datagen;

import com.fys.demonworlds.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
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
        //树叶掉落树苗
        add(ModBlocks.GOLDEN_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.GOLDEN_SAPLING.get(), new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F}));

        dropSelf(ModBlocks.DIAMOND_LOG.get());
        dropSelf(ModBlocks.STRIPPED_DIAMOND_LOG.get());
        dropSelf(ModBlocks.DIAMOND_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_DIAMOND_WOOD.get());
        dropSelf(ModBlocks.DIAMOND_PLANK.get());
        dropSelf(ModBlocks.DIAMOND_SAPLING.get());
        add(ModBlocks.DIAMOND_LEAVES.get(), block -> createLeavesDrops(block, ModBlocks.DIAMOND_SAPLING.get(), new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F}));
    }

    /**
     * 自定义掉落物：指定掉落物、掉落最小数量、掉落最大数量
     * @param block 指定方块，战利品就是该方块的掉落物
     * @param item 掉落物
     * @param min 掉落最小数量
     * @param max 掉落最大数量
     * @return
     */
    protected LootTable.Builder createCopperOreLikeDrops(Block block, Item item, float min, float max) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                block,
                (LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                        block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
