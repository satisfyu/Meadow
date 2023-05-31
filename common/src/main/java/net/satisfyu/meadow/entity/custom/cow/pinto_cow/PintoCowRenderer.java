package net.satisfyu.meadow.entity.custom.cow.pinto_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class PintoCowRenderer extends MobEntityRenderer<PintoCowEntity, CowEntityModel<PintoCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/pinto_cow.png");
    public PintoCowRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(MeadowClient.PINTO_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "pinto_cow", "cow"));
    }

    @Override
    public Identifier getTexture(PintoCowEntity cowEntity) {
        return TEXTURE;
    }
}
