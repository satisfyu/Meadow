package net.satisfyu.meadow.entity.cow.dark_cow;

import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DarkCowRenderer extends MobRenderer<DarkCowEntity, CowModel<DarkCowEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/dark_cow.png");

    public DarkCowRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.DARK_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTexture(DarkCowEntity cowEntity) {
        return TEXTURE;
    }
}
