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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TermsOfService implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String ID = "terms_of_service";

    public static final List<String> playersWarned = new ArrayList<>();

    @Override
    public void onInitialize() {
        Messages.registerClientToServerPackets();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();

            FriendlyByteBuf friendlyByteBuf = PacketByteBufs.create();
            friendlyByteBuf.writeUtf("Al unirse y permanecer en el Servidor, los usuarios aceptan cumplir con todas las reglas establecidas y reconocen que el incumplimiento de estas normas puede resultar en sanciones aplicadas por la Administración. Al igual que con los Términos de uso.");

            if (ServerPlayNetworking.canSend(player, Messages.TO_SERVER)) {
                ServerPlayNetworking.send(player, Messages.TO_PLAYER, friendlyByteBuf);
            }

            player.getServer().execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ignored) {}

                if (!playersWarned.contains(player.getName().getString())) {
                    playersWarned.add(player.getName().getString());

                    player.sendSystemMessage(Component.literal("Al unirse y permanecer en el Servidor, los usuarios aceptan cumplir con todas las reglas establecidas y reconocen que el incumplimiento de estas normas puede resultar en sanciones aplicadas por la Administración. Al igual que con los Términos de uso."));
                }
            });
        });
    }
}
