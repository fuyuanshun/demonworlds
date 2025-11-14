package com.fys.demonworlds.event;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

/**
 * @author fys
 * @date 2025/10/26
 * @description
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID, value = Dist.CLIENT)
public class ModClientEventEvents {
    @SubscribeEvent
    public static void onComputeFovModifyEvent(ComputeFovModifierEvent event) {

        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.DIAMOND_BOW.get()){
            float fovModifier = 1f;
            int i = event.getPlayer().getTicksUsingItem();
            float f1 = (float)i / 20.0F;
            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 *= f1;
            }

            fovModifier *= 1.0F - f1 * 0.15F;
            event.setNewFovModifier(fovModifier);
        }
    }
}
