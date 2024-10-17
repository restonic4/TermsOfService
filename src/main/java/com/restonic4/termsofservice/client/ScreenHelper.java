package com.restonic4.termsofservice.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenHelper {
    public static void openCustomScreen(String title, String description, String buttonText) {
        Minecraft client = Minecraft.getInstance();

        Component titleComponent = Component.literal(title);
        Component descriptionComponent = Component.literal(description);

        new Thread(() -> {
            AtomicBoolean done = new AtomicBoolean(false);

            ServerScreen serverScreen = new ServerScreen(titleComponent, descriptionComponent, buttonText, (mc) -> {
                done.set(true);
            });

            while (!done.get()) {
                client.setScreen(serverScreen);
            }
        }).start();
    }
}
