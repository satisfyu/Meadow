package net.satisfyu.meadow.entity.sheep.patched;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PatchedSheepRenderer extends MobRenderer<PatchedSheepEntity, SheepModel<PatchedSheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/patched_sheep.png");

    public PatchedSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(MeadowClient.PATCHED_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "patched", MeadowClient.PATCHED_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTexture(PatchedSheepEntity entity) {
        return TEXTURE;
    }
}
