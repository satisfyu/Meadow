package net.satisfyu.meadow.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.satisfyu.meadow.Meadow;

public class MeadowUtil {
    public static final EnumProperty<ShutterType> SHUTTER_TYPE;

    public static ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Meadow.MOD_ID, name));
    }

    static {
        SHUTTER_TYPE = EnumProperty.create("type", ShutterType.class);
    }
}
