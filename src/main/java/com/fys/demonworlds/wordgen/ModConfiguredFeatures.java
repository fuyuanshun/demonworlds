package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

/**
 * @author fys
 * @since 2025-11-16
 */
public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> SUN_ORE_KEY = createKey("sun_ore_feature");
    public static final ResourceKey<ConfiguredFeature<?,?>> END_ORE_KEY = createKey("end_ore_feature");

    public static final ResourceKey<ConfiguredFeature<?,?>> GOLDEN_TREE_KEY = createKey("golden_tree_key");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        TagMatchTest tagMatchTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        BlockMatchTest blockMatchTest = new BlockMatchTest(Blocks.END_STONE);

        register(
                context,
                SUN_ORE_KEY,
                Feature.ORE,
                new OreConfiguration(tagMatchTest, ModBlocks.SUN_ORE.get().defaultBlockState(), 9)
        );

        register(
                context,
                END_ORE_KEY,
                Feature.ORE,
                new OreConfiguration(List.of(OreConfiguration.target(blockMatchTest, ModBlocks.END_ORE.get().defaultBlockState())), 9)
        );

        register(
                context,
                GOLDEN_TREE_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.GOLDEN_LOG.get()),
                        new ForkingTrunkPlacer(5, 2, 2),
                        BlockStateProvider.simple(ModBlocks.GOLDEN_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
                ).build()
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
