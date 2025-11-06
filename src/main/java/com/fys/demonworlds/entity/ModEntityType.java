package com.fys.demonworlds.entity;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.entity.custom.GeckoEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author fys
 * @since 2025-11-06
 */
public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(Registries.ENTITY_TYPE, ModConstants.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<GeckoEntity>> GECKO = ENTITY_TYPE.register("gecko",
            () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE).build("gecko")
    );


    public static void register(IEventBus eventBus){
        ENTITY_TYPE.register(eventBus);
    }
}
