package net.satisfyu.meadow.client.render.armor;

import de.cristelknight.doapi.DoApiRL;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.LivingEntity;


public class FurArmorInner<T extends LivingEntity> extends BipedEntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new DoApiRL("wool_armor_inner"), "main");

    public FurArmorInner(ModelPart root) {
        super(root);
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData ModelPartData = meshdefinition.getRoot();

        ModelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        ModelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        ModelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        ModelPartData.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        ModelPartData.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);

        ModelPartData RightLeg = ModelPartData.addChild("right_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.05F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData LeftLeg = ModelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.05F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));

        ModelPartData additions = LeftLeg.addChild("additions", ModelPartBuilder.create().uv(3, 36).cuboid(-4.0F, -13.0F, -2.0F, 8.0F, 1.0F, 4.0F, new Dilation(0.075F))
                .uv(29, 39).cuboid(0.9F, -14.0F, -3.0F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 64, 64);
    }
}