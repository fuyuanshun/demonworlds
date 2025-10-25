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
public class ModENUSLanguageProvider extends LanguageProvider {

    public ModENUSLanguageProvider(PackOutput output) {
        super(output, ModConstants.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        //物品栏
        add("itemGroup.demon_worlds_fruit", "demon worlds：fruit");
        add("itemGroup.demon_worlds_misc", "demon worlds：misc");
        add("itemGroup.demon_worlds_tool", "demon worlds: tool");
        add("itemGroup.demon_worlds_equipment", "demon worlds: equipment");
        //恶魔果实
        add(ModItems.DEMON_FRUIT_SUN.get(), "demon fruit sun");
        add(ModItems.DEMON_FRUIT_MOON.get(), "demon fruit moon");
        add(ModItems.DEMON_FRUIT_LIGHTNING.get(), "demon fruit lighting");
        //普通物品
        add(ModItems.MF.get(), "Rubik's Cube");
        add(ModItems.DARK_DUST.get(), "dark dust");
        //自定义武器
        add(ModItems.BAT.get(), "bat");
        add(ModItems.END_SWORD.get(), "end sword");
        //自定义工具
        add(ModItems.END_AXE.get(), "end axe");
        add(ModItems.END_PICKAXE.get(), "end pickaxe");
        add(ModItems.END_HOE.get(), "end hoe");
        add(ModItems.END_SHOVEL.get(), "end shovel");
        //自定义盔甲
        add(ModItems.END_HELMET.get(), "end helmet");
        add(ModItems.END_CHESTPLATE.get(), "end chestplate");
        add(ModItems.END_LEGGINS.get(), "end leggings");
        add(ModItems.END_BOOTS.get(), "end boots");

        //方块
        add(ModBlocks.SUN_BLOCK.get(), "sun block");
        add(ModBlocks.SUN_ORE.get(), "sun ore");
        add(ModBlocks.END_BLOCK.get(), "end block");
        add(ModBlocks.END_ORE.get(), "end ore");
        add(ModBlocks.SIMPLE_BLOCK.get(), "Magic blocks");
        //物品描述
        add("item.demonworlds.demon_fruit_sun.desc", "\u00A76Curse from the Ancient Demon - Sun Demon");
        add("item.demonworlds.demon_fruit_moon.desc", "\u00A76Curse from the Ancient Demon - Moon Demon");
        add("item.demonworlds.demon_fruit_lightning.desc", "\u00A76Curse from the Ancient Demon - Lightning Demon");
        add("item.demonworlds.mf.desc", "\u00A74A small Rubik's Cube, I wonder what its function is");
        //自定义效果
        add(ModMobEffects.LIGHTNING_EFFECT.value(), "lightning");
        add(ModMobEffects.CURSE.value(), "Demon's Curse");
    }
}
