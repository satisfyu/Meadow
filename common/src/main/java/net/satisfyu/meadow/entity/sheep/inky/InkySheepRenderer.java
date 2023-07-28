package net.satisfyu.meadow.entity.sheep.inky;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class InkySheepRenderer extends MobRenderer<InkySheepEntity, SheepModel<InkySheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/inky_sheep.png");

    public InkySheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(MeadowClient.INKY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "inky", MeadowClient.INKY_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTextureLocation(InkySheepEntity entity) {
        return TEXTURE;
    }
}
