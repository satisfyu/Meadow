package net.satisfyu.meadow.entity.sheep.rocky;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class RockySheepRenderer extends MobEntityRenderer<RockySheepEntity, SheepEntityModel<RockySheepEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/sheep/rocky_sheep.png");

    public RockySheepRenderer(EntityRendererFactory.Context context) {
        super(context, new SheepEntityModel<>(context.getPart(MeadowClient.ROCKY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addFeature(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelLoader(), "rocky", MeadowClient.ROCKY_SHEEP_FUR));
    }

    @Override
    public Identifier getTexture(RockySheepEntity entity) {
        return TEXTURE;
    }
}
