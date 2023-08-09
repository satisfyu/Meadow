package net.satisfyu.meadow.mixin.variant;

import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.satisfyu.meadow.entity.sheep.SheepVar;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SheepFurLayer.class)
public class SheepFurLayerMixin {

    @ModifyArgs(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/Sheep;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/SheepFurLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFFFFF)V"))
    private void onGetTexture(Args args) {
        LivingEntity entity = args.get(6);

        if(entity instanceof Sheep sheep){
            SheepVar var = SheepVar.getVariant(sheep);
            if(var.equals(SheepVar.DEFAULT) || var.equals(SheepVar.HORNED)) return;
            args.set(2, new MeadowIdentifier(String.format("textures/entity/sheep/%s_sheep_fur.png", var.getSerializedName())));
        }
    }
}
