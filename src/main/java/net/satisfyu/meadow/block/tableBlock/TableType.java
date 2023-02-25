package net.satisfyu.meadow.block.tableBlock;

import net.minecraft.util.StringIdentifiable;

public enum TableType implements StringIdentifiable {
    NONE("none"),
    MIDDLE("middle"),

    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west"),

    NORTH_SOUTH("north_south"),
    EAST_WEST("east_west"),

    NORTH_EAST("north_east"),
    EAST_SOUTH("east_south"),
    SOUTH_WEST("south_west"),
    WEST_NORTH("west_north");

    private final String name;

    private TableType(String type) {
        this.name = type;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}

