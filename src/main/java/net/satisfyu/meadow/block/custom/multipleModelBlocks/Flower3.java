package net.satisfyu.meadow.block.custom.multipleModelBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Flower3 extends FlowerBlock {

    public static final IntProperty VAR = IntProperty.of("var", 0, 2);

    public Flower3(StatusEffect effect, int i, Settings settings) {
        super(effect, i,  settings);
        setDefaultState(this.getDefaultState().with(VAR, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VAR);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(VAR, new Random().nextInt(3));
    }


}
