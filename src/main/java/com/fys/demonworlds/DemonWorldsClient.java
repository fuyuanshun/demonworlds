package com.fys.demonworlds;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.entity.ModEntityType;
import com.fys.demonworlds.entity.client.GeckoRenderer;
import com.fys.demonworlds.entity.custom.GeckoEntity;
import com.fys.demonworlds.item.ModItemProperties;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = ModConstants.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = ModConstants.MOD_ID, value = Dist.CLIENT)
public class DemonWorldsClient {
    public DemonWorldsClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        ModItemProperties.addProperties();

        EntityRenderers.register(ModEntityType.GECKO.get(), GeckoRenderer::new);
    }
}
