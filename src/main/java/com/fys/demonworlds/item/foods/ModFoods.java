package com.fys.demonworlds.item.foods;

import com.fys.demonworlds.effect.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class ModFoods {

    public static final FoodProperties DEMON_FRUIT_MOON = new FoodProperties.Builder()
            .alwaysEdible()
                .nutrition(4)
                .saturationModifier(1.2F)
                .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 5, true, false, false), 1.0F)
            .build();

    public static final FoodProperties DEMON_FRUIT_SUN = new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(4)
            .saturationModifier(1.2F)
            .effect(()->new MobEffectInstance(MobEffects.NIGHT_VISION, Integer.MAX_VALUE, 5, true, false, false), 1.0F)
            .build();

    public static final FoodProperties DEMON_FRUIT_LIGHTNING = new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(4)
            .saturationModifier(1.2F)
            .effect(()->new MobEffectInstance(ModMobEffects.LIGHTNING_EFFECT, Integer.MAX_VALUE, 5, true, false, false), 1.0F)
            .build();
}
