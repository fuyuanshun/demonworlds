package com.fys.demonworlds.event;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.packet.LightningAbilityPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * @author fys
 * @date 2025/10/26
 * @description
 */
@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModNetWorkEventListener {

    @SubscribeEvent
    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(ModConstants.MOD_ID);

        registrar.playToServer(
                LightningAbilityPacket.TYPE,
                LightningAbilityPacket.STREAM_CODEC,
                LightningAbilityPacket::handle
        );
    }
}
