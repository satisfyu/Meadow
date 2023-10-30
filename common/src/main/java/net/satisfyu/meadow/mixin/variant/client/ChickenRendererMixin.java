package net.satisfyu.meadow.mixin.variant.client;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;
import net.satisfyu.meadow.config.MeadowConfig;
import net.satisfyu.meadow.entity.chicken.ChickenVar;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenRenderer.class)
public class ChickenRendererMixin {

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Chicken;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void onGetTexture(Chicken chicken, CallbackInfoReturnable<ResourceLocation> cir) {
        if(!MeadowConfig.DEFAULT.getConfig().renderCustomEntityTextures()) return;
        ChickenVar var = ChickenVar.getVariant(chicken);
        if(var.equals(ChickenVar.DEFAULT)) return;
        cir.setReturnValue(new MeadowIdentifier(String.format("textures/entity/chicken/meadow_%s.png", var.getSerializedName())));
    }
}
