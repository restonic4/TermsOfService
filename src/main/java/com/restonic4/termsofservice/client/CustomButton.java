package com.restonic4.termsofservice.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class CustomButton extends Button {
    private final Consumer<Minecraft> onButtonClick;

    protected CustomButton(int i, int j, int k, int l, Component component, Consumer<Minecraft> onButtonClick, OnPress onPress) {
        super(i, j, k, l, component, onPress, DEFAULT_NARRATION);
        this.onButtonClick = onButtonClick;
    }

    @Override
    public void onPress() {
        super.onPress();

        onButtonClick.accept(Minecraft.getInstance());
    }
}
