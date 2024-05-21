package net.satisfy.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.client.gui.handler.CheeseFormGuiHandler;
import net.satisfy.meadow.client.gui.handler.CookingCauldronGuiHandler;
import net.satisfy.meadow.client.gui.handler.FondueGuiHandler;
import net.satisfy.meadow.client.gui.handler.WoodcutterGuiHandler;

import java.util.function.Supplier;

public class ScreenHandlerRegistry {
    public static final DeferredRegister<MenuType<?>> SCREEN_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<WoodcutterGuiHandler>> WOODCUTTER_SCREEN_HANDLER = create("woodcutter", () -> new MenuType<>(WoodcutterGuiHandler::new, FeatureFlags.VANILLA_SET));
    public static final RegistrySupplier<MenuType<CookingCauldronGuiHandler>> COOKING_CAULDRON_SCREEN_HANDLER = create("cooking_cauldron", () -> new MenuType<>(CookingCauldronGuiHandler::new, FeatureFlags.VANILLA_SET));
    public static final RegistrySupplier<MenuType<CheeseFormGuiHandler>> CHEESE_FORM_SCREEN_HANDLER = create("cheese_form", () -> new MenuType<>(CheeseFormGuiHandler::new, FeatureFlags.VANILLA_SET));
    public static final RegistrySupplier<MenuType<FondueGuiHandler>> FONDUE_SCREEN_HANDLER = create("fondue", () -> new MenuType<>(FondueGuiHandler::new, FeatureFlags.VANILLA_SET));


    public static void init() {
        Meadow.LOGGER.debug("Registering Screens for " + Meadow.MOD_ID);
        SCREEN_TYPES.register();
    }

    private static <T extends MenuType<?>> RegistrySupplier<T> create(String name, Supplier<T> type) {
        return SCREEN_TYPES.register(name, type);
    }

}
