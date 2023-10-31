package net.satisfyu.meadow.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.forge.networking.MeadowNetworkForge;

@Mod.EventBusSubscriber(modid = Meadow.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MeadowClientForge {
    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        MeadowClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MeadowNetworkForge.registerS2CPackets();
        MeadowClient.initClient();
    }
}
