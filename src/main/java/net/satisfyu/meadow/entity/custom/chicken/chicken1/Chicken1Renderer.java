package net.satisfyu.meadow.entity.custom.chicken.chicken1;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.satisfyu.meadow.Meadow;

@Environment(value= EnvType.CLIENT)
public class Chicken1Renderer extends MobEntityRenderer<Chicken1Entity, ChickenEntityModel<Chicken1Entity>> {
    private static final Identifier TEXTURE = new Identifier(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_1.png");

    public Chicken1Renderer(EntityRendererFactory.Context context) {
        super(context, new ChickenEntityModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public Identifier getTexture(Chicken1Entity chickenEntity) {
        return TEXTURE;
    }

    @Override
    protected float getAnimationProgress(Chicken1Entity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0f) * h;
    }
}

