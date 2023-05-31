package net.satisfyu.meadow.screenHandler;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.cheeseForm.CheeseFormScreenHandler;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreenHandler;
import net.satisfyu.meadow.block.cookingPot.CookingPotScreenHandler;
import net.satisfyu.meadow.block.fondueBlock.FondueScreenHandler;
import net.satisfyu.meadow.block.woodCutter.WoodcutterScreenHandler;

import java.util.function.Supplier;

public class ModScreenHandlers {
    public static final DeferredRegister<ScreenHandlerType<?>> SCREEN_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registry.MENU_KEY);

    public static final RegistrySupplier<ScreenHandlerType<WoodcutterScreenHandler>> WOODCUTTOR_SCREEN_HANDLER = create("woodcutter", () -> new ScreenHandlerType<>(WoodcutterScreenHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<CookingCauldronScreenHandler>> COOKING_CAULDRON_SCREEN_HANDLER = create("cooking_cauldron", () -> new ScreenHandlerType<>(CookingCauldronScreenHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<CheeseFormScreenHandler>> CHEESE_FORM_SCREEN_HANDLER = create("cheese_form", () -> new ScreenHandlerType<>(CheeseFormScreenHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<FondueScreenHandler>> FONDUE_SCREEN_HANDLER = create("fondue", () -> new ScreenHandlerType<>(FondueScreenHandler::new));
    public static final RegistrySupplier<ScreenHandlerType<CookingPotScreenHandler>> COOKING_POT_SCREEN_HANDLER = create("cooking_pot", () -> new ScreenHandlerType<>(CookingPotScreenHandler::new));


    public static void init() {
        Meadow.LOGGER.debug("Registering Screens for " + Meadow.MOD_ID);
        SCREEN_TYPES.register();
    }

    private static <T extends ScreenHandlerType<?>> RegistrySupplier<T> create(String name, Supplier<T> type) {
        return SCREEN_TYPES.register(name, type);
    }

}
