package com.fys.demonworlds.provider;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author fys
 * @since 2025-10-22
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModDataGenerators {

    //方块注册 blockstate/*.json models/block/*.json models/item/*.json
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        //方块生成 包含方块状态、方块物品
        generator.addProvider(true, new ModModelProvider(packOutput));
        //物品生成
        //语言数据生成
        generator.addProvider(true, new ModZHCNLanguageProvider(packOutput));
        generator.addProvider(true, new ModENUSLanguageProvider(packOutput));

    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Server event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        //战利品列表数据生成
        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        //标签数据生成
        //方块标签
        ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider);
        generator.addProvider(true, modBlockTagsProvider);
        //物品标签
        generator.addProvider(true, new ModItemTagsProvider(packOutput, lookupProvider));
        //配方数据生成
        generator.addProvider(true, new ModRecipeProvider(lookupProvider, packOutput));
        //附魔台列表数据生成
        generator.addProvider(true, new ModEnchantmentTagsProvider(packOutput, lookupProvider));
        //
        generator.addProvider(true, new ModDatapackProvider(packOutput, lookupProvider));
        //neoforge
        generator.addProvider(true, new ModNeoForgeProvider(packOutput, lookupProvider));
    }
}
