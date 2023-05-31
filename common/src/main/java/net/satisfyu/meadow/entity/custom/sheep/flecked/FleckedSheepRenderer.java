package net.satisfyu.meadow.entity.custom.sheep.flecked;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class FleckedSheepRenderer extends MobEntityRenderer<FleckedSheepEntity, SheepEntityModel<FleckedSheepEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/sheep/flecked_sheep.png");

    public FleckedSheepRenderer(EntityRendererFactory.Context context) {
        super(context, new SheepEntityModel<>(context.getPart(MeadowClient.FLECKED_SHEEP_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "flecked_sheep", "sheep"));
        this.addFeature(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelLoader(), "flecked", MeadowClient.FLECKED_SHEEP_FUR));
    }

    @Override
    public Identifier getTexture(FleckedSheepEntity entity) {
        return TEXTURE;
    }
}
