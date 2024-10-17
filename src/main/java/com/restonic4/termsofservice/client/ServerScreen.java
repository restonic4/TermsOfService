package com.restonic4.termsofservice.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.core.pattern.TextRenderer;

import java.awt.*;
import java.util.function.Consumer;

public class ServerScreen extends Screen {
    private final Component titleText;
    private final Component descriptionText;
    private final String buttonText;
    private final Consumer<Minecraft> onButtonClick;

    public ServerScreen(Component title, Component description, String buttonText, Consumer<Minecraft> onButtonClick) {
        super(title);
        this.titleText = title;
        this.descriptionText = description;
        this.buttonText = buttonText;
        this.onButtonClick = onButtonClick;
    }

    @Override
    protected void init() {
        int midWidth = this.width / 2;
        int midHeight = this.height / 2;

        this.addRenderableWidget(new CustomButton(midWidth - 50, midHeight + 50, 100, 20, Component.literal(buttonText), onButtonClick, (button) -> {
            this.minecraft.setScreen(null);
        }));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        guiGraphics.drawCenteredString(this.font, this.titleText, this.width / 2, 40, 0xFFFFFF);

        guiGraphics.drawCenteredString(this.font, this.descriptionText, this.width / 2, 80, 0xFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, delta);
    }
}
