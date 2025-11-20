package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

/**
 * @author fys
 * @since 2025-11-16
 */
public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SUN_ORE_KEY = createKey("sun_ore_placed");

    public static final ResourceKey<PlacedFeature> END_ORE_KEY = createKey("end_ore_placed");

    public static final ResourceKey<PlacedFeature> GOLDEN_TREE_PLACED =  createKey("golden_tree_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        register(
                context,
                SUN_ORE_KEY,
                configuredFeatureHolderGetter.getOrThrow(ModConfiguredFeatures.SUN_ORE_KEY),
                ModOrePlacements.commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(80)))
        );

        register(
                context,
                END_ORE_KEY,
                configuredFeatureHolderGetter.getOrThrow(ModConfiguredFeatures.END_ORE_KEY),
                ModOrePlacements.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(40)))
        );

        register(
                context,
                GOLDEN_TREE_PLACED,
                configuredFeatureHolderGetter.getOrThrow(ModConfiguredFeatures.GOLDEN_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1f, 2), ModBlocks.GOLDEN_SAPLING.get())
        );
    }

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, key));
    }

    public static void register(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?, ?>> configuredFeature,
            List<PlacementModifier> placements
    ) {
        context.register(key, new PlacedFeature(configuredFeature, List.copyOf(placements)));
    }


}
