package com.fys.demonworlds.effect;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author fys
 * @since 2025-10-23
 */
public class ModMobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ModConstants.MOD_ID);

    public static final Holder<MobEffect> LIGHTNING_EFFECT = EFFECTS.register("lightning_effect", ModEffect::new);

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
