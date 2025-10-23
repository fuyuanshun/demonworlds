package com.fys.demonworlds.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * @author fys
 * @since 2025-10-23
 */
public class ModEffect extends MobEffect {

    protected ModEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);
    }

}
