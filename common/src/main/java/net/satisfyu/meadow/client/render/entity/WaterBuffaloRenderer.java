package net.satisfyu.meadow.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.client.model.WaterBuffaloModel;
import net.satisfyu.meadow.entity.WaterBuffalo;
import org.jetbrains.annotations.NotNull;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class WaterBuffaloRenderer extends MobRenderer<WaterBuffalo, WaterBuffaloModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/buffalo/water_buffalo.png");

    public WaterBuffaloRenderer(EntityRendererProvider.Context context) {
        super(context, new WaterBuffaloModel(context.bakeLayer(MeadowClient.WATER_BUFFALO_MODEL_LAYER)), 1.0f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(WaterBuffalo entity) {
        return TEXTURE;
    }

    @Override
    public void render(WaterBuffalo pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
