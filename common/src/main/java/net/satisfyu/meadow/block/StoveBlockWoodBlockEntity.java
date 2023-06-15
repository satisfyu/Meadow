package net.satisfyu.meadow.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.EffectRegistry;

public class StoveBlockWoodBlockEntity extends BlockEntity {
	public StoveBlockWoodBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityRegistry.STOVE_BLOCK_WOOD_BLOCK_ENTITY.get(), pos, state);
	}
	
	public static void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
		world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(12F), player -> true).forEach(player -> {
			player.addStatusEffect(new StatusEffectInstance(EffectRegistry.COZY_HOME.get(), 200, 0, true, false, true));
		});
	}
}