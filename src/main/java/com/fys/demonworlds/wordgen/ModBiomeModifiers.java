package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * @author fys
 * @since 2025-10-31
 */
public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SUN_ORE_MODIFIER = createKey("sun_ore_modifier");
    public static final ResourceKey<BiomeModifier> END_ORE_MODIFIER = createKey("end_ore_modifier");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biome = context.lookup(Registries.BIOME);
        context.register(SUN_ORE_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biome.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.SUN_ORE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(END_ORE_MODIFIER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biome.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.END_ORE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    public static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

}
