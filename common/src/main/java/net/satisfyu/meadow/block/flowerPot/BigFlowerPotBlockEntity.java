package net.satisfyu.meadow.block.flowerPot;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.block.ModBlockEntities;

public class BigFlowerPotBlockEntity extends ModFlowerPotBlockEntity {
	public BigFlowerPotBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BIG_FLOWER_POT_BLOCK_ENTITY.get(), pos, state);
	}
}