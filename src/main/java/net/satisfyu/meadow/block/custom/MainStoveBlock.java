package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MainStoveBlock extends HFacingBlock {

    public static final BooleanProperty CONNECTED_UP = BooleanProperty.of("connected_up");

    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.of("connected_down");

    public static final VoxelShape SHAPE_BIG = VoxelShapes.union(TiledBench.SHAPE, Block.createCuboidShape(0, 2, 0, 16, 16, 16));


    public MainStoveBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CONNECTED_DOWN, false).with(CONNECTED_UP, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(CONNECTED_DOWN)) return super.getOutlineShape(state, world, pos, context);
        return SHAPE_BIG;
    }

    public boolean isConnected(BlockState state){
        return state.get(CONNECTED_DOWN) || state.get(CONNECTED_UP);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        if(isBlockStove(ctx, Direction.UP)){
            return this.getDefaultState().with(CONNECTED_UP, true).with(FACING, ctx.getPlayerFacing().getOpposite());
        }
        else if(isBlockStove(ctx, Direction.DOWN)){
            return this.getDefaultState().with(CONNECTED_DOWN, true).with(FACING, ctx.getPlayerFacing().getOpposite());
        }

        return super.getPlacementState(ctx);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(!world.isClient()){
            if(direction == Direction.DOWN || direction == Direction.UP) {
                if(!isConnected(state)) {
                    if (isBlockStove(neighborState)) return state.with(direction == Direction.UP ? CONNECTED_UP : CONNECTED_DOWN, true);
                }
                else {
                    if(!isBlockStove(neighborState)) return state.with(direction == Direction.UP ? CONNECTED_UP : CONNECTED_DOWN, false);
                }
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    /*
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(!world.isClient()){

            Meadow.LOGGER.error(direction.asString());

            if(direction == Direction.DOWN || direction == Direction.UP) {
                boolean connectedDown = state.get(CONNECTED_DOWN);
                boolean connectedUp = state.get(CONNECTED_UP);


                if(direction == Direction.DOWN){
                    if(connectedDown){
                        return state.with(CONNECTED_DOWN, false);
                    }
                    else {
                        if(!connectedUp && isBlockStove(neighborState)) state.with(CONNECTED_DOWN, true);
                    }
                }
                else {
                    if(connectedUp){
                        return state.with(CONNECTED_UP, false);
                    }
                    else {
                        if(!connectedDown && isBlockStove(neighborState)) state.with(CONNECTED_UP, true);
                    }
                }


            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
     */

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CONNECTED_DOWN);
        builder.add(CONNECTED_UP);
    }


    public boolean isBlockStove(ItemPlacementContext ctx, Direction direction){
        return isBlockStove(ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction)));
    }

    public boolean isBlockStove(BlockState blockState){
        return isBlockStove(blockState.getBlock());
    }

    public boolean isBlockStove(Block block){
        return block instanceof StoveBlock || block instanceof MainStoveBlock;
    }
}
