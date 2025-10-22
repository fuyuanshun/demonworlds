package com.fys.demonworlds.item;

import com.fys.demonworlds.DemonWorlds;
import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.constants.ModConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author fys
 * @since 2025-10-22
 */
public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModConstants.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEMON_FRUIT_TAB = MODE_TAB.register("demon_fruit_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.demon_fruit_tab"))
                    .icon(()->ModItems.DEMON_FRUIT_SUN.get().getDefaultInstance())
                    .displayItems((parameters,output)->{
                        output.accept(ModItems.DEMON_FRUIT_SUN);
                        output.accept(ModItems.DEMON_FRUIT_MOON);
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEMON_WORLDS_TAB = MODE_TAB.register("demon_worlds_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.demon_worlds_tab"))
                    .icon(()->new ItemStack(ModBlocks.SUN_BLOCK))
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "demon_fruit_tab"))
                    .displayItems((parameters,output)->{
                        output.accept(ModBlocks.SUN_BLOCK);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        MODE_TAB.register(eventBus);
    }
}
