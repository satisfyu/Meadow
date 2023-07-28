package net.satisfyu.meadow.entity.cow.shearable.highland_cattle;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.cow.shearable.WoolyCowModel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HighlandCattleRenderer extends MobRenderer<HighlandCattleEntity, WoolyCowModel<HighlandCattleEntity>> {

    private static final ResourceLocation TEXTURE_SHEARED = new ResourceLocation(MOD_ID, "textures/entity/cow/highland_cattle_sheared.png");

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/cow/highland_cattle.png");

    public HighlandCattleRenderer(EntityRendererProvider.Context context) {
        super(context, new WoolyCowModel<>(context.bakeLayer(MeadowClient.HIGHLAND_CATTLE_MODEL_LAYER)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(HighlandCattleEntity cowEntity) {
        return cowEntity.isSheared() ? TEXTURE_SHEARED : TEXTURE;
    }
}
