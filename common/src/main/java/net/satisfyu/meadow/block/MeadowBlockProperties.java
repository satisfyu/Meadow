package net.satisfyu.meadow.block;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.satisfyu.meadow.util.LineConnectingType;

public class MeadowBlockProperties {
    public static final EnumProperty<ShutterType> SHUTTER_TYPE;
    public static final EnumProperty<LineConnectingType> LINE_CONNECTING_TYPE;

    static {
        SHUTTER_TYPE = EnumProperty.create("type", ShutterType.class);
        LINE_CONNECTING_TYPE = EnumProperty.create("type", LineConnectingType.class);
    }
}
