package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.util.MeadowIdentifier;

public class SoundRegistry {
    public static DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Meadow.MOD_ID, Registry.SOUND_EVENT_KEY);

    public static final RegistrySupplier<SoundEvent> SLURPING_BOWL = create("slurping_bowl");
    public static final RegistrySupplier<SoundEvent> EATING_BOWL = create("eating_bowl");
    public static final RegistrySupplier<SoundEvent> COOKING_CAULDRON = create("cooking_cauldron");
    public static final RegistrySupplier<SoundEvent> WOODCUTTER = create("woodcutter");
    public static final RegistrySupplier<SoundEvent> CLICK_CAMERA = create("click_camera");
    public static final RegistrySupplier<SoundEvent> CLICK_CAMERA2 = create("click_camera2");
    public static final RegistrySupplier<SoundEvent> SHELF_OPEN = create("shelf_open");
    public static final RegistrySupplier<SoundEvent> SHELF_CLOSED = create("shelf_closed");

    private static RegistrySupplier<SoundEvent> create(String name) {
        return SOUND_EVENTS.register(new MeadowIdentifier(name), () -> new SoundEvent(new MeadowIdentifier(name)));
    }

    public static void init(){
        Meadow.LOGGER.debug("Registering Mod Sounds for " + Meadow.MOD_ID);
    }

}
