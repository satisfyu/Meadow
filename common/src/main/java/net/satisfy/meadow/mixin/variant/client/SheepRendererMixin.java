package net.satisfy.meadow.mixin.variant.client;

import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.satisfy.meadow.entity.var.SheepVar;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepRenderer.class)
public class SheepRendererMixin {

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Sheep;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void onGetTexture(Sheep sheep, CallbackInfoReturnable<ResourceLocation> cir) {
        SheepVar var = SheepVar.getVariant(sheep);
        if(var.equals(SheepVar.DEFAULT)) return;
        cir.setReturnValue(MeadowIdentifier.of(String.format("textures/entity/sheep/%s_sheep.png", var.getSerializedName())));
    }
}
