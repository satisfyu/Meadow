package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.client.screen.handler.CookingCauldronGuiHandler;
import net.satisfyu.meadow.client.screen.handler.FondueGuiHandler;
import net.satisfyu.meadow.client.screen.handler.WoodcutterGuiHandler;

import java.util.function.Supplier;

public class ScreenHandlerRegistry {
    public static final DeferredRegister<ScreenHandlerType<?>> SCREEN_TYPES = DeferredRegister.create(Meadow.MOD_ID, RegistryKeys.SCREEN_HANDLER);

    public static final RegistrySupplier<ScreenHandlerType<WoodcutterGuiHandler>> WOODCUTTER_SCREEN_HANDLER = create("woodcutter", () -> new ScreenHandlerType<>(WoodcutterGuiHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final RegistrySupplier<ScreenHandlerType<CookingCauldronGuiHandler>> COOKING_CAULDRON_SCREEN_HANDLER = create("cooking_cauldron", () -> new ScreenHandlerType<>(CookingCauldronGuiHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final RegistrySupplier<ScreenHandlerType<CheeseFormGuiHandler>> CHEESE_FORM_SCREEN_HANDLER = create("cheese_form", () -> new ScreenHandlerType<>(CheeseFormGuiHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final RegistrySupplier<ScreenHandlerType<FondueGuiHandler>> FONDUE_SCREEN_HANDLER = create("fondue", () -> new ScreenHandlerType<>(FondueGuiHandler::new, FeatureFlags.VANILLA_FEATURES));


    public static void init() {
        Meadow.LOGGER.debug("Registering Screens for " + Meadow.MOD_ID);
        SCREEN_TYPES.register();
    }

    private static <T extends ScreenHandlerType<?>> RegistrySupplier<T> create(String name, Supplier<T> type) {
        return SCREEN_TYPES.register(name, type);
    }

}
