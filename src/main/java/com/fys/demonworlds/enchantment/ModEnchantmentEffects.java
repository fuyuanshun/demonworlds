package com.fys.demonworlds.enchantment;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.enchantment.custom.LightningEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author fys
 * @since 2025-10-28
 */
public class ModEnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ModConstants.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<LightningEnchantmentEffect>> LIGHTNING_EFFECT = EFFECTS.register("lightning_enchantment", () -> LightningEnchantmentEffect.CODEC);

    public static void registerEnchantment(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }

}
