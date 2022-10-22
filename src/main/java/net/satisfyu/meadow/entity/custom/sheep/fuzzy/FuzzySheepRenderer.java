package net.satisfyu.meadow.entity.custom.sheep.fuzzy;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class FuzzySheepRenderer extends MobEntityRenderer<FuzzySheepEntity, SheepEntityModel<FuzzySheepEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/sheep/fuzzy_sheep.png");

    public FuzzySheepRenderer(EntityRendererFactory.Context context) {
        super(context, new SheepEntityModel<>(context.getPart(MeadowClient.FUZZY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "fuzzy_sheep", "sheep"));
        this.addFeature(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelLoader(), "fuzzy", MeadowClient.FUZZY_SHEEP_FUR));
    }

    @Override
    public Identifier getTexture(FuzzySheepEntity entity) {
        return TEXTURE;
    }
}
