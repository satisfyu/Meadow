package net.satisfyu.meadow.entity.custom.cow.albino_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class AlbinoCowRenderer extends MobEntityRenderer<AlbinoCowEntity, CowEntityModel<AlbinoCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/albino_cow.png");
    public AlbinoCowRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(MeadowClient.ALBINO_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "albino_cow", "cow"));
    }

    @Override
    public Identifier getTexture(AlbinoCowEntity cowEntity) {
        return TEXTURE;
    }
}
