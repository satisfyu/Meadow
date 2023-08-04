package net.satisfyu.meadow.entity.cow;

import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

public class MeadowVarCowEntityRenderer extends MobRenderer<MeadowVarCowEntity, CowModel<MeadowVarCowEntity>> {

    public MeadowVarCowEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(MeadowClient.MEADOW_VAR_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MeadowVarCowEntity entity) {
        return new MeadowIdentifier(String.format("textures/entity/cow/%s_cow.png", entity.getVariant().getSerializedName()));
    }
}
