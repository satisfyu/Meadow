package net.satisfyu.meadow.entity.cow.sunset_cow;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SunsetCowRenderer extends MobRenderer<SunsetCowEntity, CowModel<SunsetCowEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/sunset_cow.png");

    public SunsetCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.SUNSET_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTexture(SunsetCowEntity cowEntity) {
        return TEXTURE;
    }
}
