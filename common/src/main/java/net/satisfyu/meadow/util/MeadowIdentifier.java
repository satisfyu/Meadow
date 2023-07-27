package net.satisfyu.meadow.util;

import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.Meadow;

public class MeadowIdentifier extends ResourceLocation {
    public MeadowIdentifier(String path) {
        super(Meadow.MOD_ID, path);
    }
}

