package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModCreativeModeTabs;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModZHCNLanguageProvider extends LanguageProvider {

    public ModZHCNLanguageProvider(PackOutput output) {
        super(output, ModConstants.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        //物品栏
        add("itemGroup.demon_fruit_tab", "恶魔果实");
        add("itemGroup.demon_worlds_tab", "恶魔方块");

        add(ModItems.DEMON_FRUIT_SUN.get(), "\u00A76太阳之恶魔果实");
        add(ModBlocks.SUN_BLOCK.get(), "太阳石");
    }
}
