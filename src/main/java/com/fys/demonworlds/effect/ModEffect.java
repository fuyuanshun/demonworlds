package com.fys.demonworlds.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.UUID;

/**
 * 无实际效果，根据实际逻辑继承调整
 *
 * @author fys
 * @since 2025-10-23
 */
public class ModEffect extends MobEffect {

    public ModEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);

//        this.addAttributeModifier(Attributes.ATTACK_SPEED,
//                ResourceLocation.withDefaultNamespace("effect.haste"),
//                0.2,
//                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
//                );
    }

    public ModEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        return super.applyEffectTick(livingEntity, amplifier);
    }





}
