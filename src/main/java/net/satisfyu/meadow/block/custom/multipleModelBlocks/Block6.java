package net.satisfyu.meadow.block.custom.multipleModelBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Block6 extends Block {

    public static final IntProperty VAR = IntProperty.of("var", 0, 5);

    public Block6(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(VAR, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VAR);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(new Random().nextFloat() < 0.2){
            return getDefaultState().with(VAR, new Random().nextInt(2));
        }
        return getDefaultState().with(VAR, new Random().nextInt(2, 6));
    }


}
