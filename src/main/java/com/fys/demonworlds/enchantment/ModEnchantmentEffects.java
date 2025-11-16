package com.fys.demonworlds.enchantment;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.enchantment.custom.LightningEnchantment;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author fys
 * @since 2025-11-16
 */
public class ModEnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> EFFECTS
             = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ModConstants.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, ? extends MapCodec<?>> LIGHTNING_ENCHANTMENT
            = EFFECTS.register("lightning_enchantment", () -> LightningEnchantment.CODEC);

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }

}
