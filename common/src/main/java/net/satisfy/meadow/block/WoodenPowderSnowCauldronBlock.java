package net.satisfy.meadow.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.meadow.registry.ObjectRegistry;

import java.util.Map;
import java.util.function.Predicate;

public class WoodenPowderSnowCauldronBlock extends LayeredCauldronBlock {

    public WoodenPowderSnowCauldronBlock(Biome.Precipitation precipitation, CauldronInteraction.InteractionMap interactionMap, Properties properties) {
        super(precipitation, interactionMap, properties);
    }

    protected void handleEntityOnFireInside(BlockState state, Level world, BlockPos pos) {
        lowerFillLevel(ObjectRegistry.WOODEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL)), world, pos);
    }


}
