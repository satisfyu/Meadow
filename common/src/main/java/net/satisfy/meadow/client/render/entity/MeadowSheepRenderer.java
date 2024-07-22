package net.satisfy.meadow.client.render.entity;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.meadow.client.model.MeadowSheepModel;
import net.satisfy.meadow.entity.MeadowSheep;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

public class MeadowSheepRenderer extends MobRenderer<MeadowSheep, MeadowSheepModel<MeadowSheep>> {
    private static final ResourceLocation FLECKED_WOOL_TEXTURE = new MeadowIdentifier("textures/entity/sheep/flecked_sheep.png");
    private static final ResourceLocation PATCHED_WOOL_TEXTURE = new MeadowIdentifier("textures/entity/sheep/patched_sheep.png");
    private static final ResourceLocation ROCKY_WOOL_TEXTURE = new MeadowIdentifier("textures/entity/sheep/rocky_sheep.png");
    private static final ResourceLocation INKY_WOOL_TEXTURE = new MeadowIdentifier("textures/entity/sheep/inky_sheep.png");

    public MeadowSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new MeadowSheepModel<>(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new MeadowSheepFurLayer(this, context));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MeadowSheep entity) {
        byte woolType = entity.getWoolType();
        MeadowSheep.SheepTexture texture = determineTexture(woolType);
        entity.setSheepTexture(texture); // Stellen Sie sicher, dass die Texturinformation in der Entität gesetzt wird
        return switch (texture) {
            case PATCHED -> PATCHED_WOOL_TEXTURE;
            case ROCKY -> ROCKY_WOOL_TEXTURE;
            case INKY -> INKY_WOOL_TEXTURE;
            default -> FLECKED_WOOL_TEXTURE;
        };
    }

    private MeadowSheep.SheepTexture determineTexture(byte woolType) {
        return switch (woolType) {
            case 1 -> MeadowSheep.SheepTexture.PATCHED;
            case 2 -> MeadowSheep.SheepTexture.ROCKY;
            case 3 -> MeadowSheep.SheepTexture.INKY;
            default -> MeadowSheep.SheepTexture.FLECKED;
        };
    }
}
