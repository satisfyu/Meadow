package net.satisfyu.meadow.entity.custom.sheep.horned;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;

public class HornedSheepModel<T extends HornedSheepEntity> extends SheepEntityModel<T> {
    /* This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart horn;
    private float headXRot;

     */

    public HornedSheepModel(ModelPart root) {
        super(root);
        /*
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.horn = this.head.getChild("horn");
         */
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = QuadrupedEntityModel.getModelData(12, Dilation.NONE);
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), ModelTransform.pivot(0.0F, 6.0F, -8.0F));
        partdefinition.addChild("body", ModelPartBuilder.create().uv(22, 10).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), ModelTransform.of(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));

        ModelPartData horn = head.addChild("horn", ModelPartBuilder.create().uv(28, 0).cuboid(-2.0F, 3.0F, -4.5F, 7.0F, 4.0F, 6.0F)
                .uv(28, 0).cuboid(-2.0F, -7.0F, -4.5F, 7.0F, 4.0F, 6.0F), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

        return TexturedModelData.of(meshdefinition, 64, 32);
    }
}