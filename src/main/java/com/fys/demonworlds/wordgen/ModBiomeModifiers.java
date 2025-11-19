package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * @author fys
 * @since 2025-11-16
 */
public class ModBiomeModifiers {

    public static void bootstrap(BootstrapContext<BiomeModifier> context){

        HolderGetter<PlacedFeature> placedFeatureHolderGetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);


    }

    public ResourceKey<BiomeModifier> createKey(String name){
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }
}
