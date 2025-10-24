package com.fys.demonworlds.item;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.item.customer.DemonFruitLightning;
import com.fys.demonworlds.item.customer.DemonFruitMoon;
import com.fys.demonworlds.item.customer.DemonFruitSun;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

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

    public static final DeferredItem<Item> DEMON_FRUIT_LIGHTNING = ITEMS.register("demon_fruit_lightning",
            () -> new DemonFruitLightning(new Item.Properties()));

    public static final DeferredItem<Item> MF = ITEMS.register("mf",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                    tooltipComponents.add(Component.translatable("item.demonworlds.mf.desc"));
                }
            });

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
