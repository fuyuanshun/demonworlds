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

    //恶魔果实
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEMON_WORLDS_FRUIT_TAB = MODE_TAB.register("demon_worlds_fruit",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.demon_worlds_fruit"))
                    .icon(()->ModItems.DEMON_FRUIT_SUN.get().getDefaultInstance())
                    .displayItems((parameters,output)->{
                        output.accept(ModItems.DEMON_FRUIT_SUN);
                        output.accept(ModItems.DEMON_FRUIT_MOON);
                        output.accept(ModItems.DEMON_FRUIT_LIGHTNING);
                    })
                    .build());

    //工具、装备
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEMON_WORLDS_EQUIPMENT_TAB = MODE_TAB.register("demon_worlds_equipment",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.demon_worlds_equipment"))
                    .icon(()->ModItems.END_PICKAXE.get().getDefaultInstance())
                    .displayItems((parameters,output)->{
                        //武器
                        output.accept(ModItems.BAT);
                        output.accept(ModItems.END_SWORD);
                        //工具
                        output.accept(ModItems.END_AXE);
                        output.accept(ModItems.END_PICKAXE);
                        output.accept(ModItems.END_HOE);
                        output.accept(ModItems.END_SHOVEL);
                    })
                    .build());

    //杂项
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEMON_WORLDS_MISC_TAB = MODE_TAB.register("demon_worlds_misc",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.demon_worlds_misc"))
                    .icon(()->new ItemStack(ModItems.MF.get()))
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ModConstants.MOD_ID, "demon_fruit_tab"))
                    .displayItems((parameters,output)->{
                        output.accept(ModBlocks.SUN_BLOCK);
                        output.accept(ModBlocks.SUN_ORE);
                        output.accept(ModBlocks.END_BLOCK);
                        output.accept(ModBlocks.END_ORE);
                        output.accept(ModBlocks.SIMPLE_BLOCK);
                        output.accept(ModItems.MF);


                    })
                    .build());

    public static void register(IEventBus eventBus){
        MODE_TAB.register(eventBus);
    }
}
