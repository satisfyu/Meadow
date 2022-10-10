package net.satisfyu.meadow.entity.custom.cow.pinto_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.cow.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.cow.MeadowCowEntityMdodel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class PintoCowRenderer extends MobEntityRenderer<PintoCowEntity, MeadowCowEntityMdodel<PintoCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/pinto_cow.png");
    public PintoCowRenderer(EntityRendererFactory.Context context) {
        super(context, new MeadowCowEntityMdodel<>(context.getPart(MeadowClient.PINTO_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "pinto_cow"));
    }

    @Override
    public Identifier getTexture(PintoCowEntity cowEntity) {
        return TEXTURE;
    }
}
