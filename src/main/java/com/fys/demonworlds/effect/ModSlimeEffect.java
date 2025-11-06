package com.fys.demonworlds.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

/**
 * @author fys
 * @since 2025-11-06
 */
public class ModSlimeEffect extends ModBeneficialEffect {

    public ModSlimeEffect() {
        super();
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(livingEntity.horizontalCollision){
            Vec3 deltaMovement = livingEntity.getDeltaMovement();
            livingEntity.setDeltaMovement(new Vec3(deltaMovement.x, 0.2, deltaMovement.z).scale(0.96));
            return true;
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
