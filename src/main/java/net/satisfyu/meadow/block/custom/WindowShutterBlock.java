package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class WindowShutterBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public WindowShutterBlock(Settings settings) {
        super(settings);
    }


    private static final VoxelShape[] SHAPE = {
            Block.createCuboidShape(0, 0, 0, 0, 0, 0),
            Block.createCuboidShape(0, 0, 1, 1, 16, 15),
            Block.createCuboidShape(0, 0, 0, 0, 0, 0),
            Block.createCuboidShape(0, 0, 0, 0, 0, 0)
    };

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        switch (state.get(FACING)) {
            default: {
                return SHAPE[0];
            }
            case WEST: {
                return SHAPE[1];
            }
            case SOUTH: {
                return SHAPE[2];
            }
            case EAST:
        }
        return SHAPE[3];

    }

    @Nullable
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

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}

