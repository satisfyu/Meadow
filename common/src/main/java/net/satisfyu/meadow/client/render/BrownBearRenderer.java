package net.satisfyu.meadow.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.client.model.BrownBearModel;
import net.satisfyu.meadow.entity.BrownBear;
import net.satisfyu.meadow.util.MeadowIdentifier;

@Environment(EnvType.CLIENT)
public class BrownBearRenderer extends MobRenderer<BrownBear, BrownBearModel> {
    private static final ResourceLocation TEXTURE = new MeadowIdentifier("textures/entity/brown_bear/brown_bear.png");

    public BrownBearRenderer(EntityRendererProvider.Context context) {
        super(context, new BrownBearModel(context.bakeLayer(MeadowClient.BROWN_BEAR_MODEL_LAYER)), 0.9F);
    }

    @Override
    public ResourceLocation getTextureLocation(BrownBear entity) {
        return TEXTURE;
    }

    protected void scale(BrownBear polarBearEntity, PoseStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
        super.scale(polarBearEntity, matrixStack, f);
    }
}
