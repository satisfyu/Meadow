package net.satisfyu.meadow.entity.custom.cow.dark_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.MeadowClient;
import net.satisfyu.meadow.entity.custom.EyeBlinkRenderer;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class DarkCowRenderer extends MobEntityRenderer<DarkCowEntity, CowEntityModel<DarkCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/dark_cow.png");
    public DarkCowRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(MeadowClient.DARK_COW_MODEL_LAYER)), 0.7f);
        this.addFeature(new EyeBlinkRenderer<>(this, "dark_cow", "cow"));
    }

    @Override
    public Identifier getTexture(DarkCowEntity cowEntity) {
        return TEXTURE;
    }
}
