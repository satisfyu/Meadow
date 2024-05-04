package net.satisfyu.meadow.block.storage;

import de.cristelknight.doapi.common.block.StorageBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.registry.StorageTypeRegistry;
import net.satisfyu.meadow.registry.TagRegistry;

@SuppressWarnings("deprecation")
public class CheeseRackBlock extends StorageBlock {

    public CheeseRackBlock(Properties settings) {
        super(settings);
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public ResourceLocation type() {
        return StorageTypeRegistry.CHEESE_RACK;
    }

    @Override
    public Direction[] unAllowedDirections() {
        return new Direction[]{Direction.UP, Direction.DOWN};
    }

    @Override
    public boolean canInsertStack(ItemStack itemStack) {
        return itemStack.is(TagRegistry.CHEESE_BLOCKS);
    }

    @Override
    public int getSection(Float x, Float y) {
        return y > 0.5 ? 1 : 0;
    }

    @Override
    public SoundEvent getRemoveSound(Level level, BlockPos blockPos, Player player, int i) {
        return SoundEvents.WOOL_BREAK;
    }

    @Override
    public SoundEvent getAddSound(Level level, BlockPos blockPos, Player player, int i) {
        return SoundEvents.WOOL_PLACE;
    }
}

