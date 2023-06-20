package net.satisfyu.meadow.client.render.armor;

import de.cristelknight.doapi.DoApiRL;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.LivingEntity;


public class FurArmorOuter<T extends LivingEntity> extends BipedEntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new DoApiRL( "wool_armor"), "main");

    public FurArmorOuter(ModelPart root) {
        super(root);
    }

    public static TexturedModelData createBodyLayer() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);

        modelPartData.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -37.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
                .uv(36, 0).cuboid(-3.0F, -39.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(49, 7).cuboid(3.0F, -44.0F, 0.0F, 0.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(4, 22).cuboid(6.0F, -40.0F, -6.0F, 0.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(4, 22).cuboid(-6.0F, -40.0F, -6.0F, 0.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(28, 34).cuboid(-3.0F, -35.0F, -6.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 29.0F, 0.0F));

        modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.1F))
                .uv(0, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.5F))
                .uv(40, 35).cuboid(-3.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(49, 7).cuboid(2.4F, -7.5F, -1.0F, 0.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(40, 16).cuboid(-1.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.1F))
                .uv(40, 35).cuboid(-1.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.2F))
                .uv(0, 16).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }
}