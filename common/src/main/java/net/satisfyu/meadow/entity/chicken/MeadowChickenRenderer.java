package net.satisfyu.meadow.entity.chicken;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.satisfyu.meadow.Meadow;

@Environment(value = EnvType.CLIENT)
public class MeadowChickenRenderer extends MobEntityRenderer<MeadowChickenEntity, ChickenEntityModel<MeadowChickenEntity>> {
    private static final Identifier[] TEXTURES = {
            new Identifier(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_1.png"),
            new Identifier(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_2.png"),
            new Identifier(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_3.png")
    };

    public MeadowChickenRenderer(EntityRendererFactory.Context context) {
        super(context, new ChickenEntityModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public Identifier getTexture(MeadowChickenEntity chickenEntity) {
        return chickenEntity.getTextureVariant();
    }

    @Override
    protected float getAnimationProgress(MeadowChickenEntity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0f) * h;
    }
}
