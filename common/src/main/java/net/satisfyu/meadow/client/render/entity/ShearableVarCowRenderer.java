package net.satisfyu.meadow.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.client.model.WoolyCowModel;
import net.satisfyu.meadow.entity.ShearableVarCow;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

public class ShearableVarCowRenderer extends MobRenderer<ShearableVarCow, WoolyCowModel> {

    public ShearableVarCowRenderer(EntityRendererProvider.Context context) {
        super(context, new WoolyCowModel(context.bakeLayer(MeadowClient.SHEARABLE_MEADOW_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ShearableVarCow entity) {
        return entity.isSheared() ?
                new MeadowIdentifier(String.format("textures/entity/cow/%s_sheared.png", entity.getVariant().getSerializedName())) :
                new MeadowIdentifier(String.format("textures/entity/cow/%s.png", entity.getVariant().getSerializedName()));
    }
}
