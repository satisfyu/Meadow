package net.satisfyu.meadow.painting;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Supplier;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(Meadow.MOD_ID, Registry.PAINTING_VARIANT_KEY);

    public static final RegistrySupplier<PaintingVariant> SUNSET = registerPainting("sunset", () -> new PaintingVariant(16, 16));
    public static final RegistrySupplier<PaintingVariant> PLANT = registerPainting("plant", () -> new PaintingVariant(16, 16));
    public static final RegistrySupplier<PaintingVariant> WANDERER = registerPainting("wanderer", () -> new PaintingVariant(16, 16));
    public static final RegistrySupplier<PaintingVariant> GRAHAM = registerPainting("graham", () -> new PaintingVariant(16, 32));
    public static final RegistrySupplier<PaintingVariant> SEA = registerPainting("sea", () -> new PaintingVariant(64, 48));
    public static final RegistrySupplier<PaintingVariant> WASTELAND = registerPainting("wasteland", () -> new PaintingVariant(32, 32));
    public static final RegistrySupplier<PaintingVariant> BUST = registerPainting("bust", () -> new PaintingVariant(32, 32));
    
    private static RegistrySupplier<PaintingVariant> registerPainting(String name, Supplier<PaintingVariant> paintingVariant) {
        return PAINTINGS.register(name, paintingVariant);
    }

    public static void init() {
        Meadow.LOGGER.debug("Registering Paintings for " + Meadow.MOD_ID);
        PAINTINGS.register();
    }
}
