package net.satisfyu.meadow.block.woodenCauldren;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
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

import java.util.List;

public class WoodenCauldronBlock
        extends AbstractCauldronBlock {

    public WoodenCauldronBlock(Settings settings) {
        super(settings, WoodenCauldronBehavior.EMPTY_CAULDRON_BEHAVIOR);
    }

    @Override
    public boolean isFull(BlockState state) {
        return false;
    }

    protected static boolean canFillWithPrecipitation(World world, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < 0.05f;
        }
        if (precipitation == Biome.Precipitation.SNOW) {
            return world.getRandom().nextFloat() < 0.1f;
        }
        return false;
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (!WoodenCauldronBlock.canFillWithPrecipitation(world, precipitation)) {
            return;
        }
        if (precipitation == Biome.Precipitation.RAIN) {
            world.setBlockState(pos, ObjectRegistry.WOODEN_WATER_CAULDRON.get().getDefaultState());
            world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
        } else if (precipitation == Biome.Precipitation.SNOW) {
            world.setBlockState(pos, Blocks.POWDER_SNOW_CAULDRON.getDefaultState());
            world.emitGameEvent(null, GameEvent.BLOCK_CHANGE, pos);
        }
    }

    @Override
    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return true;
    }

    @Override
    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            BlockState blockState = ObjectRegistry.WOODEN_WATER_CAULDRON.get().getDefaultState();
            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.woodencauldron.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}

