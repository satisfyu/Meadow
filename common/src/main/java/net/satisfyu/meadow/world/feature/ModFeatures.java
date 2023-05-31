package net.satisfyu.meadow.world.feature;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.MeadowIdentifier;
import net.satisfyu.meadow.world.feature.custom.CobbledLimestoneRock;

import java.util.function.Supplier;

public class ModFeatures {
    public static DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Meadow.MOD_ID, Registry.FEATURE_KEY);
    public static final RegistrySupplier<Feature<?>> COBBLED_LIMESTONE_ROCK = create("cobbled_limestone_rock", CobbledLimestoneRock::new);
    
    public static void init(){
        Meadow.LOGGER.debug("Registering the ModFeatures for " + Meadow.MOD_ID);
        FEATURES.register();
    }

    private static RegistrySupplier<Feature<?>> create(String name, Supplier<? extends Feature<?>> supplier) {
        return FEATURES.register(new MeadowIdentifier(name), CobbledLimestoneRock::new);
    }
}
