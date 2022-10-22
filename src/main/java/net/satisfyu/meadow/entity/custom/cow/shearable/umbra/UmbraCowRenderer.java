package net.satisfyu.meadow.entity.custom.cow.shearable.umbra;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.cow.shearable.WoolyCowModel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class UmbraCowRenderer extends MobEntityRenderer<UmbraCowEntity, WoolyCowModel<UmbraCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/umbra_cow.png");

    public UmbraCowRenderer(EntityRendererFactory.Context context) {
        super(context, new WoolyCowModel<>(context.getPart(MeadowClient.UMBRA_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "umbra_cow", "cow"));
    }

    @Override
    public Identifier getTexture(UmbraCowEntity cowEntity) {
        return TEXTURE;
    }
}
