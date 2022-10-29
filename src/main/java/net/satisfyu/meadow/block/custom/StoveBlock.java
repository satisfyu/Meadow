package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class StoveBlock extends FacingBlock {

    public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 4, 2, 4), Block.createCuboidShape(12, 0, 0, 16, 2, 4), Block.createCuboidShape(0, 0, 12, 16, 2, 16), Block.createCuboidShape(12, 0, 12, 16, 2, 16));

    public static final VoxelShape SHAPE_BIG = VoxelShapes.union(SHAPE, Block.createCuboidShape(0, 2, 0, 16, 16, 16));

    public static final VoxelShape SHAPE_SMALL = VoxelShapes.union(SHAPE, Block.createCuboidShape(0, 2, 0, 16, 6, 16));

    private final boolean isBig;

    public StoveBlock(Settings settings, boolean isBig) {
        super(settings);
        this.isBig = isBig;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return isBig ? SHAPE_BIG : SHAPE_SMALL;
    }
}
