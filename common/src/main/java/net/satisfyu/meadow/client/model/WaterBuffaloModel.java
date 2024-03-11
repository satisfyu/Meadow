package net.satisfyu.meadow.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.satisfyu.meadow.entity.WaterBuffalo;

public class WaterBuffaloModel extends QuadrupedModel<WaterBuffalo> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;

    public WaterBuffaloModel(ModelPart root) {
        super(root, true, 16.0F, 4.0F, 2.25F, 2.0F, 24);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("left_hind_leg");
        this.leg1 = root.getChild("right_hind_leg");
        this.leg2 = root.getChild("left_front_leg");
        this.leg3 = root.getChild("right_front_leg");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 5).addBox(-5.0F, -11.0F, -7.0F, 14.0F, 12.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(68, 0).addBox(-6.0F, -24.0F, -7.0F, 16.0F, 13.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(116, 52).addBox(0.0F, -13.0F, -8.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.0F, 12.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(86, 46).addBox(-4.0F, -5.0F, -3.0F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(118, 47).addBox(4.0F, -4.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(118, 47).mirror().addBox(-7.0F, -4.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(116, 44).addBox(-7.0F, -7.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(116, 44).mirror().addBox(5.0F, -7.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, -16.0F));

        head.addOrReplaceChild("snout_r1", CubeListBuilder.create().texOffs(93, 60).addBox(-2.0F, 2.0F, -12.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 8.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition leg0 = modelPartData.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(14, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 14.0F, 6.0F));

        PartDefinition leg1 = modelPartData.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(14, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 14.0F, 6.0F));

        PartDefinition leg2 = modelPartData.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(16, 30).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 14.0F, -8.0F));

        PartDefinition leg3 = modelPartData.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(16, 30).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 14.0F, -8.0F));
        return LayerDefinition.create(modelData, 128, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg0.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        leg3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}