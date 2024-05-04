package net.satisfyu.meadow.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class OilLantern extends LanternBlock {
    protected static final VoxelShape STANDING_SHAPE = createStandingLanternShape();

    public static VoxelShape createHangingLanternShape() {
        VoxelShape baseShape = Shapes.empty();
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.375, 0.375, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.25, 0.3125, 0.5, 0.3125, 0.75, 0.5), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.6875, 0.3125, 0.5, 0.75, 0.75, 0.5), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.5, 0.3125, 0.6875, 0.5, 0.75, 0.75), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.5, 0.3125, 0.25, 0.5, 0.75, 0.3125), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.3125, 0.1875, 0.3125, 0.6875, 0.375, 0.6875), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.3125, 0.6875, 0.3125, 0.6875, 0.8125, 0.6875), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.375, 0.8125, 0.375, 0.625, 1, 0.625), BooleanOp.OR);
        return baseShape;
    }

    public static VoxelShape createStandingLanternShape() {
        VoxelShape baseShape = Shapes.empty();
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.1875, 0.0625, 0.5, 0.3125, 0.5625, 0.5), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.5, 0.0625, 0.6875, 0.5, 0.5625, 0.8125), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.1875, 0.6875), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.375, 0.1875, 0.375, 0.625, 0.5, 0.625), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.3125, 0.5, 0.3125, 0.6875, 0.625, 0.6875), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.5, 0.0625, 0.1875, 0.5, 0.5625, 0.3125), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.6875, 0.0625, 0.5, 0.8125, 0.5625, 0.5), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.375, 0.625, 0.375, 0.625, 0.6875, 0.625), BooleanOp.OR);
        baseShape = Shapes.joinUnoptimized(baseShape, Shapes.box(0.40625, 0.6875, 0.40625, 0.59375, 0.8125, 0.59375), BooleanOp.OR);
        return baseShape;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double d = (double) pos.getX() + 0.5;
        double e = pos.getY() + 0.7;
        double f = (double) pos.getZ() + 0.5;
        if (random.nextDouble() < 0.3) {
            world.playLocalSound(d, e, f, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 2.0f, 1.0f, false);
        }
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = random.nextDouble() * 9.0 / 16.0;
        double j = random.nextDouble() * 0.6 - 0.3;
        world.addParticle(ParticleTypes.SMOKE, d + h, e + i, f + j, 0.0, 0.0, 0.0);
    }

    public OilLantern(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(HANGING, false).setValue(WATERLOGGED, false));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(HANGING) ? createHangingLanternShape() : STANDING_SHAPE;
    }
}
