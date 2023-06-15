package net.satisfyu.meadow.entity.buffalo;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class WaterBuffaloEntityRenderer extends MobEntityRenderer<WaterBuffaloEntity, WaterBuffaloEntityModel> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/buffalo/water_buffalo.png");
    public WaterBuffaloEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new WaterBuffaloEntityModel(context.getPart(MeadowClient.WATER_BUFFALO_MODEL_LAYER)), 0.9f);
    }

    @Override
    public Identifier getTexture(WaterBuffaloEntity cowEntity) {
        return TEXTURE;
    }


}
