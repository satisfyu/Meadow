package net.satisfyu.meadow.entity.custom;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

import java.util.Random;

public class EyeBlinkRenderer<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    private final String name;

    private final String entityType;

    private int blinkTime = 0;
    public EyeBlinkRenderer(FeatureRendererContext<T, M> featureRendererContext, String name, String entityType) {
        super(featureRendererContext);
        this.name = name;
        this.entityType = entityType;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(new Random().nextFloat() < 0.001 || blinkTime > 0){
            if(blinkTime == 0) blinkTime = 30;
            blinkTime--;
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getEyesTexture(name));
            this.getContextModel().render(matrices, vertexConsumer, 0xF00000, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public RenderLayer getEyesTexture(String name) {
        String s;
        if(entityType.equals("cow")){
            s = "textures/entity/cow/cow_blink.png";
        }
        else {
            s = "textures/entity/"+ entityType + "/" + name + "_blink.png";
        }
        return RenderLayer.getEntityCutout(new Identifier(Meadow.MOD_ID, s));
    }


}
