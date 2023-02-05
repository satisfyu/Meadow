package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;

public class MainStoveBlock extends StoveBlock {

    public static final BooleanProperty CONNECTED_UP = BooleanProperty.of("connected_up");
    public MainStoveBlock(Settings settings) {
        super(settings, Direction.UP);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CONNECTED_UP);
    }
}
