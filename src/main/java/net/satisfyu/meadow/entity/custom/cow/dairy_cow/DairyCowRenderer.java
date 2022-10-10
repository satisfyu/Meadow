package net.satisfyu.meadow.entity.custom.cow.dairy_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.cow.EyeBlinkRenderer;
import net.satisfyu.meadow.entity.custom.cow.MeadowCowEntityMdodel;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class DairyCowRenderer extends MobEntityRenderer<DairyCowEntity, MeadowCowEntityMdodel<DairyCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/dairy_cow.png");
    public DairyCowRenderer(EntityRendererFactory.Context context) {
        super(context, new MeadowCowEntityMdodel<>(context.getPart(MeadowClient.DAIRY_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "dairy_cow"));
    }

    @Override
    public Identifier getTexture(DairyCowEntity cowEntity) {
        return TEXTURE;
    }
}
