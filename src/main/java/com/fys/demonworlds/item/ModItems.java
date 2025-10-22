package com.fys.demonworlds.item;

import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
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
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .alwaysEdible()
                    .build())));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
