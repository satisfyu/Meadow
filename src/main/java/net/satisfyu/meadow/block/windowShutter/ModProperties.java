package net.satisfyu.meadow.block.windowShutter;

import net.minecraft.state.property.EnumProperty;

public class ModProperties{
    public static final EnumProperty<ShutterType> SHUTTER_TYPE;

    static {
        SHUTTER_TYPE = EnumProperty.of("type", ShutterType.class);
    }
}
