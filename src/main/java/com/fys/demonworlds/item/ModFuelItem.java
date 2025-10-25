package com.fys.demonworlds.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

/**
 * 可燃物 使用getBurnTime设置燃烧时间即可
 * @author fys
 * @date 2025/10/25
 * @description
 */
public class ModFuelItem extends Item {
    private int fuelTime;


    public ModFuelItem(Properties properties, int fuelTime) {
        super(properties);
        this.fuelTime = fuelTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.fuelTime;
    }
}
