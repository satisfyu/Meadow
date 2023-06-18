package net.satisfyu.meadow.entity.cow.sunset_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class SunsetCowRenderer extends MobEntityRenderer<SunsetCowEntity, CowEntityModel<SunsetCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/sunset_cow.png");

    public SunsetCowRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(MeadowClient.SUNSET_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public Identifier getTexture(SunsetCowEntity cowEntity) {
        return TEXTURE;
    }
}
