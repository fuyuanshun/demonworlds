package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.DEMON_FRUIT_SUN)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.SUN_BLOCK)
                .unlockedBy(getHasName(ModBlocks.SUN_BLOCK), has(ModBlocks.SUN_BLOCK))
                .save(recipeOutput);
    }
}
