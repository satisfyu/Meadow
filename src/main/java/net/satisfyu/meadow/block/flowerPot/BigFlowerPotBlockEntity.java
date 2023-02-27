package net.satisfyu.meadow.block.flowerPot;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.block.flowerPot.ModFlowerPotBlockEntity;
import net.satisfyu.meadow.entity.ModEntities;

public class BigFlowerPotBlockEntity extends ModFlowerPotBlockEntity {
	public BigFlowerPotBlockEntity(BlockPos pos, BlockState state) {
		super(ModEntities.BIG_FLOWER_POT_BLOCK_ENTITY, pos, state);
	}
}