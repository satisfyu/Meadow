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
import net.satisfyu.meadow.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StoveBlock extends FacingBlock {

    public static final BooleanProperty CONNECTED = BooleanProperty.of("connected");

    public static final VoxelShape SHAPE_BIG = VoxelShapes.union(TiledBench.SHAPE, Block.createCuboidShape(0, 2, 0, 16, 16, 16));

    private final Direction directionToCheck;

    public StoveBlock(Settings settings, Direction directionToCheck) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CONNECTED, false));
        this.directionToCheck = directionToCheck;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(directionToCheck == Direction.DOWN && state.get(CONNECTED)) return super.getOutlineShape(state, world, pos, context);
        return SHAPE_BIG;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        List<Block> block = getBlocksToCheck();
        if(!block.isEmpty()){
            if(block.contains(ctx.getWorld().getBlockState(ctx.getBlockPos().offset(directionToCheck)).getBlock())){
                return this.getDefaultState().with(CONNECTED, true).with(FACING, ctx.getPlayerFacing().getOpposite());
            }
        }
        return super.getPlacementState(ctx);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        List<Block> block = getBlocksToCheck();
        if(!world.isClient() && !block.isEmpty()){
            if(direction == directionToCheck){
                boolean connected = state.get(CONNECTED);
                if(!connected) {
                    if (block.contains(neighborState.getBlock())) return state.with(CONNECTED, true);
                }
                else if(!block.contains(neighborState.getBlock())) return state.with(CONNECTED, false);

            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CONNECTED);
    }

    private List<Block> getBlocksToCheck(){
        if(directionToCheck == Direction.UP){
            return List.of(ModBlocks.STOVE);
        }
        else if(directionToCheck == Direction.DOWN){
            return List.of(ModBlocks.STOVE_WOOD, ModBlocks.STOVE_LID);
        }
        else return List.of();
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.uc.tooltip").formatted(Formatting.ITALIC, Formatting.DARK_RED));
    }
}
