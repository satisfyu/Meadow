package net.satisfyu.meadow.block.custom;

import net.minecraft.block.*;
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
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.chair.ChairEntity;
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
        if(world.isClient) return ActionResult.PASS;
        if(player.isSneaking()) return ActionResult.PASS;
        if(ChairUtil.isPlayerSitting(player)) return ActionResult.PASS;
        if(hit.getSide() == Direction.DOWN) return ActionResult.PASS;
        BlockPos hitPos = hit.getBlockPos();
        if(!ChairUtil.isOccupied(world, hitPos) && player.getStackInHand(hand).isEmpty()) {
            ChairEntity chair = ModEntities.CHAIR.create(world);
            float yaw = state.get(FACING).asRotation();
            chair.refreshPositionAndAngles(hitPos.getX() + 0.5D, hitPos.getY() + 0.25D, hitPos.getZ() + 0.5D, yaw, 0);
            if(ChairUtil.addChairEntity(world, hitPos, chair, player.getBlockPos())) {
                world.spawnEntity(chair);
                player.startRiding(chair);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(!world.isClient) {
            ChairEntity entity = ChairUtil.getChairEntity(world, pos);
            if(entity != null) {
                ChairUtil.removeChairEntity(world, pos);
                entity.removeAllPassengers();
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        boolean isX = state.get(FACING).getAxis() == Direction.Axis.X;

        Direction direction = state.get(FACING);


        if (state.get(CHEST_TYPE) == ChestType.SINGLE) {
            /*
            VoxelShape leg1YMini = Block.createCuboidShape(1.0, 0.0, 1.0, 5.0, 4.0, 15.0);
            VoxelShape leg2YMini = Block.createCuboidShape(11.0, 0.0, 1.0, 15.0, 4.0, 15.0);

            VoxelShape unionY = VoxelShapes.union(leg1YMini, leg2YMini);

            VoxelShape leg1XMini = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 4.0, 5.0);
            VoxelShape leg2XMini = Block.createCuboidShape(1.0, 0.0, 11.0, 15.0, 4.0, 15.0);

            VoxelShape unionX = VoxelShapes.union(leg1XMini, leg2XMini);
             */

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
