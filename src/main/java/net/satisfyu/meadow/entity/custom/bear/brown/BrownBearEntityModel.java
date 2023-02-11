package net.satisfyu.meadow.entity.custom.bear.brown;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.PolarBearEntityModel;

public class BrownBearEntityModel extends PolarBearEntityModel<BrownBearEntity> {
	public BrownBearEntityModel(ModelPart root) {
		super(root);
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -24.0F, -7.0F, 14.0F, 25.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 9.0F, 12.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 37).cuboid(-4.5F, -3.0F, -3.0F, 9.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(50, 34).mirrored().cuboid(-2.5F, 0.0F, -6.0F, 5.0F, 4.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
		.uv(0, 37).cuboid(-5.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 37).mirrored().cuboid(3.5F, -4.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
		.uv(12, 2).cuboid(-1.5F, -3.0F, 29.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 10.0F, -16.0F));


		modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(50, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.5F, 14.0F, 6.0F));

		modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(33, 37).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 14.0F, 6.0F));

		modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(51, 17).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, 14.0F, -8.0F));

		modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(51, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, 14.0F, -8.0F));
		/*
		ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(50, 22).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F);
		modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-4.5F, 14.0F, 6.0F));
		modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(4.5F, 14.0F, 6.0F));
		ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(50, 40).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F);
		modelPartData.addChild("right_front_leg", modelPartBuilder2, ModelTransform.pivot(-3.5F, 14.0F, -8.0F));
		modelPartData.addChild("left_front_leg", modelPartBuilder2, ModelTransform.pivot(3.5F, 14.0F, -8.0F));

		 */

		return TexturedModelData.of(modelData, 128, 128);
	}
}