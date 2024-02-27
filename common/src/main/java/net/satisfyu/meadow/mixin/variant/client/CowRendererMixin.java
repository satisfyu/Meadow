package net.satisfyu.meadow.mixin.variant.client;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cow;
import net.satisfyu.meadow.entity.cow.CowVar;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CowRenderer.class)
public class CowRendererMixin {

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Cow;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void onGetTexture(Cow cow, CallbackInfoReturnable<ResourceLocation> cir) {
        CowVar var = CowVar.getVariant(cow);
        if(var.equals(CowVar.DEFAULT)) return;
        cir.setReturnValue(new MeadowIdentifier(String.format("textures/entity/cow/%s_cow.png", var.getSerializedName())));
    }
}
