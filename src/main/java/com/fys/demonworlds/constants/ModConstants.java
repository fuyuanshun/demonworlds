package com.fys.demonworlds.constants;

import com.fys.demonworlds.effect.ModMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import java.util.List;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModConstants {

    public static final String MOD_ID = "demonworlds";

    //闪电果实攻击距离
    public static final Integer LIGHTNING_FRUIT_DISTANCE = 10;

    public static final List<Holder<MobEffect>> UN_CLEAR_EFFECT_LIST = List.of(
            ModMobEffects.LIGHTNING_EFFECT
    );

}
