package com.fys.demonworlds.item;

import com.fys.demonworlds.block.component.ModDataComponents;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * 自定义物品属性
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModItemProperties {

    public static void addProperties() {
        //
        ItemProperties.register(ModItems.MF.get(), ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "used"), (itemStack,clientLevel,entity, d)->{
            return itemStack.get(ModDataComponents.TOGGLE) == null ? 0F : 1F;
        });

        //
        customBow(ModItems.DIAMOND_BOW.get());
    }

    public static void customBow(Item item){
        ItemProperties.register(item, ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "pull"), (p_344163_, p_344164_, p_344165_, p_344166_) -> {
            if (p_344165_ == null) {
                return 0.0F;
            } else {
                return p_344165_.getUseItem() != p_344163_ ? 0.0F : (float)(p_344163_.getUseDuration(p_344165_) - p_344165_.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(
                item,
                ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "pulling"),
                (p_174630_, p_174631_, p_174632_, p_174633_) -> p_174632_ != null && p_174632_.isUsingItem() && p_174632_.getUseItem() == p_174630_ ? 1.0F : 0.0F
        );
    }

}
