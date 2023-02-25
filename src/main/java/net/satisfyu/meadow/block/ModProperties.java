package net.satisfyu.meadow.block;

import net.minecraft.state.property.EnumProperty;
import net.satisfyu.meadow.block.tableBlock.TableType;
import net.satisfyu.meadow.block.windowShutter.ShutterType;

public class ModProperties{
    public static final EnumProperty<ShutterType> SHUTTER_TYPE;
    public static final EnumProperty<TableType> TABLE_TYPE;

    static {
        SHUTTER_TYPE = EnumProperty.of("type", ShutterType.class);
        TABLE_TYPE = EnumProperty.of("type", TableType.class);
    }
}
