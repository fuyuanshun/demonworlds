package com.fys.demonworlds.effect;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/**
 * @author fys
 * @since 2025-10-23
 */
public class ModEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ModConstants.MOD_ID);

    public static final Holder<MobEffect> LIGHTNING_EFFECT = EFFECTS.register("lightning_effect", LightningEffect::new);

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
