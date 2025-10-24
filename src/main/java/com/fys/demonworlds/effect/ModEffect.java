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

    // 用于属性修改的唯一标识符（避免与其他效果冲突）
    private static final UUID MOVEMENT_SPEED_BOOST_UUID = UUID.fromString("8a5f8432-5e4d-4f3a-9b8c-7d6e5c4b3a2f");

    protected ModEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFF00);

//        this.addAttributeModifier(Attributes.ATTACK_SPEED,
//                ResourceLocation.withDefaultNamespace("effect.haste"),
//                0.2,
//                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
//                );
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        return super.applyEffectTick(livingEntity, amplifier);
    }

}
