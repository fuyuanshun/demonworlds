package com.fys.demonworlds;

import com.fys.demonworlds.block.ModBlocks;
import com.fys.demonworlds.item.ModCreativeModeTabs;
import com.fys.demonworlds.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(DemonWorlds.MOD_ID)
public class DemonWorlds {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "demon-worlds";


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public DemonWorlds(IEventBus modEventBus, ModContainer modContainer) {

        NeoForge.EVENT_BUS.register(this);

        //注册物品
        ModItems.register(modEventBus);
        //注册方块
        ModBlocks.register(modEventBus);
        //注册物品栏
        ModCreativeModeTabs.register(modEventBus);


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
