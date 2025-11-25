package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

/**
 *
 * 定义 生成规则
 * 参考原版类：FeatureUtils
 * @author fys
 * @date 2025/10/27
 * @description
 */
public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUN_ORE_KEY = registerKey("sun_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_ORE_KEY = registerKey("end_ore_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDEN_TREE_KEY = registerKey("golden_tree_key");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIAMOND_TREE_KEY = registerKey("diamond_tree_key");

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

        //黄金树
        register(context, GOLDEN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.GOLDEN_LOG.get()),
                //基础高度12，第一阶段随机增长1个高度
                new StraightTrunkPlacer(12, 1, 0),
                BlockStateProvider.simple(ModBlocks.GOLDEN_LEAVES.get()),
                //
                new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(1), 3),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 1, OptionalInt.empty())
        ).build());

        //钻石树
        register(context, DIAMOND_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.GOLDEN_LOG.get()),
                //基础高度12，第一阶段随机增长1个高度
                new StraightTrunkPlacer(12, 1, 0),
                BlockStateProvider.simple(ModBlocks.GOLDEN_LEAVES.get()),
                //
                new BlobFoliagePlacer(ConstantInt.of(4), ConstantInt.of(1), 3),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 1, OptionalInt.empty())
        ).build());
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
