package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.custom.chair.ChairUtil;

public class TiledBench extends HFacingBlock {

    public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 4, 2, 4), Block.createCuboidShape(12, 0, 0, 16, 2, 4), Block.createCuboidShape(0, 0, 12, 4, 2, 16), Block.createCuboidShape(12, 0, 12, 16, 2, 16));

    public static final VoxelShape SHAPE_SMALL = VoxelShapes.union(SHAPE, Block.createCuboidShape(0, 2, 0, 16, 6, 16));


    public TiledBench(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_SMALL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
         return ChairUtil.onUse(world, player, hand, hit, -0.1);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        ChairUtil.onStateReplaced(world, pos);
    }
}
