package net.satisfyu.meadow.entity.custom.sheep.horned;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepWoolFeatureRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class HornedSheepRenderer extends MobEntityRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/sheep/horned_sheep.png");

    public HornedSheepRenderer(EntityRendererFactory.Context context) {
        super(context, new HornedSheepModel<>(context.getPart(MeadowClient.HORNED_SHEEP_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "horned_sheep", "sheep"));
        this.addFeature(new MeadowSheepWoolFeatureRenderer<>(this, context.getModelLoader(), "DEFAULT", EntityModelLayers.SHEEP_FUR));
    }

    @Override
    public Identifier getTexture(HornedSheepEntity entity) {
        return TEXTURE;
    }
}
