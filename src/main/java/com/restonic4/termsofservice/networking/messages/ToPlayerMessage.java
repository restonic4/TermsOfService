package com.restonic4.termsofservice.networking.messages;

import com.restonic4.termsofservice.client.ScreenHelper;
import com.restonic4.termsofservice.networking.Messages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mojang.text2speech.Narrator.LOGGER;

public class ToPlayerMessage {
    public static void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender)
    {
        // Read the server data.

        String message = buf.readUtf();
        Minecraft.getInstance().execute(() -> {
            try {
                ScreenHelper.openCustomScreen("Terms of service", message, "Accept");
            } catch (Exception e) {
                LOGGER.info("Error", e);
            }
        });

        // Tell the sever that the client has the mod.

        FriendlyByteBuf friendlyByteBuf = PacketByteBufs.create();
        friendlyByteBuf.writeBoolean(true);

        ClientPlayNetworking.send(Messages.TO_SERVER, friendlyByteBuf);
    }
}
