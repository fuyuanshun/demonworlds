package com.fys.demonworlds.event;

import com.fys.demonworlds.constants.ModConstants;
import com.fys.demonworlds.packet.ElytraFlightPacket;
import com.fys.demonworlds.packet.ElytraSlotPacket;
import com.fys.demonworlds.packet.LightningAbilityPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ModNetWorkEventEvents {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(ModConstants.MOD_ID);

        registrar.playToServer(
                LightningAbilityPacket.TYPE,
                LightningAbilityPacket.STREAM_CODEC,
                LightningAbilityPacket::handle
        );

        registrar.playToServer(
                ElytraSlotPacket.TYPE,
                ElytraSlotPacket.STREAM_CODEC,
                ElytraSlotPacket::handle
        );

        registrar.playToServer(
                ElytraFlightPacket.TYPE,
                ElytraFlightPacket.STREAM_CODEC,
                ElytraFlightPacket::handle
        );
    }
}
