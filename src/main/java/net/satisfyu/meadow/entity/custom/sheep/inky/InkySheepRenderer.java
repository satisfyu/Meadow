package net.satisfyu.meadow.entity.custom.sheep.inky;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class InkySheepRenderer extends MobEntityRenderer<InkySheepEntity, SheepEntityModel<InkySheepEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/sheep/inky_sheep.png");

    public InkySheepRenderer(EntityRendererFactory.Context context) {
        super(context, new SheepEntityModel<>(context.getPart(MeadowClient.INKY_SHEEP_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "inky_sheep", "sheep"));
        this.addFeature(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelLoader(), "inky", MeadowClient.INKY_SHEEP_FUR));
    }

    @Override
    public Identifier getTexture(InkySheepEntity entity) {
        return TEXTURE;
    }
}
