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
    public WoodenPowderSnowCauldronBlock(Properties settings, Predicate<Biome.Precipitation> predicate, Map<Item, CauldronInteraction> map) {
        super(settings, predicate, map);
    }

    protected void handleEntityOnFireInside(BlockState state, Level world, BlockPos pos) {
        lowerFillLevel(ObjectRegistry.WOODEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, state.getValue(LEVEL)), world, pos);
    }


}
