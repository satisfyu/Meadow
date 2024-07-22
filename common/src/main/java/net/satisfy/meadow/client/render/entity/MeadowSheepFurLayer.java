package net.satisfy.meadow.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.meadow.client.model.MeadowSheepModel;
import net.satisfy.meadow.entity.MeadowSheep;
import net.satisfy.meadow.util.MeadowIdentifier;

public class MeadowSheepFurLayer extends RenderLayer<MeadowSheep, MeadowSheepModel<MeadowSheep>> {
    private static final ResourceLocation FLECKED_FUR_LOCATION = new MeadowIdentifier("textures/entity/sheep/flecked_sheep_fur.png");
    private static final ResourceLocation PATCHED_FUR_LOCATION = new MeadowIdentifier("textures/entity/sheep/patched_sheep_fur.png");
    private static final ResourceLocation ROCKY_FUR_LOCATION = new MeadowIdentifier("textures/entity/sheep/rocky_sheep_fur.png");
    private static final ResourceLocation INKY_FUR_LOCATION = new MeadowIdentifier("textures/entity/sheep/inky_sheep_fur.png");
    private final MeadowSheepFurModel<MeadowSheep> sheepModel;

    public MeadowSheepFurLayer(RenderLayerParent<MeadowSheep, MeadowSheepModel<MeadowSheep>> renderLayerParent, EntityRendererProvider.Context context) {
        super(renderLayerParent);
        this.sheepModel = new MeadowSheepFurModel<>(context.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, MeadowSheep meadowSheep, float f, float g, float h, float j, float k, float l) {
        if (!meadowSheep.isSheared() && !meadowSheep.isInvisible()) {
            System.out.println("Rendering fur for: " + meadowSheep.getSheepTexture());
            ResourceLocation furLocation = getFurLocation(meadowSheep.getSheepTexture());
            float[] fs = meadowSheep.getTextureColor().getTextureDiffuseColors();

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.sheepModel, furLocation, poseStack, multiBufferSource, i, meadowSheep, f, g, j, k, l, h, fs[0], fs[1], fs[2]);
        }
    }


    private ResourceLocation getFurLocation(MeadowSheep.SheepTexture texture) {
        return switch (texture) {
            case PATCHED -> PATCHED_FUR_LOCATION;
            case ROCKY -> ROCKY_FUR_LOCATION;
            case INKY -> INKY_FUR_LOCATION;
            default -> FLECKED_FUR_LOCATION;
        };
    }
}
