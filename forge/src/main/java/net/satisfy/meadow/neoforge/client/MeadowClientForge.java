package net.satisfy.meadow.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.client.MeadowClient;
import net.satisfy.meadow.client.gui.CheeseFormGui;
import net.satisfy.meadow.client.gui.CookingCauldronGui;
import net.satisfy.meadow.client.gui.FondueGui;
import net.satisfy.meadow.client.gui.WoodcutterGui;
import net.satisfy.meadow.neoforge.networking.MeadowNetworkForge;
import net.satisfy.meadow.registry.ScreenHandlerRegistry;

@EventBusSubscriber(modid = Meadow.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
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

    @SubscribeEvent
    public static void clientSetup(RegisterMenuScreensEvent event) {
        event.register(ScreenHandlerRegistry.CHEESE_FORM_SCREEN_HANDLER.get(), CheeseFormGui::new);
        event.register(ScreenHandlerRegistry.WOODCUTTER_SCREEN_HANDLER.get(), WoodcutterGui::new);
        event.register(ScreenHandlerRegistry.COOKING_CAULDRON_SCREEN_HANDLER.get(), CookingCauldronGui::new);
        event.register(ScreenHandlerRegistry.FONDUE_SCREEN_HANDLER.get(), FondueGui::new);

    }
}
