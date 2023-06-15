package net.satisfyu.meadow.entity.cow.dairy_cow;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.client.MeadowClient;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class DairyCowRenderer extends MobEntityRenderer<DairyCowEntity, CowEntityModel<DairyCowEntity>> {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/entity/cow/dairy_cow.png");
    public DairyCowRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel<>(context.getPart(MeadowClient.DAIRY_COW_MODEL_LAYER)), 0.7f);
    }

    @Override
    public Identifier getTexture(DairyCowEntity cowEntity) {
        return TEXTURE;
    }
}
