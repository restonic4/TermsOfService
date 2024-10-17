package com.restonic4.termsofservice.networking;

import com.restonic4.termsofservice.TermsOfService;
import com.restonic4.termsofservice.networking.messages.ToPlayerMessage;
import com.restonic4.termsofservice.networking.messages.ToServerMessage;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class Messages  {
    public static final ResourceLocation TO_PLAYER = new ResourceLocation(TermsOfService.ID, "to_player");
    public static final ResourceLocation TO_SERVER = new ResourceLocation(TermsOfService.ID, "to_server");

    public static void registerServerToClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(TO_PLAYER, ToPlayerMessage::receive);
    }

    public static void registerClientToServerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(TO_SERVER, ToServerMessage::receive);
    }
}
