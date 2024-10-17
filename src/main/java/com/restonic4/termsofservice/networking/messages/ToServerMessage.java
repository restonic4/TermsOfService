package com.restonic4.termsofservice.networking.messages;

import com.restonic4.termsofservice.TermsOfService;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;

public class ToServerMessage {
    public static void receive(MinecraftServer minecraftServer, ServerPlayer serverPlayer, ServerGamePacketListenerImpl serverGamePacketListener, FriendlyByteBuf friendlyByteBuf, PacketSender packetSender) {
        TermsOfService.playersWarned.add(serverPlayer.getName().getString());
    }
}
