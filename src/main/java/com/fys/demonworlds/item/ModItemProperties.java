package com.fys.demonworlds.item;

import com.fys.demonworlds.block.component.ModDataComponents;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;

/**
 * 物品属性
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModItemProperties {

    public static void addProperties() {
        ItemProperties.register(ModItems.MF.get(), ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "used"), (itemStack,clientLevel,entity, d)->{
            return itemStack.get(ModDataComponents.TOGGLE) == null ? 0F : 1F;
        });
    }

}
