package net.satisfy.meadow.util;

import net.minecraft.resources.ResourceLocation;
import net.satisfy.meadow.Meadow;

public final class MeadowIdentifier {
    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(Meadow.MOD_ID, path);
    }

    public static ResourceLocation of(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}

