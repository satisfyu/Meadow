package net.satisfyu.meadow.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.client.model.WaterBuffaloModel;
import net.satisfyu.meadow.entity.WaterBuffalo;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class WaterBuffaloRenderer extends MobRenderer<WaterBuffalo, WaterBuffaloModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/buffalo/water_buffalo.png");

    public WaterBuffaloRenderer(EntityRendererProvider.Context context) {
        super(context, new WaterBuffaloModel(context.bakeLayer(MeadowClient.WATER_BUFFALO_MODEL_LAYER)), 0.9f);
    }

    @Override
    public ResourceLocation getTextureLocation(WaterBuffalo entity) {
        return TEXTURE;
    }


}
