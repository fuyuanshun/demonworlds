package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModENUSLanguageProvider extends LanguageProvider {

    public ModENUSLanguageProvider(PackOutput output) {
        super(output, ModConstants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //物品栏
        add("itemGroup.demon_fruit_tab", "demon fruit");
        add("itemGroup.demon_worlds_tab", "demon worlds");
        //恶魔果实
        add(ModItems.DEMON_FRUIT_SUN.get(), "demon fruit sun");
        add(ModItems.DEMON_FRUIT_MOON.get(), "demon fruit moon");
        //方块
        add(ModBlocks.SUN_BLOCK.get(), "sun block");
        //物品描述
        add("item.demonworlds.demon_fruit_sun.desc", "\u00A76Curse from the Ancient Demon - Sun Demon");
        add("item.demonworlds.demon_fruit_moon.desc", "\u00A76Curse from the Ancient Demon - Moon Demon");
    }
}
