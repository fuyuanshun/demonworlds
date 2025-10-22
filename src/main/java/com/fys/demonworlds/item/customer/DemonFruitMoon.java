package com.fys.demonworlds.item.customer;

import com.fys.demonworlds.item.foods.ModFoods;
import com.fys.demonworlds.util.HealthUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class DemonFruitMoon extends DemonFruit {
    public DemonFruitMoon(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.EPIC).food(ModFoods.DEMON_FRUIT_MOON));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.demonworlds.demon_fruit_moon.desc"));
    }
}
