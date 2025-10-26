package com.fys.demonworlds.provider;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModMobEffects;
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
        add("itemGroup.demon_worlds_fruit", "恶魔世界：果实");
        add("itemGroup.demon_worlds_misc", "恶魔世界：杂项");
        add("itemGroup.demon_worlds_tool", "恶魔世界: 工具");
        add("itemGroup.demon_worlds_equipment", "恶魔世界: 装备");
        //恶魔果实
        add(ModItems.DEMON_FRUIT_SUN.get(), "太阳之恶魔果实");
        add(ModItems.DEMON_FRUIT_MOON.get(), "月亮之恶魔果实");
        add(ModItems.DEMON_FRUIT_LIGHTNING.get(), "闪电之恶魔果实");
        //普通物品
        add(ModItems.MF.get(), "魔方");
        add(ModItems.DARK_DUST.get(), "黑暗之尘");
        //自定义武器
        add(ModItems.BAT.get(), "球棒");
        add(ModItems.END_SWORD.get(), "末地之剑");
        add(ModItems.DIAMOND_BOW.get(), "钻石弓");
        //自定义工具
        add(ModItems.END_AXE.get(), "末地之斧");
        add(ModItems.END_PICKAXE.get(), "末地之稿");
        add(ModItems.END_HOE.get(), "末地之锄");
        add(ModItems.END_SHOVEL.get(), "末地之铲");
        //自定义盔甲
        add(ModItems.END_HELMET.get(), "末地头盔");
        add(ModItems.END_CHESTPLATE.get(), "末地胸甲");
        add(ModItems.END_LEGGINS.get(), "末地护腿");
        add(ModItems.END_BOOTS.get(), "末地靴");
        //方块
        add(ModBlocks.SUN_BLOCK.get(), "太阳石");
        add(ModBlocks.SUN_ORE.get(), "太阳原矿");
        add(ModBlocks.END_BLOCK.get(), "末影石");
        add(ModBlocks.END_ORE.get(), "末影原矿");
        add(ModBlocks.SIMPLE_BLOCK.get(), "神奇的方块");
        //物品描述
        add("item.demonworlds.demon_fruit_sun.desc", "\u00A76来自远古恶魔的诅咒-太阳之恶魔");
        add("item.demonworlds.demon_fruit_moon.desc", "\u00A76来自远古恶魔的诅咒-月亮之恶魔");
        add("item.demonworlds.demon_fruit_lightning.desc", "长按\u00A76SHIFT\u00A7f查看详细信息");
        add("item.demonworlds.demon_fruit_lightning.shiftdown", "\u00A76来自远古恶魔的诅咒-闪电之恶魔");
        add("item.demonworlds.mf.desc", "\u00A74一个小小的魔方，不知道有什么作用");
        //自定义效果
        add(ModMobEffects.LIGHTNING_EFFECT.value(), "闪电之力");
        add(ModMobEffects.CURSE.value(), "恶魔的诅咒");
    }
}
