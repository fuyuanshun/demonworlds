package com.fys.demonworlds;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.block.component.ModDataComponents;
import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.effect.ModMobEffects;
import com.fys.demonworlds.enchantment.ModEnchantmentEffects;
import com.fys.demonworlds.entity.ModEntityType;
import com.fys.demonworlds.item.ModCreativeModeTabs;
import com.fys.demonworlds.item.ModItems;
import com.fys.demonworlds.potion.ModPotions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ModConstants.MOD_ID)
public class DemonWorlds {

    public DemonWorlds(IEventBus modEventBus, ModContainer modContainer) {

        NeoForge.EVENT_BUS.register(this);

        //注册物品
        ModItems.register(modEventBus);
        //自定义药水
        ModPotions.registerPotions(modEventBus);
        //注册方块
        ModBlocks.register(modEventBus);
        //注册物品栏
        ModCreativeModeTabs.register(modEventBus);
        //注册自定义效果
        ModMobEffects.register(modEventBus);
        //自定义数据组件
        ModDataComponents.register(modEventBus);
        //自定义附魔
        ModEnchantmentEffects.register(modEventBus);
        ModEntityType.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    // Add the example block item to the building block tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
