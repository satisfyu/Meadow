package net.satisfyu.meadow.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class MeadowExpectPlatformImpl {
    public static boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }
}