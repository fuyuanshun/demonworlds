package com.fys.demonworlds.provider;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ModConstants.MOD_ID, existingFileHelper);
    }

    //物品注册 models/item
    @Override
    protected void registerModels() {
        basicItem(ModItems.DEMON_FRUIT_SUN.get());
        basicItem(ModItems.DEMON_FRUIT_MOON.get());
        basicItem(ModItems.DEMON_FRUIT_LIGHTNING.get());
        basicItem(ModItems.MF.get());
    }
}
