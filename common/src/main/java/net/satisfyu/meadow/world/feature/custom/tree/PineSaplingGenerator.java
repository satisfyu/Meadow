package net.satisfyu.meadow.world.feature.custom.tree;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.satisfyu.meadow.util.GeneralUtil;
import org.jetbrains.annotations.Nullable;

public class PineSaplingGenerator extends DynamicSaplingGenerator {

    public static final RegistryKey<ConfiguredFeature<?, ?>> NORMAL = GeneralUtil.configuredFeatureKey("pine");

    @Override
    protected @Nullable RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(ServerWorld world, Random var1, boolean bees) {
        Registry<ConfiguredFeature<?, ?>> registry = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY);
        return registry.getEntry(NORMAL).orElse(null);
    }



}