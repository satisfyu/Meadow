package net.satisfy.meadow.client.model;

import de.cristelknight.doapi.DoApiRL;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;


public class FurArmorInner<T extends LivingEntity> extends HumanoidModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new DoApiRL("wool_armor_inner"), "main");

    public FurArmorInner(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition ModelPartData = meshdefinition.getRoot();

        ModelPartData.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        ModelPartData.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        ModelPartData.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        ModelPartData.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        ModelPartData.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition RightLeg = ModelPartData.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition LeftLeg = ModelPartData.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.05F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition additions = LeftLeg.addOrReplaceChild("additions", CubeListBuilder.create().texOffs(3, 36).addBox(-4.0F, -13.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.075F))
                .texOffs(29, 39).addBox(0.9F, -14.0F, -3.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}