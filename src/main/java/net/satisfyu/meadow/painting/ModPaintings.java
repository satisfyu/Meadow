package net.satisfyu.meadow.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;

public class ModPaintings {
    public static final PaintingVariant SUNSET = registerPainting("sunset", new PaintingVariant(16, 16));
    public static final PaintingVariant PLANT = registerPainting("plant", new PaintingVariant(16, 16));
    public static final PaintingVariant WANDERER = registerPainting("wanderer", new PaintingVariant(16, 16));
    public static final PaintingVariant GRAHAM = registerPainting("graham", new PaintingVariant(16, 32));
    public static final PaintingVariant SEA = registerPainting("sea", new PaintingVariant(48, 48));
    public static final PaintingVariant WASTELAND = registerPainting("wasteland", new PaintingVariant(32, 32));
    public static final PaintingVariant BUST = registerPainting("bust", new PaintingVariant(32, 32));


    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(Meadow.MOD_ID, name), paintingVariant);
    }

    public static void registerPaintings() {
        Meadow.LOGGER.debug("Registering Paintings for " + Meadow.MOD_ID);
    }
}
