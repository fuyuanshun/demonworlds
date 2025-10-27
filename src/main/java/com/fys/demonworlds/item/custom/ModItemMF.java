package com.fys.demonworlds.item.custom;

import com.fys.demonworlds.block.component.ModDataComponents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModItemMF extends Item {

    public ModItemMF(Properties properties) {
        super(properties);
    }

    //使用时，改变魔方的属性值
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if(!level.isClientSide){
            if(context.getItemInHand().has(ModDataComponents.TOGGLE)){
                context.getItemInHand().remove(ModDataComponents.TOGGLE);
            } else {
                context.getItemInHand().set(ModDataComponents.TOGGLE, 1);
            }
        }

        return super.useOn(context);
    }


}
