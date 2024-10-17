package com.restonic4.termsofservice;

import com.restonic4.termsofservice.networking.Messages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TermsOfService implements ModInitializer {
    public static final String ID = "terms_of_service";

    public static final List<String> playersWarned = new ArrayList<>();

    @Override
    public void onInitialize() {
        Messages.registerClientToServerPackets();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();

            FriendlyByteBuf friendlyByteBuf = PacketByteBufs.create();
            friendlyByteBuf.writeUtf("Hola, creo que se contar");

            if (ServerPlayNetworking.canSend(player, Messages.TO_SERVER)) {
                ServerPlayNetworking.send(player, Messages.TO_PLAYER, friendlyByteBuf);
            }

            player.getServer().execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ignored) {}

                if (!playersWarned.contains(player.getName().getString())) {
                    playersWarned.add(player.getName().getString());

                    player.sendSystemMessage(Component.literal("No se detectó el mod en tu cliente."));
                }
            });
        });
    }
}
