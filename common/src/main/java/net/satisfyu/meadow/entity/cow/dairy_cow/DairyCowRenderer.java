package net.satisfyu.meadow.entity.cow.dairy_cow;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DairyCowRenderer extends MobRenderer<DairyCowEntity, CowModel<DairyCowEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/dairy_cow.png");

    public DairyCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.DAIRY_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTexture(DairyCowEntity cowEntity) {
        return TEXTURE;
    }
}
