package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.List;

public class OilLantern extends LanternBlock{

    public static final IntProperty LUMINANCE = IntProperty.of("luminance", 12, 15);
    protected static final VoxelShape HANGING_SHAPE = makeShapeHS();
    protected static final VoxelShape STANDING_SHAPE = makeShapeSS();

    public static VoxelShape makeShapeHS() {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.4375, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.4375, 0.375, 0.625, 0.5625, 0.625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.359375, 0.5, 0.359375, 0.640625, 0.5625, 0.640625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.40625, 0.625, 0.5, 0.59375, 0.875, 0.5), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5, 0.5625, 0.40625, 0.5, 0.6875, 0.59375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5, 0.8125, 0.40625, 0.5, 1, 0.59375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.265625, 0, 0.265625, 0.734375, 0.0625, 0.734375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.265625, 0.375, 0.265625, 0.734375, 0.4375, 0.734375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.640625, 0.0625, 0.296875, 0.703125, 0.375, 0.359375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.296875, 0.0625, 0.296875, 0.359375, 0.375, 0.359375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.296875, 0.0625, 0.640625, 0.359375, 0.375, 0.703125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.640625, 0.0625, 0.640625, 0.703125, 0.375, 0.703125), BooleanBiFunction.OR);
        return shape;
    }

    public static VoxelShape makeShapeSS() {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.4375, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.4375, 0.375, 0.625, 0.5625, 0.625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.359375, 0.500625, 0.359375, 0.640625, 0.563125, 0.640625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.40625, 0.5625, 0.5, 0.59375, 0.6875, 0.5), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5, 0.5625, 0.40625, 0.5, 0.6875, 0.59375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.265625, 0, 0.265625, 0.734375, 0.0625, 0.734375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.265625, 0.375625, 0.265625, 0.734375, 0.438125, 0.734375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.640625, 0.0625, 0.296875, 0.703125, 0.375, 0.359375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.296875, 0.0625, 0.296875, 0.359375, 0.375, 0.359375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.296875, 0.0625, 0.640625, 0.359375, 0.375, 0.703125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.640625, 0.0625, 0.640625, 0.703125, 0.375, 0.703125), BooleanBiFunction.OR);

        return shape;
    }


    public OilLantern(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(HANGING, false).with(WATERLOGGED, false).with(LUMINANCE, 12));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LUMINANCE, HANGING, WATERLOGGED);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, this.getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)).with(LUMINANCE, Random.create().nextBetween(12, 15)).with(HANGING, state.get(HANGING)));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.lantern.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}
