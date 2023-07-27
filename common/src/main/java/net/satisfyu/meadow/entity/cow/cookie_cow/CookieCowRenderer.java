package net.satisfyu.meadow.entity.cow.cookie_cow;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CookieCowRenderer extends MobRenderer<CookieCowEntity, CowModel<CookieCowEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/cookie_cow.png");

    public CookieCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.COOKIE_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTexture(CookieCowEntity cowEntity) {
        return TEXTURE;
    }
}
