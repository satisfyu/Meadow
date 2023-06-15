package net.satisfyu.meadow.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.meadow.util.ChairUtil;
import net.satisfyu.meadow.util.LineConnectingType;

public class BenchBlock extends LineConnectingBlock {

    public static final VoxelShape[] TOP_SHAPE;
    public static final VoxelShape[] BOTTOM_SINGLE_SHAPE;
    public static final VoxelShape[] BOTTOM_MULTI_SHAPE;

    public BenchBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean isX = state.get(FACING).getAxis() == Direction.Axis.X;
        Direction direction = state.get(FACING);

        if (state.get(TYPE) == LineConnectingType.NONE) {
            return VoxelShapes.union(isX ? TOP_SHAPE[0] : TOP_SHAPE[1], isX ? BOTTOM_SINGLE_SHAPE[0] : BOTTOM_SINGLE_SHAPE[1]);
        }
        if (state.get(TYPE) == LineConnectingType.MIDDLE) {
            return isX ? TOP_SHAPE[0] : TOP_SHAPE[1];
        }

        int i = 0;
        LineConnectingType type = state.get(TYPE);

        if((direction == Direction.NORTH && type == LineConnectingType.LEFT) || (direction == Direction.SOUTH && type == LineConnectingType.RIGHT)){
            i = 0;
        }
        else if((direction == Direction.NORTH && type == LineConnectingType.RIGHT) || (direction == Direction.SOUTH && type == LineConnectingType.LEFT)){
            i = 1;
        }
        else if((direction == Direction.EAST && type == LineConnectingType.RIGHT) || (direction == Direction.WEST && type == LineConnectingType.LEFT)){
            i = 2;
        }
        else if((direction == Direction.EAST && type == LineConnectingType.LEFT) || (direction == Direction.WEST && type == LineConnectingType.RIGHT)){
            i = 3;
        }
        return VoxelShapes.union(isX ? TOP_SHAPE[0] : TOP_SHAPE[1], BOTTOM_MULTI_SHAPE[i]);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ChairUtil.onUse(world, player, hand, hit, 0);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ChairUtil.onStateReplaced(world, pos);
    }

    static {
        TOP_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(2.0, 4.0, 0.0, 14.0, 8.0, 16.0), // X
                Block.createCuboidShape(0.0, 4.0, 2.0, 16.0, 8.0, 14.0)  // Y
        };
        BOTTOM_SINGLE_SHAPE = new VoxelShape[]{
                VoxelShapes.union(Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 4.0, 5.0), Block.createCuboidShape(1.0, 0.0, 11.0, 15.0, 4.0, 15.0)),// X
                VoxelShapes.union(Block.createCuboidShape(1.0, 0.0, 1.0, 5.0, 4.0, 15.0), Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 4.0, 15.0)) // Z
        };
        BOTTOM_MULTI_SHAPE = new VoxelShape[]{
                Block.createCuboidShape(1.0, 0.0, 1.0, 9.0, 4.0, 15.0), //left X
                Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 4.0, 15.0),//left Z
                Block.createCuboidShape(1.0, 0.0, 7.0, 15.0, 4.0, 15.0),//right X
                Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 4.0, 9.0), //right Z
        };
    }
}
