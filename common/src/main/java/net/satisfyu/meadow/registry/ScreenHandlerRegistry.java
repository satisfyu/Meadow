package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.client.screen.handler.CookingPotGuiHandler;
import net.satisfyu.meadow.client.screen.handler.FondueGuiHandler;
import net.satisfyu.meadow.client.screen.handler.WoodcutterGuiHandler;

import java.util.function.Supplier;

public class ScreenHandlerRegistry {
    public static final DeferredRegister<ScreenHandlerType<?>> SCREEN_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registry.MENU_KEY);

    public static final RegistrySupplier<ScreenHandlerType<WoodcutterGuiHandler>> WOODCUTTER_SCREEN_HANDLER = create("woodcutter", () -> new ScreenHandlerType<>(WoodcutterGuiHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<CookingPotGuiHandler>> COOKING_CAULDRON_SCREEN_HANDLER = create("cooking_cauldron", () -> new ScreenHandlerType<>(CookingPotGuiHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<CheeseFormGuiHandler>> CHEESE_FORM_SCREEN_HANDLER = create("cheese_form", () -> new ScreenHandlerType<>(CheeseFormGuiHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<FondueGuiHandler>> FONDUE_SCREEN_HANDLER = create("fondue", () -> new ScreenHandlerType<>(FondueGuiHandler::new));


    public static void init() {
        Meadow.LOGGER.debug("Registering Screens for " + Meadow.MOD_ID);
        SCREEN_TYPES.register();
    }

    private static <T extends ScreenHandlerType<?>> RegistrySupplier<T> create(String name, Supplier<T> type) {
        return SCREEN_TYPES.register(name, type);
    }

}
