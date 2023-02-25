package net.satisfyu.meadow.block.tableBlock;

import net.minecraft.block.*;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModProperties;
import net.satisfyu.meadow.block.windowShutter.ShutterType;
import org.jetbrains.annotations.Nullable;


public class TableBlock extends Block implements Waterloggable {

    public static final EnumProperty<TableType> TYPE = ModProperties.TABLE_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;


    public TableBlock(Settings settings) {
        super(settings);
        this.setDefaultState();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape top = Block.createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
        VoxelShape leg1 = Block.createCuboidShape(1.0, 0.0, 1.0, 4.0, 13.0, 4.0);
        VoxelShape leg2 = Block.createCuboidShape(1.0, 0.0, 12.0, 4.0, 13.0, 15.0);
        VoxelShape leg3 = Block.createCuboidShape(12.0, 0.0, 12.0, 15.0, 13.0, 15.0);
        VoxelShape leg4 = Block.createCuboidShape(12.0, 0.0, 1.0, 15.0, 13.0, 4.0);

        TableType type = state.get(TYPE);

        if (type == TableType.NONE || type == TableType.NORTH_SOUTH || type == TableType.EAST_WEST) {
            return VoxelShapes.union(top);
        }
        if (type == TableType.MIDDLE) {
            return VoxelShapes.union(top, leg1, leg2, leg3, leg4);
        }

        VoxelShape legs1;
        if (type == TableType.MIDDLE) {
            return VoxelShapes.union(top, leg1, leg2, leg3, leg4);
        }

        VoxelShape legs2;
        VoxelShape legs3;

        if((direction == Direction.NORTH && chestType == ChestType.LEFT) || (direction == Direction.SOUTH && chestType == ChestType.RIGHT)){
            legs1 = leg1;
            legs2 = leg2;
        }
        else if((direction == Direction.NORTH && chestType == ChestType.RIGHT) || (direction == Direction.SOUTH && chestType == ChestType.LEFT)){
            legs1 = leg3;
            legs2 = leg4;
        }
        else if((direction == Direction.EAST && chestType == ChestType.RIGHT) || (direction == Direction.WEST && chestType == ChestType.LEFT)){
            legs1 = leg2;
            legs2 = leg3;
        }
        else if((direction == Direction.EAST && chestType == ChestType.LEFT) || (direction == Direction.WEST && chestType == ChestType.RIGHT)){
            legs1 = leg1;
            legs2 = leg4;
        }
        else {
            Meadow.LOGGER.error("Table blockstate not correct!");
            return top;
        }
        return VoxelShapes.union(top, legs1, legs2);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockState = this.getDefaultState();

        World world = context.getWorld();
        BlockPos clickedPos = context.getBlockPos();

        blockState = blockState.with(TYPE, getType(blockState, world.getBlockState(clickedPos.north()), world.getBlockState(clickedPos.east()), world.getBlockState(clickedPos.south()), world.getBlockState(clickedPos.west())));

        return blockState.with(WATERLOGGED, world.getFluidState(clickedPos).getFluid() == Fluids.WATER);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) return;


        TableType type = getType(state, world.getBlockState(pos.north()), world.getBlockState(pos.east()), world.getBlockState(pos.south()), world.getBlockState(pos.west()));
        if (state.get(TYPE) != type) {
            state = state.with(TYPE, type);
        }
        world.setBlockState(pos, state, 3);
    }

    public TableType getType(BlockState state, BlockState north, BlockState east, BlockState south, BlockState west) {
        boolean shape_north_same = north.getBlock() == state.getBlock();
        boolean shape_east_same = east.getBlock() == state.getBlock();
        boolean shape_south_same = south.getBlock() == state.getBlock();
        boolean shape_west_same = west.getBlock() == state.getBlock();

        if (shape_north_same && shape_east_same && shape_south_same && shape_west_same) {
            return TableType.MIDDLE;
        } else if (shape_north_same && !shape_east_same && !shape_south_same && !shape_west_same) {
            return TableType.NORTH;
        } else if (!shape_north_same && shape_east_same && !shape_south_same && !shape_west_same) {
            return TableType.EAST;
        } else if (!shape_north_same && !shape_east_same && shape_south_same && !shape_west_same) {
            return TableType.SOUTH;
        } else if (!shape_north_same && !shape_east_same && !shape_south_same && shape_west_same) {
            return TableType.WEST;
        } else if (shape_north_same && !shape_east_same && shape_south_same && !shape_west_same) {
            return TableType.NORTH_SOUTH;
        } else if (!shape_north_same && shape_east_same && !shape_south_same && shape_west_same) {
            return TableType.EAST_WEST;
        } else if (shape_north_same && shape_east_same && !shape_south_same && !shape_west_same) {
            return TableType.NORTH_EAST;
        } else if (!shape_north_same && shape_east_same && shape_south_same && !shape_west_same) {
            return TableType.EAST_SOUTH;
        } else if (!shape_north_same && !shape_east_same && shape_south_same && shape_west_same) {
            return TableType.SOUTH_WEST;
        } else if (shape_north_same && !shape_east_same && !shape_south_same && shape_west_same) {
            return TableType.WEST_NORTH;
        }
        return TableType.NONE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }


}
