package net.satisfyu.meadow.entity.bear;

import net.minecraft.client.model.PolarBearModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BrownBearModel extends PolarBearModel<BrownBear> {
    public BrownBearModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -24.0F, -7.0F, 14.0F, 25.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.0F, 12.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 37).addBox(-4.5F, -3.0F, -3.0F, 9.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(50, 34).mirror().addBox(-2.5F, 0.0F, -6.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 37).addBox(-5.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).mirror().addBox(3.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 2), PartPose.offset(0.0F, 10.0F, -16.0F));


        modelPartData.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(50, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 14.0F, 6.0F));

        modelPartData.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(33, 37).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 14.0F, 6.0F));

        modelPartData.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(51, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 14.0F, -8.0F));

        modelPartData.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(51, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 14.0F, -8.0F));
		/*
		ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(50, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F);
		modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-4.5F, 14.0F, 6.0F));
		modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(4.5F, 14.0F, 6.0F));
		ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(50, 40).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F);
		modelPartData.addChild("right_front_leg", modelPartBuilder2, ModelTransform.pivot(-3.5F, 14.0F, -8.0F));
		modelPartData.addChild("left_front_leg", modelPartBuilder2, ModelTransform.pivot(3.5F, 14.0F, -8.0F));

		 */

        return LayerDefinition.create(modelData, 128, 128);
    }
}