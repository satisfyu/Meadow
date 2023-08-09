package net.satisfyu.meadow;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class MeadowExpectPlatform {

    @ExpectPlatform
    public static boolean isModLoaded(String modid) {
        throw new AssertionError();
    }
}
