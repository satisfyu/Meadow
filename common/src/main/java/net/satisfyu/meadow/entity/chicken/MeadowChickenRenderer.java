package net.satisfyu.meadow.entity.chicken;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.satisfyu.meadow.Meadow;

@Environment(value = EnvType.CLIENT)
public class MeadowChickenRenderer extends MobRenderer<MeadowChickenEntity, ChickenModel<MeadowChickenEntity>> {
    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_1.png"),
            new ResourceLocation(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_2.png"),
            new ResourceLocation(Meadow.MOD_ID, "textures/entity/chicken/meadow_chicken_3.png")
    };

    public MeadowChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(MeadowChickenEntity entity) {
        return entity.getTextureVariant();
    }

    @Override
    protected float getAttackAnim(MeadowChickenEntity livingEntity, float f) {
        float g = Mth.lerp(f, livingEntity.oFlap, livingEntity.flap);
        float h = Mth.lerp(f, livingEntity.oFlapSpeed, livingEntity.flapSpeed);
        return (Mth.sin(g) + 1.0f) * h;
    }
}
