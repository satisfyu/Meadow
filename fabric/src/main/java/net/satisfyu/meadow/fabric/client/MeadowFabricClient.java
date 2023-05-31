package net.satisfyu.meadow.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.satisfyu.meadow.client.MeadowClient;

public class MeadowFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MeadowClient.preInitClient();
        MeadowClient.initClient();
    }
}
