package net.satisfyu.meadow.entity.bear;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.util.MeadowIdentifier;

@Environment(EnvType.CLIENT)
public class BrownBearEntityRenderer extends MobRenderer<BrownBearEntity, BrownBearEntityModel> {
    private static final ResourceLocation TEXTURE = new MeadowIdentifier("textures/entity/brown_bear/brown_bear.png");

    public BrownBearEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BrownBearEntityModel(context.bakeLayer(MeadowClient.BROWN_BEAR_MODEL_LAYER)), 0.9F);
    }

    public ResourceLocation getTexture(BrownBearEntity polarBearEntity) {
        return TEXTURE;
    }

    protected void scale(BrownBearEntity polarBearEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
        super.scale(polarBearEntity, matrixStack, f);
    }
}
