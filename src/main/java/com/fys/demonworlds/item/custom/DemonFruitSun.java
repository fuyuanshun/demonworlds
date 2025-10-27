package com.fys.demonworlds.item.custom;

import com.fys.demonworlds.item.foods.ModFoods;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class DemonFruitSun extends DemonFruit {
    public DemonFruitSun(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.EPIC).food(ModFoods.DEMON_FRUIT_SUN));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.demonworlds.demon_fruit_sun.desc"));
    }

}
