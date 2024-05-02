package net.satisfyu.meadow.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.registry.BlockEntityRegistry;

public class WheelBarrowBlockEntity extends FlowerPotBlockEntity {
    public WheelBarrowBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.WHEEL_BARROW_BLOCK_ENTITY.get(), pos, state);
        setFlower(null);
    }
}