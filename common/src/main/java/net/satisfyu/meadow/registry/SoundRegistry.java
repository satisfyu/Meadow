package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.util.MeadowIdentifier;

public class SoundRegistry {
    public static Registrar<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Meadow.MOD_ID, RegistryKeys.SOUND_EVENT).getRegistrar();

    public static final RegistrySupplier<SoundEvent> COOKING_CAULDRON = create("cooking_cauldron");
    public static final RegistrySupplier<SoundEvent> WOODCUTTER = create("woodcutter");
    public static final RegistrySupplier<SoundEvent> CLICK_CAMERA = create("click_camera");
    public static final RegistrySupplier<SoundEvent> CLICK_CAMERA2 = create("click_camera2");
    public static final RegistrySupplier<SoundEvent> SHELF_OPEN = create("shelf_open");
    public static final RegistrySupplier<SoundEvent> SHELF_CLOSED = create("shelf_closed");

    private static RegistrySupplier<SoundEvent> create(String name) {
        final Identifier id = new MeadowIdentifier(name);
        return SOUND_EVENTS.register(id, () -> SoundEvent.of(id));
    }

    public static void init() {
        Meadow.LOGGER.debug("Registering Sounds for " + Meadow.MOD_ID);
    }
}
