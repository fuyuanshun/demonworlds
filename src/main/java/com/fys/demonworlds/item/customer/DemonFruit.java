package com.fys.demonworlds.item.customer;

import com.fys.demonworlds.util.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author fys
 * @date 2025/10/22
 * @description
 */
public class DemonFruit extends Item {

    public DemonFruit(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            Utils.addHealth(player, -2, this.getDefaultInstance().getDescriptionId());
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
