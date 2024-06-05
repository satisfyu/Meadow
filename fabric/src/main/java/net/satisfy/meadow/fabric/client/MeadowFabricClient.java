package net.satisfy.meadow.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.satisfy.meadow.client.MeadowClient;
import net.satisfy.meadow.registry.ObjectRegistry;

public class MeadowFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MeadowClient.preInitClient();
        MeadowClient.initClient();

        ArmorRenderer.register(new FurArmorHatRenderer(), ObjectRegistry.FUR_HELMET.get());
    }
}
