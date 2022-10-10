package net.satisfyu.meadow.entity.custom.cow.cookie_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.cow.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.cow.MeadowCowEntityMdodel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class CookieCowRenderer extends MobEntityRenderer<CookieCowEntity, MeadowCowEntityMdodel<CookieCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/cookie_cow.png");
    public CookieCowRenderer(EntityRendererFactory.Context context) {
        super(context, new MeadowCowEntityMdodel<>(context.getPart(MeadowClient.COOKIE_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "cookie_cow"));
    }

    @Override
    public Identifier getTexture(CookieCowEntity cowEntity) {
        return TEXTURE;
    }
}
