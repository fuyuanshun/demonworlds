package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModCreativeModeTabs;
import com.fys.demonworlds.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
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

        add(ModItems.DEMON_FRUIT_SUN.get(), "\u00A76demon fruit sun");
        add(ModBlocks.SUN_BLOCK.get(), "sun block");
    }
}
