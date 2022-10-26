package net.satisfyu.meadow.entity.custom.bear.brown;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PolarBearEntityRenderer;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

public class BrownBearRenderer extends PolarBearEntityRenderer {

    private static final Identifier TEXTURE = new Identifier(Meadow.MOD_ID, "textures/entity/brown_bear/brown_bear.png");

    public BrownBearRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PolarBearEntity polarBearEntity) {
        return TEXTURE;
    }
}
