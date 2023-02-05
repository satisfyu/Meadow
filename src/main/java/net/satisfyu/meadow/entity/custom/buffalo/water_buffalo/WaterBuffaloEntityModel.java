package net.satisfyu.meadow.entity.custom.buffalo.water_buffalo;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class WaterBuffaloEntityModel extends QuadrupedEntityModel<WaterBuffaloEntity> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    public WaterBuffaloEntityModel(ModelPart root) {
        super(root, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("left_hind_leg");
        this.leg1 = root.getChild("right_hind_leg");
        this.leg2 = root.getChild("left_front_leg");
        this.leg3 = root.getChild("right_front_leg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(18, 5).cuboid(-5.0F, -11.0F, -7.0F, 14.0F, 12.0F, 10.0F, new Dilation(0.0F))
                .uv(68, 0).cuboid(-6.0F, -24.0F, -7.0F, 16.0F, 13.0F, 14.0F, new Dilation(0.0F))
                .uv(116, 52).cuboid(0.0F, -13.0F, -8.0F, 4.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 9.0F, 12.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(86, 46).cuboid(-4.0F, -5.0F, -3.0F, 8.0F, 8.0F, 7.0F, new Dilation(0.0F))
                .uv(118, 47).cuboid(4.0F, -4.0F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(118, 47).mirrored().cuboid(-7.0F, -4.0F, -1.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(116, 44).cuboid(-7.0F, -7.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(116, 44).mirrored().cuboid(5.0F, -7.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 10.0F, -16.0F));

        head.addChild("snout_r1", ModelPartBuilder.create().uv(93, 60).cuboid(-2.0F, 2.0F, -12.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 8.0F, 0.0873F, 0.0F, 0.0F));

        ModelPartData leg0 = modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(14, 28).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.5F, 14.0F, 6.0F));

        ModelPartData leg1 = modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(14, 28).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 14.0F, 6.0F));

        ModelPartData leg2 = modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(16, 30).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, 14.0F, -8.0F));

        ModelPartData leg3 = modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(16, 30).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, 14.0F, -8.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg0.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}