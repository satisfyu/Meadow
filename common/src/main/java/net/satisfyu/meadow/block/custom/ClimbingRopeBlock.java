package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.List;

public class ClimbingRopeBlock extends Block {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(7, 0, 7, 9, 16, 9);

    public ClimbingRopeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(!world.isClient()){
            BlockState blockState = world.getBlockState(pos.up());
            if (blockState.isOf(ObjectRegistry.CLIMBING_ROPE.get()) || blockState.isOf(ObjectRegistry.CLIMBING_ROPE_TOPMOUNT.get())) {
                return state;
            }
            world.breakBlock(pos, true);
            return world.getBlockState(pos);
        }
        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.rope.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}
