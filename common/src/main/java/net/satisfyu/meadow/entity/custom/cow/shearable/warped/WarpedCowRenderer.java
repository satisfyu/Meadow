package net.satisfyu.meadow.entity.custom.cow.shearable.warped;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.cow.shearable.WoolyCowModel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class WarpedCowRenderer extends MobEntityRenderer<WarpedCowEntity, WoolyCowModel<WarpedCowEntity>> {

    private static final Identifier TEXTURE_SHEARED = new Identifier(MOD_ID, "textures/entity/cow/warped_cow_sheared.png");

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/warped_cow.png");

    public WarpedCowRenderer(EntityRendererFactory.Context context) {
        super(context, new WoolyCowModel<>(context.getPart(MeadowClient.UMBRA_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "umbra_cow", "cow"));
    }

    @Override
    public Identifier getTexture(WarpedCowEntity cowEntity) {
        return cowEntity.isSheared() ? TEXTURE_SHEARED : TEXTURE;
    }
}
