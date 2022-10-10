package net.satisfyu.meadow.entity.custom.cow;

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

    private final String cowName;

    private int blinkTime = 0;
    public EyeBlinkRenderer(FeatureRendererContext<T, M> featureRendererContext, String cowName) {
        super(featureRendererContext);
        this.cowName = cowName;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if(new Random().nextFloat() < 0.001 || blinkTime > 0){
            if(blinkTime == 0) blinkTime = 20;
            blinkTime--;
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getEyesTexture(cowName));
            this.getContextModel().render(matrices, vertexConsumer, 0xF00000, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public RenderLayer getEyesTexture(String cowName) {
        return RenderLayer.getEntitySolid(new Identifier(Meadow.MOD_ID, "textures/entity/cow/" + cowName + "_blink.png"));
    }


}
