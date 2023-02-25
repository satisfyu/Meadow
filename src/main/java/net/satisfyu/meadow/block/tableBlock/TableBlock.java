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
        this.setDefaultState(((this.stateManager.getDefaultState().with(TYPE, TableType.NONE)).with(WATERLOGGED, false)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape top = Block.createCuboidShape(0.0, 13.0, 0.0, 16.0, 16.0, 16.0);
        VoxelShape north_leg = Block.createCuboidShape(1.0, 0.0, 1.0, 4.0, 13.0, 4.0);
        VoxelShape east_leg = Block.createCuboidShape(12.0, 0.0, 1.0, 15.0, 13.0, 4.0);
        VoxelShape south_leg = Block.createCuboidShape(12.0, 0.0, 12.0, 15.0, 13.0, 15.0);
        VoxelShape west_leg = Block.createCuboidShape(1.0, 0.0, 12.0, 4.0, 13.0, 15.0);

        TableType type = state.get(TYPE);

        switch (type) {
            case CENTER, NORTH_SOUTH, EAST_WEST, NORTH_EDGE, EAST_EDGE, SOUTH_EDGE, WEST_EDGE -> {
                return VoxelShapes.union(top);
            }
            case NORTH -> {
                return VoxelShapes.union(top, south_leg, west_leg);
            }
            case EAST -> {
                return VoxelShapes.union(top, north_leg, west_leg);
            }
            case SOUTH -> {
                return VoxelShapes.union(top, north_leg, east_leg);
            }
            case WEST -> {
                return VoxelShapes.union(top, east_leg, south_leg);
            }
            case NORTH_CORNER -> {
                return VoxelShapes.union(top, north_leg);
            }
            case EAST_CORNER -> {
                return VoxelShapes.union(top, east_leg);
            }
            case SOUTH_CORNER -> {
                return VoxelShapes.union(top, south_leg);
            }
            case WEST_CORNER -> {
                return VoxelShapes.union(top, west_leg);
            }
        }
        return VoxelShapes.union(top, north_leg, east_leg, south_leg, west_leg);
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
            return TableType.CENTER;
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
        } else if (!shape_north_same && shape_east_same && shape_south_same && !shape_west_same) {
            return TableType.NORTH_CORNER;
        } else if (!shape_north_same && !shape_east_same && shape_south_same && shape_west_same) {
            return TableType.EAST_CORNER;
        } else if (shape_north_same && !shape_east_same && !shape_south_same && shape_west_same) {
            return TableType.SOUTH_CORNER;
        } else if (shape_north_same && shape_east_same && !shape_south_same && !shape_west_same) {
            return TableType.WEST_CORNER;
        } else if (!shape_north_same && shape_east_same && shape_south_same && shape_west_same) {
            return TableType.NORTH_EDGE;
        } else if (shape_north_same && !shape_east_same && shape_south_same && shape_west_same) {
            return TableType.EAST_EDGE;
        } else if (shape_north_same && shape_east_same && !shape_south_same && shape_west_same) {
            return TableType.SOUTH_EDGE;
        } else if (shape_north_same && shape_east_same && shape_south_same && !shape_west_same) {
            return TableType.WEST_EDGE;
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
