package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModRecipeProvider extends RecipeProvider {

    public static final List<ItemLike> SUN_BLOCK_ORE = List.of(ModBlocks.SUN_ORE);
    public static final List<ItemLike> END_BLOCK_ORE = List.of(ModBlocks.END_ORE);

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }


    @Override
    protected void buildRecipes() {
        //熔炉配方
        oreSmelting(recipeOutput, SUN_BLOCK_ORE, RecipeCategory.MISC, ModBlocks.SUN_BLOCK, 0.8F, 200, "sun_block");
        oreBlasting(recipeOutput, SUN_BLOCK_ORE, RecipeCategory.MISC, ModBlocks.SUN_BLOCK, 0.8F, 200, "sun_block");
        //
        oreSmelting(recipeOutput, END_BLOCK_ORE, RecipeCategory.MISC, ModBlocks.END_BLOCK, 0.8F, 200, "end_block");
        oreBlasting(recipeOutput, END_BLOCK_ORE, RecipeCategory.MISC, ModBlocks.END_BLOCK, 0.8F, 200, "end_block");

        //工作台配方
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.DEMON_FRUIT_SUN)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.SUN_BLOCK)
                .unlockedBy(getHasName(ModBlocks.SUN_BLOCK), has(ModBlocks.SUN_BLOCK))
                .save(recipeOutput);

    }

    protected static void oreSmelting(
            RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group
    ) {
        oreCooking(
                recipeOutput,
                RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new,
                ingredients,
                category,
                result,
                experience,
                cookingTime,
                group,
                "_from_smelting"
        );
    }

    protected static void oreBlasting(
            RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group
    ) {
        oreCooking(
                recipeOutput,
                RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new,
                ingredients,
                category,
                result,
                experience,
                cookingTime,
                group,
                "_from_blasting"
        );
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput recipeOutput,
            RecipeSerializer<T> serializer,
            AbstractCookingRecipe.Factory<T> recipeFactory,
            List<ItemLike> ingredients,
            RecipeCategory category,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String suffix
    ) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, ModConstants.MOD_ID+":"+getItemName(result) + suffix + "_" + getItemName(itemlike));
        }
    }
}
