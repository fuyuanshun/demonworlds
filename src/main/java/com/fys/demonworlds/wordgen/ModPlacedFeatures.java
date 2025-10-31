package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * @author fys
 * @since 2025-10-31
 */
public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SUN_ORE_PLACED = createKey("sun_ore_placed");
    public static final ResourceKey<PlacedFeature> END_ORE_PLACED = createKey("end_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, SUN_ORE_PLACED,
                configuredFeature.getOrThrow(ModConfiguredFeature.SUN_ORE_CONFIGURED),
                commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(80)))
        );
        register(context, END_ORE_PLACED,
                configuredFeature.getOrThrow(ModConfiguredFeature.END_ORE_CONFIGURED),
                commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(30)))
        );
    }

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

    public static void register(
            BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
            Holder<ConfiguredFeature<?,?>> feature, List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(feature, List.copyOf(modifiers)));
    }


    private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}
