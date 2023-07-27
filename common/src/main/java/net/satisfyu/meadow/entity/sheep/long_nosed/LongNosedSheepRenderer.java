package net.satisfyu.meadow.entity.sheep.long_nosed;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LongNosedSheepRenderer extends MobRenderer<LongNosedSheepEntity, SheepModel<LongNosedSheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/long_nosed_sheep.png");

    public LongNosedSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(MeadowClient.LONG_NOSED_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "long_nosed", MeadowClient.LONG_NOSED_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTexture(LongNosedSheepEntity entity) {
        return TEXTURE;
    }
}
