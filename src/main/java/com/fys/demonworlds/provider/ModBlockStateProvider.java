package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModArmorMaterials;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModBlockStateProvider extends ModelProvider {


    public ModBlockStateProvider(PackOutput output, String modId) {
        super(output, ModConstants.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
       itemModels.generateFlatItem(ModItems.DEMON_FRUIT_SUN.get(), ModelTemplates.FLAT_ITEM);
       itemModels.generateFlatItem(ModItems.DEMON_FRUIT_MOON.get(), ModelTemplates.FLAT_ITEM);
       itemModels.generateFlatItem(ModItems.DEMON_FRUIT_LIGHTNING.get(), ModelTemplates.FLAT_ITEM);

       itemModels.generateFlatItem(ModItems.END_SWORD.get(), ModelTemplates.FLAT_ITEM);
       itemModels.generateFlatItem(ModItems.END_AXE.get(), ModelTemplates.FLAT_ITEM);
       itemModels.generateFlatItem(ModItems.END_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
       itemModels.generateFlatItem(ModItems.END_HOE.get(), ModelTemplates.FLAT_ITEM);
       itemModels.generateFlatItem(ModItems.END_SHOVEL.get(), ModelTemplates.FLAT_ITEM);

       itemModels.generateTrimmableItem(ModItems.END_HELMET.get(), ModArmorMaterials.END, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, ""), false);


        blockModels.createTrivialCube(ModBlocks.SUN_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SUN_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.END_ORE.get());


    }

}
