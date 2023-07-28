package net.satisfyu.meadow.entity.cow.shearable.warped;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.cow.shearable.WoolyCowModel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WarpedCowRenderer extends MobRenderer<WarpedCowEntity, WoolyCowModel<WarpedCowEntity>> {

    private static final ResourceLocation TEXTURE_SHEARED = new ResourceLocation(MOD_ID, "textures/entity/cow/warped_cow_sheared.png");

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/warped_cow.png");

    public WarpedCowRenderer(EntityRendererProvider.Context context) {
        super(context, new WoolyCowModel<>(context.bakeLayer(MeadowClient.UMBRA_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(WarpedCowEntity cowEntity) {
        return cowEntity.isSheared() ? TEXTURE_SHEARED : TEXTURE;
    }
}
