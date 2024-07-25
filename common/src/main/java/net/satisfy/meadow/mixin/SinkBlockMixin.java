package net.satisfy.meadow.mixin;

import de.cristelknight.doapi.common.block.SinkBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.satisfy.meadow.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SinkBlock.class)
public abstract class SinkBlockMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void onUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();

        if (!world.isClientSide && state.getValue(SinkBlock.FILLED) && item == ObjectRegistry.WOODEN_BUCKET.get()) {
            world.setBlock(pos, state.setValue(SinkBlock.FILLED, false), 3);
            world.playSound(null, pos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!player.isCreative()) {
                itemStack.shrink(1);
                player.addItem(new ItemStack(ObjectRegistry.WOODEN_WATER_BUCKET.get()));
            }

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
