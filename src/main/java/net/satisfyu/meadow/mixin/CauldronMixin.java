package net.satisfyu.meadow.mixin;

import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(CauldronBehavior.class)
public interface CauldronMixin {


    @Inject(method = "registerBehavior", at = @At("HEAD"))
    private static void modify(CallbackInfo ci) {

    }

    @Inject(method = "registerBucketBehavior", at = @At("HEAD"))
    private static void modify(Map<Item, CauldronBehavior> behavior, CallbackInfo ci) {

    }



}