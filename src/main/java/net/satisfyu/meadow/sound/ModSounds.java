package net.satisfyu.meadow.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;

public class ModSounds {

    public static final Identifier SLURPING_BOWL_ID = new Identifier(Meadow.MOD_ID, "slurping_bowl");
    public static final Identifier CLICK_CAMERA_ID = new Identifier(Meadow.MOD_ID, "click_camera");
    public static final Identifier CLICK_CAMERA2_ID = new Identifier(Meadow.MOD_ID, "click_camera2");
    public static final Identifier WOODCUTTER_ID = new Identifier(Meadow.MOD_ID, "woodcutter");

    public static final Identifier COOKING_CAULDRON_ID = new Identifier(Meadow.MOD_ID, "cooking_cauldron");


    public static SoundEvent SLURPING_BOWL = new SoundEvent(SLURPING_BOWL_ID);

    public static SoundEvent COOKING_CAULDRON = new SoundEvent(COOKING_CAULDRON_ID);
    public static SoundEvent WOODCUTTER = new SoundEvent(WOODCUTTER_ID);
    public static SoundEvent CLICK_CAMERA = new SoundEvent(CLICK_CAMERA_ID);
    public static SoundEvent CLICK_CAMERA2 = new SoundEvent(CLICK_CAMERA2_ID);


    private static void registerSound(Identifier id, SoundEvent sE) {
        Registry.register(Registry.SOUND_EVENT, id, sE);
    }

    public static void registerSounds(){
        Meadow.LOGGER.debug("Registering Mod Sounds for " + Meadow.MOD_ID);

        registerSound(COOKING_CAULDRON_ID, COOKING_CAULDRON);
        registerSound(WOODCUTTER_ID, WOODCUTTER);
        registerSound(SLURPING_BOWL_ID, SLURPING_BOWL);
        registerSound(CLICK_CAMERA_ID, CLICK_CAMERA);
        registerSound(CLICK_CAMERA2_ID, CLICK_CAMERA2);
    }

}
