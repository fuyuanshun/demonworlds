package com.fys.demonworlds.wordgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * 三个方法来自于 “OrePlacements”类
 * @author fys
 * @date 2025/10/27
 * @description
 */
public class ModOrePlacements {

    public static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}
