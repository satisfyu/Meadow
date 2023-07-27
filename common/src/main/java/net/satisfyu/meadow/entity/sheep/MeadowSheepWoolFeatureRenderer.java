package net.satisfyu.meadow.entity.sheep;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.satisfyu.meadow.Meadow;

@Environment(value = EnvType.CLIENT)
public class MeadowSheepWoolFeatureRenderer<T extends Sheep, F extends SheepModel<T>>
        extends RenderLayer<T, F> {

    private final SheepFurModel<T> model;

    private final String name;


    public MeadowSheepWoolFeatureRenderer(RenderLayerParent<T, F> context, EntityModelSet loader, String name, ModelLayerLocation sheepWoolModel) {
        super(context);
        this.model = new SheepFurModel<>(loader.bakeLayer(sheepWoolModel));
        this.name = name;
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, T sheepEntity, float f, float g, float h, float j, float k, float l) {
        ResourceLocation skin;
        if (name.equals("DEFAULT")) {
            skin = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
        } else {
            skin = new ResourceLocation(Meadow.MOD_ID, "textures/entity/sheep/" + name + "_sheep_fur.png");
        }


        float u;
        float t;
        float s;
        if (sheepEntity.isSheared()) {
            return;
        }
        if (sheepEntity.isInvisible()) {
            Minecraft minecraftClient = Minecraft.getInstance();
            boolean bl = minecraftClient.shouldEntityAppearGlowing(sheepEntity);
            if (bl) {
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(sheepEntity, f, g, h);
                this.model.setupAnim(sheepEntity, f, g, j, k, l);
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.outline(skin));
                this.model.renderToBuffer(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlayCoords(sheepEntity, 0.0f), 0.0f, 0.0f, 0.0f, 1.0f);
            }
            return;
        }
        if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getString())) {
            int n = sheepEntity.tickCount / 25 + sheepEntity.getId();
            int o = DyeColor.values().length;
            int p = n % o;
            int q = (n + 1) % o;
            float r = ((float) (sheepEntity.tickCount % 25) + h) / 25.0f;
            float[] fs = Sheep.getColorArray(DyeColor.byId(p));
            float[] gs = Sheep.getColorArray(DyeColor.byId(q));
            s = fs[0] * (1.0f - r) + gs[0] * r;
            t = fs[1] * (1.0f - r) + gs[1] * r;
            u = fs[2] * (1.0f - r) + gs[2] * r;
        } else {
            float[] hs = Sheep.getColorArray(sheepEntity.getColor());
            s = hs[0];
            t = hs[1];
            u = hs[2];
        }
        MeadowSheepWoolFeatureRenderer.coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, skin, matrixStack, vertexConsumerProvider, i, sheepEntity, f, g, j, k, l, h, s, t, u);
    }
}

