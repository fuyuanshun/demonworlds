package com.fys.demonworlds.item.customer;

import com.fys.demonworlds.util.HealthUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        //减一滴血
        HealthUtil.addHealth(player, -2);
        return super.use(level, player, usedHand);
    }
}
