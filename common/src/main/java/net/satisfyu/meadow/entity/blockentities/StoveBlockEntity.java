package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import org.jetbrains.annotations.NotNull;

public class StoveBlockEntity extends SmokerBlockEntity {
    public StoveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return BlockEntityRegistry.STOVE_BLOCK_ENTITY.get();
    }
}