package net.satisfyu.meadow.entity.custom.bear.brown;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.util.MeadowIdentifier;

@Environment(EnvType.CLIENT)
public class BrownBearEntityRenderer extends MobEntityRenderer<BrownBearEntity, BrownBearEntityModel> {
    private static final Identifier TEXTURE = new MeadowIdentifier("textures/entity/brown_bear/brown_bear.png");

    public BrownBearEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BrownBearEntityModel(context.getPart(MeadowClient.BROWN_BEAR_MODEL_LAYER)), 0.9F);
    }

    public Identifier getTexture(BrownBearEntity polarBearEntity) {
        return TEXTURE;
    }

    protected void scale(BrownBearEntity polarBearEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
        super.scale(polarBearEntity, matrixStack, f);
    }
}
