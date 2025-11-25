package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.entity.ModEntityType;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

/**
 * @author fys
 * @date 2025/10/27
 * @description
 */
public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_ORE_SUN = registerKey("add_ore_sun");
    public static final ResourceKey<BiomeModifier> ADD_ORE_END = registerKey("add_ore_end");
    public static final ResourceKey<BiomeModifier> ADD_GOLDEN_TREE = registerKey("add_golden_tree");
    public static final ResourceKey<BiomeModifier> ADD_DIAMOND_TREE = registerKey("add_diamond_tree");
    public static final ResourceKey<BiomeModifier> SPAWN_GECKO = registerKey("spawn_gecko");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        HolderGetter<PlacedFeature> placedFeature = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biome = context.lookup(Registries.BIOME);

        context.register(ADD_ORE_SUN, new BiomeModifiers.AddFeaturesBiomeModifier(
                biome.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ORE_SUN_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_ORE_END, new BiomeModifiers.AddFeaturesBiomeModifier(
                biome.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.ORE_END_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        context.register(ADD_GOLDEN_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biome.getOrThrow(Biomes.PLAINS)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.GOLDEN_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_DIAMOND_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biome.getOrThrow(Biomes.PLAINS)),
                HolderSet.direct(placedFeature.getOrThrow(ModPlacedFeatures.DIAMOND_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(SPAWN_GECKO, new BiomeModifiers.AddSpawnsBiomeModifier(
           HolderSet.direct(biome.getOrThrow(Biomes.PLAINS)),
                List.of(new MobSpawnSettings.SpawnerData(ModEntityType.GECKO.get(), 20, 1, 1))
        ));
    }

    public static ResourceKey<BiomeModifier> registerKey(String name){
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

}
