package net.satisfyu.meadow.world.feature.custom;


import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.satisfyu.meadow.block.ModBlocks;


public class CobbledLimestoneRock extends Feature<SingleStateFeatureConfig> {

    public CobbledLimestoneRock() {
        super(SingleStateFeatureConfig.CODEC);
    }


    @Override
    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        Random random = context.getRandom();
        SingleStateFeatureConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        int radius = random.nextBetween(3, 7);
        BlockPos pos = context.getOrigin();
        for(; pos.getY() > world.getBottomY() + radius; pos = pos.down()) {
            if(!world.isAir(pos)) {
                if(world.getBlockState(pos).isIn(BlockTags.DIRT)) {
                    break;
                }
            }
        }
        if(pos.getY() <= world.getBottomY() + radius) {
            return false;
        }
        else {
            //boolean wasInit = false;
            //boolean gerade = false;
            for(int i = 0; i < 1; ++i) {
                int j = random.nextInt(radius);
                int k = random.nextInt(radius);
                int l = random.nextInt(radius);
                float f = (float) (j + k + l) * 0.333F + 0.5F;

                for(BlockPos pos2 : BlockPos.iterate(pos.add(-j, -k, -l), pos.add(j, k, l))) {
                    if(pos2.getSquaredDistance(pos) <= (double) (f * f)) {
                        this.setBlockState(world, pos2, config.state);
                        /*
                        if(!wasInit){
                            gerade = pos2.getY() % 2 == 0;
                            wasInit = true;
                        }
                        if(!world.getBlockState(pos2).isOf(ModBlocks.COBBLED_LIMESTONE_SLAB)) this.setBlockState(world, pos2, config.state);
                        setSlab(world, pos2, gerade);

                         */
                    }
                }
                pos = pos.add(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
            }
            return true;
        }
    }

    private void setSlab(StructureWorldAccess world, BlockPos pos2, boolean gerade){
        if((pos2.getY() % 2 == 0) == gerade) {
            if (world.getBlockState(pos2.up()).isOf(Blocks.AIR)) {
                this.setBlockState(world, pos2.up(), ModBlocks.COBBLED_LIMESTONE_SLAB.getDefaultState());
            }
        }
    }
}