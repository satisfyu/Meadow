package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.registry.BlockEntityRegistry;

public class BigFlowerPotBlockEntity extends FlowerPotBlockEntity {
	public BigFlowerPotBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.BIG_FLOWER_POT_BLOCK_ENTITY.get(), pos, state);
	}
}