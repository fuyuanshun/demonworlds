package com.fys.demonworlds.wordgen.tree;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.wordgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

/**
 * @author fys
 * @date 2025/11/12
 * @description
 */
public class ModTreeGrowers {

    public static final TreeGrower GOLDEN_TREE = new TreeGrower(ModConstants.MOD_ID+":golden_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.GOLDEN_TREE_KEY), Optional.empty());

    public static final TreeGrower DIAMOND_TREE = new TreeGrower(ModConstants.MOD_ID+":diamond_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DIAMOND_TREE_KEY), Optional.empty());

    public static final TreeGrower LIGHTNING_TREE = new TreeGrower(ModConstants.MOD_ID+":lightning_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LIGHTNING_TREE_KEY), Optional.empty());
}
