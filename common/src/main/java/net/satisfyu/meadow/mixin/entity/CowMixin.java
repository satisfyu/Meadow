package net.satisfyu.meadow.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CowEntity.class)
public abstract class CowMixin extends AnimalEntity {


    protected CowMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "interactMob",
            at = @At(value = "HEAD"),
            cancellable = true)
//TODO
    private void addServerPackFinders(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        boolean bl;
        if (((bl = itemStack.isOf(ObjectRegistry.WOODEN_BUCKET.get())) && !this.isBaby())) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack milkBucket = bl ? ObjectRegistry.WOODEN_MILK_BUCKET.get().getDefaultStack() : ItemStack.EMPTY;
            ItemStack updatedStack = ItemUsage.exchangeStack(itemStack, player, milkBucket);
            player.setStackInHand(hand, updatedStack);
            cir.setReturnValue(ActionResult.success(this.world.isClient));
        }
    }
}


