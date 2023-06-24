package net.satisfyu.meadow.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.SoundRegistry;

import java.util.List;

public class OilLantern extends LanternBlock {

    protected static final VoxelShape HANGING_SHAPE = makeShapeHS();
    protected static final VoxelShape STANDING_SHAPE = makeShapeSS();

    public static VoxelShape makeShapeHS() {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.625, 0.375, 0.625, 0.6875, 0.625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.34375, 0.6875, 0.34375, 0.65625, 0.75, 0.65625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.40625, 0.75, 0.5, 0.59375, 1, 0.5), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5, 0.75, 0.40625, 0.5, 1, 0.59375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.1875, 0.3125, 0.6875, 0.25, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.5625, 0.3125, 0.6875, 0.625, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.25, 0.3125, 0.6875, 0.5625, 0.375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.25, 0.3125, 0.375, 0.5625, 0.375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.25, 0.625, 0.375, 0.5625, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.25, 0.625, 0.6875, 0.5625, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.25, 0.375, 0.625, 0.5625, 0.625), BooleanBiFunction.OR);
        return shape;
    }

    public static VoxelShape makeShapeSS() {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.4375, 0.375, 0.625, 0.5, 0.625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.34375, 0.5, 0.34375, 0.65625, 0.5625, 0.65625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.40625, 0.5625, 0.5, 0.59375, 0.6875, 0.5), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5, 0.5625, 0.40625, 0.5, 0.6875, 0.59375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.0625, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.375, 0.3125, 0.6875, 0.4375, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.0625, 0.3125, 0.6875, 0.375, 0.375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.0625, 0.3125, 0.375, 0.375, 0.375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.0625, 0.625, 0.375, 0.375, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.0625, 0.625, 0.6875, 0.375, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.0625, 0.375, 0.625, 0.375, 0.625), BooleanBiFunction.OR);

        return shape;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double) pos.getX() + 0.5;
        double e = pos.getY() + 0.7;
        double f = (double) pos.getZ() + 0.5;
        if (random.nextDouble() < 0.3) {
            world.playSound(d, e, f, SoundEvents.BLOCK_CANDLE_AMBIENT, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
        }
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = random.nextDouble() * 9.0 / 16.0;
        double j = random.nextDouble() * 0.6 - 0.3;
        world.addParticle(ParticleTypes.SMOKE, d + h, e + i, f + j, 0.0, 0.0, 0.0);
    }



    public OilLantern(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(HANGING, false).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.lantern.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}
