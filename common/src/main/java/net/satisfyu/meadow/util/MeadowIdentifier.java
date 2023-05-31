package net.satisfyu.meadow.util;

import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

public class MeadowIdentifier extends Identifier {

    public MeadowIdentifier(String path) {
        super(Meadow.MOD_ID, path);
    }

    public static String asString(String path) {
        return (Meadow.MOD_ID + ":" + path);
    }
}

