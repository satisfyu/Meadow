package net.satisfyu.meadow.entity.sheep.rocky;

import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RockySheepRenderer extends MobRenderer<RockySheepEntity, SheepModel<RockySheepEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/sheep/rocky_sheep.png");

    public RockySheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(MeadowClient.ROCKY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addLayer(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelSet(), "rocky", MeadowClient.ROCKY_SHEEP_FUR));
    }

    @Override
    public ResourceLocation getTexture(RockySheepEntity entity) {
        return TEXTURE;
    }
}
