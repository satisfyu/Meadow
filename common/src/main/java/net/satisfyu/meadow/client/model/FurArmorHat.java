package net.satisfyu.meadow.client.model;


import de.cristelknight.doapi.DoApiRL;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;


public class FurArmorHat<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new DoApiRL("hat"), "main");
    private final ModelPart head;

    public FurArmorHat(ModelPart root) {
        this.head = root.getChild("head");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 40.0F, 0.0F));

        head.addChild("hat", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -47.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
                .uv(36, 0).cuboid(-3.0F, -49.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(49, 7).cuboid(3.0F, -54.0F, 0.0F, 0.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(4, 22).cuboid(6.0F, -50.0F, -6.0F, 0.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(4, 22).cuboid(-6.0F, -50.0F, -6.0F, 0.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(28, 34).cuboid(-3.0F, -45.0F, -6.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 29.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }


    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        head.render(matrices, vertices, light, overlay);
    }
}