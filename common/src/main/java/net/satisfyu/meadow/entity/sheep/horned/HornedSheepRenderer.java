package net.satisfyu.meadow.entity.sheep.horned;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HornedSheepRenderer extends MobRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/horned_sheep.png");

    public HornedSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new HornedSheepModel<>(context.bakeLayer(MeadowClient.HORNED_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "DEFAULT", ModelLayers.SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTexture(HornedSheepEntity entity) {
        return TEXTURE;
    }
}
