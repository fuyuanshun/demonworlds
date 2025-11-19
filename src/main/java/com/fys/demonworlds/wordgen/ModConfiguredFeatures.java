package com.fys.demonworlds.wordgen;

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
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

/**
 * @author fys
 * @since 2025-11-16
 */
public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> SUN_ORE_KEY = createKey("sun_ore_key");
    public static final ResourceKey<ConfiguredFeature<?,?>> END_ORE_KEY = createKey("end_ore_key");

    public static final ResourceKey<ConfiguredFeature<?,?>> GOLDEN_TREE_KEY = createKey("golden_tree_key");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        TagMatchTest tagMatchTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        BlockMatchTest blockMatchTest = new BlockMatchTest(Blocks.END_STONE);

//        register(
//                context,
//        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
