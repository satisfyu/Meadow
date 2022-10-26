package net.satisfyu.meadow.entity.custom.cow.shearable.highland_cattle;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.cow.shearable.WoolyCowModel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class HighlandCattleRenderer extends MobEntityRenderer<HighlandCattleEntity, WoolyCowModel<HighlandCattleEntity>> {

    private static final Identifier TEXTURE_SHEARED = new Identifier(MOD_ID, "textures/entity/cow/highland_cattle_sheared.png");

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/highland_cattle.png");

    public HighlandCattleRenderer(EntityRendererFactory.Context context) {
        super(context, new WoolyCowModel<>(context.getPart(MeadowClient.HIGHLAND_CATTLE_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "highland_cattle", "cow"));
    }

    @Override
    public Identifier getTexture(HighlandCattleEntity cowEntity) {
        return cowEntity.isSheared() ? TEXTURE_SHEARED : TEXTURE;
    }
}
