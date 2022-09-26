package net.satisfyu.meadow.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;

public class ModPaintings {
    public static final PaintingVariant RUETI = registerPainting("rueti", new PaintingVariant(16, 16));
    public static final PaintingVariant BERNE = registerPainting("berne", new PaintingVariant(16, 16));
    public static final PaintingVariant LIMMAT = registerPainting("limmat", new PaintingVariant(16, 16));
    public static final PaintingVariant GENEVA = registerPainting("geneva", new PaintingVariant(16, 16));
    public static final PaintingVariant ZUERICH = registerPainting("zuerich", new PaintingVariant(48, 48));
    public static final PaintingVariant MATTERHORN = registerPainting("matterhorn", new PaintingVariant(32, 32));
    public static final PaintingVariant LUCERNE = registerPainting("lucerne", new PaintingVariant(16, 32));


    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(Meadow.MOD_ID, name), paintingVariant);
    }

    public static void registerPaintings() {
        Meadow.LOGGER.debug("Registering Paintings for " + Meadow.MOD_ID);
    }
}
