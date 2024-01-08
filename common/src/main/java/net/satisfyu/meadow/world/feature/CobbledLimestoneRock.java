package net.satisfyu.meadow.world.feature;


import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.satisfyu.meadow.registry.ObjectRegistry;


public class CobbledLimestoneRock extends Feature<BlockStateConfiguration> {

    public CobbledLimestoneRock() {
        super(BlockStateConfiguration.CODEC);
    }


    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        RandomSource random = context.random();
        BlockStateConfiguration config = context.config();
        WorldGenLevel world = context.level();
        int radius = random.nextIntBetweenInclusive(3, 7);
        BlockPos pos = context.origin();
        for (; pos.getY() > world.getMinBuildHeight() + radius; pos = pos.below()) {
            if (!world.isEmptyBlock(pos)) {
                if (world.getBlockState(pos).is(BlockTags.DIRT)) {
                    break;
                }
            }
        }
        if (pos.getY() <= world.getMinBuildHeight() + radius) {
            return false;
        } else {
            for (int i = 0; i < 1; ++i) {
                int j = random.nextInt(radius);
                int k = random.nextInt(radius);
                int l = random.nextInt(radius);
                float f = (float) (j + k + l) * 0.333F + 0.5F;

                for (BlockPos pos2 : BlockPos.betweenClosed(pos.offset(-j, -k, -l), pos.offset(j, k, l))) {
                    if (pos2.distSqr(pos) <= (double) (f * f)) {
                        this.setBlock(world, pos2, config.state);
                    }
                }
                pos = pos.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
            }
            return true;
        }
    }

    private void setSlab(WorldGenLevel world, BlockPos pos2, boolean gerade) {
        if ((pos2.getY() % 2 == 0) == gerade) {
            if (world.getBlockState(pos2.above()).is(Blocks.AIR)) {
                this.setBlock(world, pos2.above(), ObjectRegistry.COBBLED_LIMESTONE_SLAB.get().defaultBlockState());
            }
        }
    }
}