package net.satisfyu.meadow.entity.sheep.flecked;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FleckedSheepRenderer extends MobRenderer<FleckedSheepEntity, SheepModel<FleckedSheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/flecked_sheep.png");

    public FleckedSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(MeadowClient.FLECKED_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "flecked", MeadowClient.FLECKED_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTextureLocation(FleckedSheepEntity entity) {
        return TEXTURE;
    }
}
