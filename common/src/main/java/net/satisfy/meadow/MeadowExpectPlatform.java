package net.satisfy.meadow;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;

public class MeadowExpectPlatform {

    @ExpectPlatform
    public static boolean isModLoaded(String modid) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getTypeVariant(Entity entity){
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setTypeVariant(Entity entity, int var){
        throw new AssertionError();
    }
}