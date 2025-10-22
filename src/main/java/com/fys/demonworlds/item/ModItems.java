package com.fys.demonworlds.item;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.customer.DemonFruitMoon;
import com.fys.demonworlds.item.customer.DemonFruitSun;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModConstants.MOD_ID);

    public static final DeferredItem<Item> DEMON_FRUIT_SUN = ITEMS.register("demon_fruit_sun",
            () -> new DemonFruitSun(new Item.Properties()));

    public static final DeferredItem<Item> DEMON_FRUIT_MOON = ITEMS.register("demon_fruit_moon",
            () -> new DemonFruitMoon(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
