package net.satisfyu.meadow.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.util.MeadowIdentifier;
import net.satisfyu.meadow.world.feature.CobbledLimestoneRock;

import java.util.function.Supplier;

public class FeatureRegistry {
    public static DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Meadow.MOD_ID, Registries.FEATURE);
    public static final RegistrySupplier<Feature<?>> COBBLED_LIMESTONE_ROCK = create("cobbled_limestone_rock", CobbledLimestoneRock::new);

    public static void init() {
        Meadow.LOGGER.debug("Registering the ModFeatures for " + Meadow.MOD_ID);
        FEATURES.register();
    }

    private static RegistrySupplier<Feature<?>> create(String name, Supplier<? extends Feature<?>> supplier) {
        return FEATURES.register(new MeadowIdentifier(name), CobbledLimestoneRock::new);
    }
}
