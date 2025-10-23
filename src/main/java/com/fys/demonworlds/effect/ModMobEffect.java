package com.fys.demonworlds.effect;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

/**
 * @author fys
 * @since 2025-10-23
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModMobEffect {

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event){
        if(event.getEffectInstance() != null && event.getEffectInstance().getEffect() == MobEffects.NIGHT_VISION){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(MobEffectEvent.Expired event){
        if(event.getEffectInstance() != null && event.getEffectInstance().getEffect() == MobEffects.NIGHT_VISION){
            event.setCanceled(true);
        }
    }
}
