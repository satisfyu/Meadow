package net.satisfyu.meadow.entity.cow.albino_cow;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AlbinoCowRenderer extends MobRenderer<AlbinoCowEntity, CowModel<AlbinoCowEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/albino_cow.png");

    public AlbinoCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.ALBINO_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(AlbinoCowEntity entity) {
        return TEXTURE;
    }
}
