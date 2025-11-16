package com.fys.demonworlds.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

/**
 * @author fys
 * @since 2025-11-16
 */
public class LightningEnchantment implements EnchantmentEntityEffect {

    public static final MapCodec<? extends EnchantmentEntityEffect> CODEC
             = MapCodec.unit(LightningEnchantment::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if(enchantmentLevel == 1){
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), MobSpawnType.TRIGGERED);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
