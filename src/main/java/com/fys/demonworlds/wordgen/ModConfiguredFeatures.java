package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

/**
 *
 * 定义 生成规则
 *
 * @author fys
 * @date 2025/10/27
 * @description
 */
public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUN_ORE_KEY = registerKey("sun_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ORE_KEY = registerKey("end_ore_key");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest stoneReplace = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest endStoneReplace = new BlockMatchTest(Blocks.END_STONE);
        List<OreConfiguration.TargetBlockState> targetBlockStateList = List.of(
                OreConfiguration.target(stoneReplace, ModBlocks.SUN_ORE.get().defaultBlockState())
        );
        //
        register(context, SUN_ORE_KEY, Feature.ORE, new OreConfiguration(targetBlockStateList, 9));
        //末地
        register(context, END_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplace, ModBlocks.SUN_ORE.get().defaultBlockState(), 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature> void register(BootstrapContext<ConfiguredFeature<?,?>> context,
                                                                                     ResourceKey<ConfiguredFeature<?,?>> key,
                                                                                     F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
