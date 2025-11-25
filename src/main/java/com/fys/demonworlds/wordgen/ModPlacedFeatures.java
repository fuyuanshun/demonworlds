package com.fys.demonworlds.wordgen;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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
 * 参考 OrePlacements 类 PlacementUtils类
 *
 * 定义 “在何处生成”
 *
 * @author fys
 * @date 2025/10/27
 * @description
 */
public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ORE_SUN_PLACED = registerKey("ore_sun_placed");
    public static final ResourceKey<PlacedFeature> ORE_END_PLACED = registerKey("ore_end_placed");
    public static final ResourceKey<PlacedFeature> GOLDEN_TREE_PLACED = registerKey("golden_tree_placed");
    public static final ResourceKey<PlacedFeature> DIAMOND_TREE_PLACED = registerKey("diamond_tree_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);

        register(
                context,
                ORE_SUN_PLACED,
                configuredFeature.getOrThrow(ModConfiguredFeatures.SUN_ORE_KEY),
                ModOrePlacements.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))
        );

        register(
                context,
                ORE_END_PLACED,
                configuredFeature.getOrThrow(ModConfiguredFeatures.END_ORE_KEY),
                ModOrePlacements.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))
        );

        register(
                context,
                GOLDEN_TREE_PLACED,
                configuredFeature.getOrThrow(ModConfiguredFeatures.GOLDEN_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05f, 1),
                        ModBlocks.GOLDEN_SAPLING.get())
        );

        register(
                context,
                DIAMOND_TREE_PLACED,
                configuredFeature.getOrThrow(ModConfiguredFeatures.DIAMOND_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05f, 1),
                        ModBlocks.DIAMOND_SAPLING.get())
        );
    }

    /**
     * 参考 “PlacementUtils”类的 createKey 方法
     * @param name
     * @return
     */
    public static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, name));
    }

    /**
     * 参考 “PlacementUtils”类的 register 方法
     * @param context
     * @param key
     * @param configuration
     * @param placementModifierList
     */
    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                Holder<ConfiguredFeature<?,?>> configuration, List<PlacementModifier> placementModifierList){
        context.register(key, new PlacedFeature(configuration, List.copyOf(placementModifierList)));
    }
}
