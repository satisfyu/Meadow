package net.satisfyu.meadow.entity.buffalo;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WaterBuffaloEntityRenderer extends MobRenderer<WaterBuffaloEntity, WaterBuffaloEntityModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/buffalo/water_buffalo.png");

    public WaterBuffaloEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new WaterBuffaloEntityModel(context.bakeLayer(MeadowClient.WATER_BUFFALO_MODEL_LAYER)), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(WaterBuffaloEntity entity) {
        return TEXTURE;
    }


}
