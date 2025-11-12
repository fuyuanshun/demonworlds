package com.fys.demonworlds.wordgen.tree;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.wordgen.ModConfiguredFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

/**
 * @author fys
 * @date 2025/11/12
 * @description
 */
public class ModTreeGrowers {

    public static final TreeGrower GOLDEN_TREE = new TreeGrower(ModConstants.MOD_ID+":golden_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GOLDEN_TREE_KEY), Optional.empty());;

}
