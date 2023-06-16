package net.satisfyu.meadow;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class MeadowExpectPlatform {
    @ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }
}
