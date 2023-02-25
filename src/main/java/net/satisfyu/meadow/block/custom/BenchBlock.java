package net.satisfyu.meadow.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.ChestType;
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
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.tableBlock.TableBlock;
import net.satisfyu.meadow.entity.custom.chair.ChairUtil;

public class BenchBlock extends TableBlock {

    public BenchBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public static final VoxelShape[] MINI_SHAPE = {VoxelShapes.union(Block.createCuboidShape(1.0, 0.0, 1.0, 5.0, 4.0, 15.0), Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 4.0, 15.0)),
            VoxelShapes.union(Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 4.0, 5.0), Block.createCuboidShape(1.0, 0.0, 11.0, 15.0, 4.0, 15.0))};

    public static final VoxelShape[] SHAPE = {Block.createCuboidShape(1.0, 0.0, 1.0, 9.0, 4.0, 15.0), Block.createCuboidShape(7.0, 0.0, 1.0, 15.0, 4.0, 15.0),
            Block.createCuboidShape(1.0, 0.0, 7.0, 15.0, 4.0, 15.0), Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 4.0, 9.0)};

    public static final VoxelShape[] TOP_SHAPE = {Block.createCuboidShape(2.0, 4.0, 0.0, 14.0, 8.0, 16.0), Block.createCuboidShape(0.0, 4.0, 2.0, 16.0, 8.0, 14.0)};

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ChairUtil.onUse(world, player, hand, hit, 0);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ChairUtil.onStateReplaced(world, pos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean isX = state.get(FACING).getAxis() == Direction.Axis.X;
        Direction direction = state.get(FACING);
        if (state.get(CHEST_TYPE) == ChestType.SINGLE) {
            return VoxelShapes.union(isX ? TOP_SHAPE[0] : TOP_SHAPE[1], isX ? MINI_SHAPE[1] : MINI_SHAPE[0]);
        }

        int i;
        ChestType chestType = state.get(CHEST_TYPE);

        if((direction == Direction.NORTH && chestType == ChestType.LEFT) || (direction == Direction.SOUTH && chestType == ChestType.RIGHT)){
            i = 0;
        }
        else if((direction == Direction.NORTH && chestType == ChestType.RIGHT) || (direction == Direction.SOUTH && chestType == ChestType.LEFT)){ //
            i = 1;
        }
        else if((direction == Direction.EAST && chestType == ChestType.RIGHT) || (direction == Direction.WEST && chestType == ChestType.LEFT)){ //
            i = 2;
        }
        else if((direction == Direction.EAST && chestType == ChestType.LEFT) || (direction == Direction.WEST && chestType == ChestType.RIGHT)){
            i = 3;
        }
        else {
            Meadow.LOGGER.error("Table blockstate not correct!");
            return TOP_SHAPE[0];
        }
        return VoxelShapes.union(isX ? TOP_SHAPE[0] : TOP_SHAPE[1], SHAPE[i]);
    }
}
