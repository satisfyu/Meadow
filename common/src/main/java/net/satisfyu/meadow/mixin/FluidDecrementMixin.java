package net.satisfyu.meadow.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.LeveledCauldronBlock.LEVEL;

@Mixin(LeveledCauldronBlock.class)
public class FluidDecrementMixin {

    @Inject(method = "decrementFluidLevel", at = @At(value = "HEAD"), cancellable = true)
    private static void register(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        Block b = state.getBlock();
        if(b.equals(ObjectRegistry.WOODEN_WATER_CAULDRON.get())){
            int i = state.get(LEVEL) - 1;
            BlockState blockState = i == 0 ? ObjectRegistry.WOODEN_CAULDRON.get().getDefaultState() : state.with(LEVEL, i);

            world.setBlockState(pos, blockState);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
            ci.cancel();
        }
    }

    @Inject(method = "onEntityCollision", at = @At(value = "HEAD"), cancellable = true)
    private void register(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        Object o = this;
        LeveledCauldronBlock block = (LeveledCauldronBlock) o;

        if (!world.isClient && block.isEntityTouchingFluid(state, pos, entity)) {
            if(entity.isOnFire()) return;
            if(block instanceof PowderSnowCauldronBlock){
                Blocks.POWDER_SNOW.onEntityCollision(state, world, pos, entity);
            }
        }
    }

}
