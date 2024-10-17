package com.restonic4.termsofservice.client;

import com.restonic4.termsofservice.networking.Messages;
import net.fabricmc.api.ClientModInitializer;

public class TermsOfServiceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Messages.registerServerToClientPackets();
    }
}
