package net.satisfyu.meadow.world;


import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.satisfyu.meadow.util.MeadowIdentifier;


public class MeadowPlacedFeature {


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new MeadowIdentifier(name));
    }
}

