package com.fys.demonworlds.block.component;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModDataComponents extends DataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ModConstants.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TOGGLE = DATA_COMPONENT_TYPES.register("toggle",
            builder ->
                    DataComponentType.<Integer>builder()
                        .persistent(ExtraCodecs.intRange(0,1))
                            .build()
                );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }

}
