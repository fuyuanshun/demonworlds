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
            ModMobEffects.LIGHTNING_EFFECT,
            ModMobEffects.CURSE
    );
    //存储吃过的恶魔果实列表
    public static final String NBT_DEMON_FRUIT = "DEMON_FRUIT";

    //存储死亡之前吃过的恶魔果实效果，重生后恢复
    public static final String NBT_SAVED_EFFECT_LIST = "SAVED_EFFECT_LIST";

}
