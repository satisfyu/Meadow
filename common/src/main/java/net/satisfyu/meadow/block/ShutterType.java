package net.satisfyu.meadow.block;

import net.minecraft.util.StringRepresentable;

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
    public String getSerializedName() {
        return this.name;
    }
}

