package net.satisfyu.meadow.util;

import net.minecraft.util.StringRepresentable;
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
}

