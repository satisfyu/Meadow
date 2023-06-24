package net.satisfyu.meadow.block;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.SoundRegistry;

public class WarpedCheeseBlock extends CheeseBlock {
    public WarpedCheeseBlock(Settings settings, RegistrySupplier<Item> slice, boolean big) {
        super(settings, slice, big);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double) pos.getX() + 0.5;
        double e = pos.getY() + 0.7;
        double f = (double) pos.getZ() + 0.5;
        if (random.nextDouble() < 0.3) {
            world.playSound(d, e, f, SoundEvents.BLOCK_SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0f, 0.2f, false);
        }
        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.0 : h;
        double j = random.nextDouble() * 9.0 / 16.0;
        double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.0 : h;
        world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
    }
}
