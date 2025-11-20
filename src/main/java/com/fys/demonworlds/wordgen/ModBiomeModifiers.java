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
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * @author fys
 * @since 2025-11-16
 */
public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SUN_ORE_KEY = createKey("sun_ore_key");

    public static final ResourceKey<BiomeModifier> END_ORE_KEY = createKey("end_ore_key");

    public static final ResourceKey<BiomeModifier> GOLDEN_TREE_KEY = createKey("golden_tree_key");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){

        HolderGetter<PlacedFeature> placedFeatureHolderGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);

        context.register(
                SUN_ORE_KEY,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomeHolderGetter.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeatureHolderGetter.getOrThrow(ModPlacedFeatures.SUN_ORE_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(
                END_ORE_KEY,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomeHolderGetter.getOrThrow(BiomeTags.IS_END),
                        HolderSet.direct(placedFeatureHolderGetter.getOrThrow(ModPlacedFeatures.END_ORE_KEY)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );

        context.register(
                GOLDEN_TREE_KEY,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(biomeHolderGetter.getOrThrow(Biomes.PLAINS)),
                        HolderSet.direct(placedFeatureHolderGetter.getOrThrow(ModPlacedFeatures.GOLDEN_TREE_PLACED)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }

    public static ResourceKey<BiomeModifier> createKey(String name){
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }
}
