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
        //自定义3D物品，不写数据生成
//        basicItem(ModItems.MF.get());
        //自定义武器
        handheldItem(ModItems.END_SWORD.get());

        //自定义工具
        handheldItem(ModItems.END_AXE.get());
        handheldItem(ModItems.END_PICKAXE.get());
        handheldItem(ModItems.END_HOE.get());
        handheldItem(ModItems.END_SHOVEL.get());
        //自定义盔甲
        basicItem(ModItems.END_HELMET.get());
        basicItem(ModItems.END_CHESTPLATE.get());
        basicItem(ModItems.END_LEGGINS.get());
        basicItem(ModItems.END_BOOTS.get());
    }
}
