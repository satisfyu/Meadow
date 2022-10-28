package net.satisfyu.meadow.entity.custom.chicken.chicken3;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.satisfyu.meadow.Meadow;

@Environment(value= EnvType.CLIENT)
public class Chicken3Renderer extends MobEntityRenderer<Chicken3Entity, ChickenEntityModel<Chicken3Entity>> {
    private static final Identifier TEXTURE = new Identifier(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_3.png");

    public Chicken3Renderer(EntityRendererFactory.Context context) {
        super(context, new ChickenEntityModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public Identifier getTexture(Chicken3Entity chickenEntity) {
        return TEXTURE;
    }

    @Override
    protected float getAnimationProgress(Chicken3Entity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0f) * h;
    }
}

