package net.satisfyu.meadow.util;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

public enum ShutterType implements StringRepresentable {
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom"),
    NONE("none");

    private final String name;

    ShutterType(String type) {
        this.name = type;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    public static final EnumProperty<ShutterType> SHUTTER_TYPE;

    static {
        SHUTTER_TYPE = EnumProperty.create("type", ShutterType.class);
    }
}

