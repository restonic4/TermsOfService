package com.restonic4.termsofservice.networking.messages;

import com.restonic4.termsofservice.networking.Messages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;

public class ToPlayerMessage {
    public static void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender)
    {
        // Read the server data.

        String message = buf.readUtf();
        System.out.println(message);

        // Tell the sever that the client has the mod.

        FriendlyByteBuf friendlyByteBuf = PacketByteBufs.create();
        friendlyByteBuf.writeBoolean(true);

        ClientPlayNetworking.send(Messages.TO_SERVER, friendlyByteBuf);
    }
}
