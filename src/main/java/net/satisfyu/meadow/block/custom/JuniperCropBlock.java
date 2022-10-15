package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.satisfyu.meadow.item.ModItems;

public class JuniperCropBlock extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age", 0, 2);
    protected static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 15.0, 16.0)
    };


    public JuniperCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.JUNIPER_SEEDS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(this.getAgeProperty())];
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
