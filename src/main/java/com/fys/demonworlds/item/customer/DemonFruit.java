package com.fys.demonworlds.item.customer;

import com.fys.demonworlds.util.Utils;
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

    /**
     * 所有恶魔果实通用效果
     * 吃掉后获得诅咒效果，生命值- ((已吃恶魔果实+1)* ModConstants.DEMON_FRUIT_SUB_HEALTH )（最小值不会小于2）。相同恶魔果实只会减少一次生命上限
     * @param stack
     * @param level
     * @param livingEntity
     * @return
     */
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            Utils.subHealthByFruitCount(player, this.getDefaultInstance().getDescriptionId());
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
