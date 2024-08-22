package net.satisfy.meadow.neoforge.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.satisfy.meadow.entity.var.SheepVar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public class SheepMixin {
    @Unique
    Sheep meadow$sheepEntity = (Sheep) (Object)this;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void meadow$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir){
        if(meadow$sheepEntity.isSheared() && meadow$sheepEntity.readyForShearing()){
            meadow$setSheared();
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Unique
    private ItemLike meadow$safeGetWoolVariant() {
        SheepVar variant = SheepVar.getVariant(meadow$sheepEntity);
        if (variant != null) {
            ItemLike wool = variant.getWool();
            if (wool != null) {
                return wool;
            }
        }
        return Items.WHITE_WOOL;
    }

    @Unique
    private void meadow$setSheared(){
        meadow$sheepEntity.level().playSound(null, meadow$sheepEntity, SoundEvents.SHEEP_SHEAR, SoundSource.PLAYERS, 1.0f, 1.0f);
        meadow$sheepEntity.setSheared(true);

        int i = 1 + meadow$sheepEntity.getRandom().nextInt(3);
        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = meadow$sheepEntity.spawnAtLocation(meadow$safeGetWoolVariant(), 0);
            if (itemEntity == null) continue;
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((meadow$sheepEntity.getRandom().nextFloat() - meadow$sheepEntity.getRandom().nextFloat()) * 0.1f, meadow$sheepEntity.getRandom().nextFloat() * 0.05f, (meadow$sheepEntity.getRandom().nextFloat() - meadow$sheepEntity.getRandom().nextFloat()) * 0.1f));
        }
    }
}
