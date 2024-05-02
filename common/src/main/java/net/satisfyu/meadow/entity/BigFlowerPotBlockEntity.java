package net.satisfyu.meadow.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.registry.BlockEntityRegistry;

public class BigFlowerPotBlockEntity extends FlowerPotBlockEntity {
    public BigFlowerPotBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.BIG_FLOWER_POT_BLOCK_ENTITY.get(), pos, state);
    }
}