package com.fys.demonworlds.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * 无实际效果，根据实际逻辑继承调整
 *
 * @author fys
 * @since 2025-10-23
 */
public class ModBeneficialEffect extends MobEffect {

    public ModBeneficialEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);

//        this.addAttributeModifier(Attributes.ATTACK_SPEED,
//                ResourceLocation.withDefaultNamespace("effect.haste"),
//                0.2,
//                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
//                );
    }

    public ModBeneficialEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        return super.applyEffectTick(livingEntity, amplifier);
    }





}
