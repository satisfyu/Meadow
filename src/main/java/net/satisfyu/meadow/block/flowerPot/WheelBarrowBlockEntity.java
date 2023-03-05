package net.satisfyu.meadow.block.flowerPot;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.entity.ModEntities;

public class WheelBarrowBlockEntity extends ModFlowerPotBlockEntity {
	public WheelBarrowBlockEntity(BlockPos pos, BlockState state) {
		super(ModEntities.WHEEL_BARROW_BLOCK_ENTITY, pos, state);
		setFlower(null);
	}
}