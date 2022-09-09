package net.satisfyu.meadow.entity.cow.cream_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.cow.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.cow.MeadowCowEntityMdodel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class CreamCowRenderer extends MobEntityRenderer<CreamCowEntity, MeadowCowEntityMdodel<CreamCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/cream_cow.png");
    public CreamCowRenderer(EntityRendererFactory.Context context) {
        super(context, new MeadowCowEntityMdodel<>(context.getPart(MeadowClient.CREAM_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "cream_cow"));
    }

    @Override
    public Identifier getTexture(CreamCowEntity cowEntity) {
        return TEXTURE;
    }
}
