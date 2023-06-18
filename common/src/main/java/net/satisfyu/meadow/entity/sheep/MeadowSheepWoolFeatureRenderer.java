package net.satisfyu.meadow.entity.sheep;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

@Environment(value = EnvType.CLIENT)
public class MeadowSheepWoolFeatureRenderer<T extends SheepEntity, F extends SheepEntityModel<T>>
        extends FeatureRenderer<T, F> {

    private final SheepWoolEntityModel<T> model;

    private final String name;


    public MeadowSheepWoolFeatureRenderer(FeatureRendererContext<T, F> context, EntityModelLoader loader, String name, EntityModelLayer sheepWoolModel) {
        super(context);
        this.model = new SheepWoolEntityModel<>(loader.getModelPart(sheepWoolModel));
        this.name = name;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T sheepEntity, float f, float g, float h, float j, float k, float l) {
        Identifier skin;
        if (name.equals("DEFAULT")) {
            skin = new Identifier("textures/entity/sheep/sheep_fur.png");
        } else {
            skin = new Identifier(Meadow.MOD_ID, "textures/entity/sheep/" + name + "_sheep_fur.png");
        }


        float u;
        float t;
        float s;
        if (sheepEntity.isSheared()) {
            return;
        }
        if (sheepEntity.isInvisible()) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            boolean bl = minecraftClient.hasOutline(sheepEntity);
            if (bl) {
                this.getContextModel().copyStateTo(this.model);
                this.model.animateModel(sheepEntity, f, g, h);
                this.model.setAngles(sheepEntity, f, g, j, k, l);
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getOutline(skin));
                this.model.render(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(sheepEntity, 0.0f), 0.0f, 0.0f, 0.0f, 1.0f);
            }
            return;
        }
        if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getString())) {
            int n = sheepEntity.age / 25 + sheepEntity.getId();
            int o = DyeColor.values().length;
            int p = n % o;
            int q = (n + 1) % o;
            float r = ((float) (sheepEntity.age % 25) + h) / 25.0f;
            float[] fs = SheepEntity.getRgbColor(DyeColor.byId(p));
            float[] gs = SheepEntity.getRgbColor(DyeColor.byId(q));
            s = fs[0] * (1.0f - r) + gs[0] * r;
            t = fs[1] * (1.0f - r) + gs[1] * r;
            u = fs[2] * (1.0f - r) + gs[2] * r;
        } else {
            float[] hs = SheepEntity.getRgbColor(sheepEntity.getColor());
            s = hs[0];
            t = hs[1];
            u = hs[2];
        }
        MeadowSheepWoolFeatureRenderer.render(this.getContextModel(), this.model, skin, matrixStack, vertexConsumerProvider, i, sheepEntity, f, g, j, k, l, h, s, t, u);
    }
}

