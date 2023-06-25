package net.satisfyu.meadow.block;

import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.util.WoodenCauldronBehavior;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class WoodenPowderSnowCauldronBlock extends LeveledCauldronBlock {
    public WoodenPowderSnowCauldronBlock(AbstractBlock.Settings settings, Predicate<Biome.Precipitation> predicate, Map<Item, CauldronBehavior> map) {
        super(settings, predicate, map);
    }

    protected void onFireCollision(BlockState state, World world, BlockPos pos) {
        decrementFluidLevel(ObjectRegistry.WOODEN_WATER_CAULDRON.get().getDefaultState().with(LEVEL, state.get(LEVEL)), world, pos);
    }


}
