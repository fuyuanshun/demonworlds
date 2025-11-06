package com.fys.demonworlds.potion;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModMobEffects;
import com.fys.demonworlds.effect.ModSlimeEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 自定义药水 自动注册，只需要添加翻译就行
 * @author fys
 * @date 2025/10/26
 * @description
 */
public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, ModConstants.MOD_ID);

    //史莱姆药水
    public static final Holder<Potion> SLIME_POTION = POTIONS.register("slime_potion",
            ()->new Potion(
                    new MobEffectInstance(MobEffects.GLOWING, 2000, 1),
                    new MobEffectInstance(ModMobEffects.SLIME, 2000, 1)
            ));

    public static void registerPotions(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
