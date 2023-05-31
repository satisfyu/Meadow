package net.satisfyu.meadow.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.LeveledCauldronBlock.LEVEL;

@Mixin(LeveledCauldronBlock.class)
public abstract class FluidDecrementMixin {

    @Inject(method = "decrementFluidLevel", at = @At(value = "HEAD"), cancellable = true)
    private static void register(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        int i = state.get(LEVEL) - 1;
        BlockState blockState = i == 0 ? state.getBlock().equals(ObjectRegistry.WOODEN_WATER_CAULDRON) ? ObjectRegistry.WOODEN_CAULDRON.get().getDefaultState() : Blocks.CAULDRON.getDefaultState() : state.with(LEVEL, i);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        ci.cancel();
    }
}
