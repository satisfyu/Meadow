package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.EffectRegistry;

public class StoveBlockWoodBlockEntity extends BlockEntity {
    public StoveBlockWoodBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.STOVE_BLOCK_WOOD_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        world.getEntitiesOfClass(Player.class, new AABB(pos).inflate(12F), player -> true).forEach(player -> player.addEffect(new MobEffectInstance(EffectRegistry.COZY_HOME.get(), 200, 0, true, false, true)));
    }
}