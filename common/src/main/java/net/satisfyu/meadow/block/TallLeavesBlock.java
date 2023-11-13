package net.satisfyu.meadow.block;

import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class TallLeavesBlock extends LeavesBlock {
    public TallLeavesBlock(Properties properties) {
        super(properties);
    }

    public static final EnumProperty<DoubleBlockHalf> HALF;

}
