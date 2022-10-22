package net.satisfyu.meadow.entity.custom.cow.shearable;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.passive.CowEntity;

public class WoolyCowModel<T extends CowEntity> extends CowEntityModel<T> {

    public WoolyCowModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F).uv(22, 0).cuboid("right_horn", -5.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F).uv(22, 0).cuboid("left_horn", 4.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F), ModelTransform.pivot(0.0F, 4.0F, -8.0F));
        ModelPartData partdefinition2 = partdefinition.addChild("body", ModelPartBuilder.create().uv(18, 4).cuboid(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F).uv(52, 0).cuboid(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F), ModelTransform.of(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
        ModelPartData partdefinition3 = partdefinition2.addChild("body_part", ModelPartBuilder.create().uv(52, 6).cuboid(-42.0F, -1.5F, 30.0F, 3F, 8.0F, 0.0F), ModelTransform.of(-2.0F, 20.0F, -49.0F, ((float) Math.PI / 2F), ((float) Math.PI / 2F), 0.0F));
        partdefinition3.addChild("body_part2", ModelPartBuilder.create().uv(52, 6).cuboid(-1.5F, -4.0F, 0.0F, 3F, 8.0F, 0.0F), ModelTransform.of(-41.0F, 2.5F, 12.0F, ((float) Math.PI), 0.0F, 0.0F));

        ModelPartData partdefinition4 = partdefinition2.addChild("body_part3", ModelPartBuilder.create().uv(26, 0).cuboid(1.0F, -0.01F, -2.0F, 16.0F, 0.0F, 3.0F), ModelTransform.of(6.0F, -10.0F, -8.0F, 0.0F, 0.0F, ((float) Math.PI / 2F)));
        partdefinition4.addChild("body_part4", ModelPartBuilder.create().uv(26, 0).cuboid(-8.0F, 0.0F, -1.5F, 16.0F, 0.0F, 3.0F), ModelTransform.of(9.0F, 12.01F, -0.5F, 0.0F, 0.0F, -((float) Math.PI)));


        ModelPartBuilder cubelistbuilder = ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        partdefinition.addChild("right_hind_leg", cubelistbuilder, ModelTransform.pivot(-4.0F, 12.0F, 7.0F));
        partdefinition.addChild("left_hind_leg", cubelistbuilder, ModelTransform.pivot(4.0F, 12.0F, 7.0F));
        partdefinition.addChild("right_front_leg", cubelistbuilder, ModelTransform.pivot(-4.0F, 12.0F, -6.0F));
        partdefinition.addChild("left_front_leg", cubelistbuilder, ModelTransform.pivot(4.0F, 12.0F, -6.0F));
        return TexturedModelData.of(meshdefinition, 64, 32);
    }


}