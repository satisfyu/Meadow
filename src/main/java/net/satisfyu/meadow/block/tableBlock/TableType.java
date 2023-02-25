package net.satisfyu.meadow.block.tableBlock;

import net.minecraft.util.StringIdentifiable;

public enum TableType implements StringIdentifiable {
    NONE("none"),
    CENTER("center"),

    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west"),

    NORTH_SOUTH("north_south"),
    EAST_WEST("east_west"),

    NORTH_CORNER("north_corner"),
    EAST_CORNER("east_corner"),
    SOUTH_CORNER("south_corner"),
    WEST_CORNER("west_corner"),

    NORTH_EDGE("north_edge"),
    EAST_EDGE("east_edge"),
    SOUTH_EDGE("south_edge"),
    WEST_EDGE("west_edge");

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

