package net.satisfyu.meadow.block.flowerPot;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.satisfyu.meadow.block.ModBlockEntities;

public class WheelBarrowBlockEntity extends ModFlowerPotBlockEntity {
	public WheelBarrowBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.WHEEL_BARROW_BLOCK_ENTITY.get(), pos, state);
		setFlower(null);
	}
}