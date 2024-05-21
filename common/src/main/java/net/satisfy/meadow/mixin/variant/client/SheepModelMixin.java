package net.satisfy.meadow.mixin.variant.client;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SheepModel.class)
public class SheepModelMixin {

    @Inject(method = "createBodyLayer", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void onCreateBodyLayer(CallbackInfoReturnable<LayerDefinition> ci, MeshDefinition meshDefinition, PartDefinition partDefinition) {
        PartDefinition head = partDefinition.getChild("head");

        PartDefinition horn_right = head.addOrReplaceChild("horn_right", CubeListBuilder.create(), PartPose.offset(7.0F, 0.0F, 1.0F));
        horn_right.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(27, 0).mirror().addBox(-25.0F, 2.0F, -13.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(27, 0).mirror().addBox(-25.0F, 4.0F, -13.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(27, 0).mirror().addBox(-25.0F, 3.0F, -13.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 18.0F, 8.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition horn_left = head.addOrReplaceChild("horn_left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
        horn_left.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(27, 0).mirror().addBox(-25.0F, 2.0F, -13.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(27, 0).mirror().addBox(-25.0F, 4.0F, -13.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(27, 0).mirror().addBox(-25.0F, 3.0F, -13.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 18.0F, 8.0F, 0.0F, 0.0F, 1.5708F));
    }
}
