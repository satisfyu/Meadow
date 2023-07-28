package net.satisfyu.meadow.entity.sheep.fuzzy;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FuzzySheepRenderer extends MobRenderer<FuzzySheepEntity, SheepModel<FuzzySheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/fuzzy_sheep.png");

    public FuzzySheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(MeadowClient.FUZZY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "fuzzy", MeadowClient.FUZZY_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTextureLocation(FuzzySheepEntity entity) {
        return TEXTURE;
    }
}
