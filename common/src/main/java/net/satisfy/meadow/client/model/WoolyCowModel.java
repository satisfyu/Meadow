package net.satisfy.meadow.client.model;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.satisfy.meadow.entity.ShearableVarCow;

public class WoolyCowModel extends CowModel<ShearableVarCow> {

    private float headPitchModifier;

    public WoolyCowModel(ModelPart modelPart) {
        super(modelPart);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 33).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-6.0F, -10.0F, -12.0F, 12.0F, 18.0F, 15.0F, new CubeDeformation(0.2F))
                .texOffs(0, 0).addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(39, 0).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(34, 33).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 4.0F, -9.0F));

        PartDefinition snout_r1 = head.addOrReplaceChild("snout_r1", CubeListBuilder.create().texOffs(16, 61).addBox(-3.0F, -1.0F, -7.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition horn_right_top_r1 = head.addOrReplaceChild("horn_right_top_r1", CubeListBuilder.create().texOffs(6, 10).addBox(5.0F, -29.0F, -11.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(66, 12).addBox(0.0F, -26.0F, -11.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 8.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition horn_left_top_r1 = head.addOrReplaceChild("horn_left_top_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-7.0F, -29.0F, -11.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(61, 0).addBox(-7.0F, -26.0F, -11.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 22.0F, 8.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition ear_right_r1 = head.addOrReplaceChild("ear_right_r1", CubeListBuilder.create().texOffs(67, 4).addBox(-13.0F, -19.0F, -12.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 8.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition ear_left_r1 = head.addOrReplaceChild("ear_left_r1", CubeListBuilder.create().texOffs(12, 67).addBox(9.0F, -19.0F, -12.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 8.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(44, 43).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 47).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.15F)), PartPose.offset(-4.0F, 12.0F, 7.0F));

        PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(44, 43).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 47).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.15F)), PartPose.offset(4.0F, 12.0F, 7.0F));

        PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(44, 43).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 47).addBox(-2.0F, 5.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.15F)), PartPose.offset(-4.0F, 12.0F, -6.0F));

        PartDefinition right_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(44, 43).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 47).addBox(-2.0F, 5.0F, -1.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.15F)), PartPose.offset(4.0F, 12.0F, -6.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(ShearableVarCow sheepEntity, float f, float g, float h, float i, float j) {
        super.setupAnim(sheepEntity, f, g, h, i, j);
        this.head.xRot = this.headPitchModifier;
    }

    @Override
    public void prepareMobModel(ShearableVarCow entity, float f, float g, float h) {
        super.prepareMobModel(entity, f, g, h);
        this.head.y = 6.0f + entity.getNeckAngle(h) * 9.0f;
        this.headPitchModifier = entity.getHeadAngle(h);
    }
}