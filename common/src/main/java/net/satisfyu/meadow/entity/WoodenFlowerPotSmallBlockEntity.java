package net.satisfyu.meadow.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.registry.BlockEntityRegistry;

public class WoodenFlowerPotSmallBlockEntity extends FlowerPotBlockEntity {
    public WoodenFlowerPotSmallBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.WOODEN_FLOWER_POT_BIG.get(), pos, state);
    }
}