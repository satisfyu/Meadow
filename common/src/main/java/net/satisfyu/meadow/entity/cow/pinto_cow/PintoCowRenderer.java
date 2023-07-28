package net.satisfyu.meadow.entity.cow.pinto_cow;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PintoCowRenderer extends MobRenderer<PintoCowEntity, CowModel<PintoCowEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/pinto_cow.png");

    public PintoCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.PINTO_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(PintoCowEntity cowEntity) {
        return TEXTURE;
    }
}
