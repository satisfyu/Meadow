package net.satisfyu.meadow.util;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.satisfyu.meadow.Meadow;

public class GeneralUtil {

    public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String name) {
        return RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(Meadow.MOD_ID, name));
    }
}
