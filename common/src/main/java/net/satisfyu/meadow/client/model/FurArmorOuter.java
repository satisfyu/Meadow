package net.satisfyu.meadow.client.model;

import de.cristelknight.doapi.DoApiRL;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;


public class FurArmorOuter<T extends LivingEntity> extends HumanoidModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new DoApiRL( "wool_armor"), "main");

    public FurArmorOuter(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);

        modelPartData.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -37.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-3.0F, -39.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(49, 7).addBox(3.0F, -44.0F, 0.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(4, 22).addBox(6.0F, -40.0F, -6.0F, 0.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(4, 22).addBox(-6.0F, -40.0F, -6.0F, 0.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(28, 34).addBox(-3.0F, -35.0F, -6.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 29.0F, 0.0F));

        modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        modelPartData.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(0, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F))
                .texOffs(40, 35).addBox(-3.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        modelPartData.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(49, 7).addBox(2.4F, -7.5F, -1.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(40, 16).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(40, 35).addBox(-1.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
                .texOffs(0, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        modelPartData.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        modelPartData.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }
}