package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

//Code From https://github.com/starfish-studios/AnotherFurniture
public class TableBlock extends Block implements Waterloggable {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LEG1 = BooleanProperty.of("leg_1");
    public static final BooleanProperty LEG2 = BooleanProperty.of("leg_2");
    public static final BooleanProperty LEG3 = BooleanProperty.of("leg_3");
    public static final BooleanProperty LEG4 = BooleanProperty.of("leg_4");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    //exists solely to update corner blocks
    public static final BooleanProperty UPDATE = BooleanProperty.of("update");

    protected static final VoxelShape TOP = Block.createCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape LEG_1 = Block.createCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 14.0D, 2.0D);
    protected static final VoxelShape LEG_2 = Block.createCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_3 = Block.createCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 14.0D, 16.0D);
    protected static final VoxelShape LEG_4 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 14.0D, 2.0D);
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            TOP, VoxelShapes.union(TOP, LEG_1), VoxelShapes.union(TOP, LEG_2), VoxelShapes.union(TOP, LEG_1, LEG_2),
            VoxelShapes.union(TOP, LEG_3), VoxelShapes.union(TOP, LEG_1, LEG_3), VoxelShapes.union(TOP, LEG_2, LEG_3),
            VoxelShapes.union(TOP, LEG_1, LEG_2, LEG_3), VoxelShapes.union(TOP, LEG_4), VoxelShapes.union(TOP, LEG_1, LEG_4),
            VoxelShapes.union(TOP, LEG_2, LEG_4), VoxelShapes.union(TOP, LEG_1, LEG_2, LEG_4), VoxelShapes.union(TOP, LEG_3, LEG_4),
            VoxelShapes.union(TOP, LEG_1, LEG_3, LEG_4), VoxelShapes.union(TOP, LEG_2, LEG_3, LEG_4), VoxelShapes.union(TOP, LEG_1, LEG_2, LEG_3, LEG_4)
    };

    public TableBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(LEG1, true).with(LEG2, true).with(LEG3, true).with(LEG4, true).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int shape = 0;
        if (state.get(LEG1)) shape += 1;
        if (state.get(LEG2)) shape += 2;
        if (state.get(LEG3)) shape += 4;
        if (state.get(LEG4)) shape += 8;
        return SHAPES[shape];
    }



    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean waterlogged = ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerFacing().getOpposite())
                .with(WATERLOGGED, waterlogged);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction direction, BlockState neighborState, WorldAccess pLevel, BlockPos pCurrentPos, BlockPos neighborPos) {
        if (pState.get(WATERLOGGED)) {
            pLevel.createAndScheduleFluidTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickRate(pLevel));
        }
        boolean n = pLevel.getBlockState(pCurrentPos.north()).getBlock() instanceof TableBlock;
        boolean e = pLevel.getBlockState(pCurrentPos.east()).getBlock() instanceof TableBlock;
        boolean s = pLevel.getBlockState(pCurrentPos.south()).getBlock() instanceof TableBlock;
        boolean w = pLevel.getBlockState(pCurrentPos.west()).getBlock() instanceof TableBlock;
        boolean leg1 = (!n && !e) || (n && e && !(pLevel.getBlockState(pCurrentPos.north().east()).getBlock() instanceof TableBlock));
        boolean leg2 = (!e && !s) || (e && s && !(pLevel.getBlockState(pCurrentPos.south().east()).getBlock() instanceof TableBlock));
        boolean leg3 = (!s && !w) || (s && w && !(pLevel.getBlockState(pCurrentPos.south().west()).getBlock() instanceof TableBlock));
        boolean leg4 = (!n && !w) || (n && w && !(pLevel.getBlockState(pCurrentPos.north().west()).getBlock() instanceof TableBlock));
        boolean update = ((n ? 1 : 0) + (e ? 1 : 0) + (s ? 1 : 0) + (w ? 1 : 0)) % 2 == 0;
        return pState.with(LEG1, leg1).with(LEG2, leg2).with(LEG3, leg3).with(LEG4, leg4).with(UPDATE, update);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(pState);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEG1, LEG2, LEG3, LEG4, UPDATE, WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState pState, BlockRotation pRotation) {
        boolean leg1 = pState.get(LEG1);
        boolean leg2 = pState.get(LEG2);
        boolean leg3 = pState.get(LEG3);
        boolean leg4 = pState.get(LEG4);
        return switch(pRotation) {
            case NONE -> pState.with(FACING, pRotation.rotate(pState.get(FACING)));
            case CLOCKWISE_90 -> pState.with(FACING, pRotation.rotate(pState.get(FACING))).with(LEG1, leg4).with(LEG2, leg1).with(LEG3, leg2).with(LEG4, leg3);
            case CLOCKWISE_180 -> pState.with(FACING, pRotation.rotate(pState.get(FACING))).with(LEG1, leg3).with(LEG2, leg4).with(LEG3, leg1).with(LEG4, leg2);
            case COUNTERCLOCKWISE_90 -> pState.with(FACING, pRotation.rotate(pState.get(FACING))).with(LEG1, leg2).with(LEG2, leg3).with(LEG3, leg4).with(LEG4, leg1);
        };
    }


    @Override
    public BlockState mirror(BlockState pState, BlockMirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.get(FACING)));
    }


    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
