package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class WoodStackBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    private static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 4, 15);

    private static final VoxelShape SHAPE_BIG = Block.createCuboidShape(1, 0, 1, 15, 16, 15);

    private static final VoxelShape SHAPE_MID = Block.createCuboidShape(1, 0, 1, 15, 12, 15);

    private final int size;
    public WoodStackBlock(Settings settings, int size) {
        super(settings);
        this.size = size;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(size == 0) return SHAPE;
        else if(size == 1) return SHAPE_MID;
        else if(size == 2) return SHAPE_BIG;
        else return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }


    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}
