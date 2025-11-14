package com.fys.demonworlds.event;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

/**
 * @author fys
 * @since 2025-11-01
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModBrewingEventEvents {

    /**
     * 酿造台-药水配方
     * @param event
     */
    @SubscribeEvent
    public static void onBrewingEvent(RegisterBrewingRecipesEvent event){
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIME_POTION);
    }

}
