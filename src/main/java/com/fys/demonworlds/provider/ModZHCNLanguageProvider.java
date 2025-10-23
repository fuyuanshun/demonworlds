package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModEffects;
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
        //恶魔果实
        add(ModItems.DEMON_FRUIT_SUN.get(), "太阳之恶魔果实");
        add(ModItems.DEMON_FRUIT_MOON.get(), "月亮之恶魔果实");
        //方块
        add(ModBlocks.SUN_BLOCK.get(), "太阳石");
        add(ModBlocks.SUN_ORE.get(), "太阳原矿");
        add(ModBlocks.END_BLOCK.get(), "末影石");
        add(ModBlocks.END_ORE.get(), "末影原矿");
        //物品描述
        add("item.demonworlds.demon_fruit_sun.desc", "\u00A76来自远古恶魔的诅咒-太阳之恶魔");
        add("item.demonworlds.demon_fruit_moon.desc", "\u00A76来自远古恶魔的诅咒-月亮之恶魔");
        add("item.demonworlds.demon_fruit_lightning.desc", "\u00A76来自远古恶魔的诅咒-闪电之恶魔");
        //自定义效果
        add(ModEffects.LIGHTNING_EFFECT.value(), "您已拥有闪电之力");
    }
}
