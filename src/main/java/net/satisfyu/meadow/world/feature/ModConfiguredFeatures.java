package net.satisfyu.meadow.world.feature;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {


    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINE_TREE =
            ConfiguredFeatures.register("pine_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.PINE_LOG),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.PINE_LEAVES),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final RegistryEntry<PlacedFeature> PINE_CHECKED = PlacedFeatures.register("pine_checked",
            ModConfiguredFeatures.PINE_TREE, List.of(PlacedFeatures.wouldSurvive(ModBlocks.PINE_SAPLING)));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PINE_SPAWN =
            ConfiguredFeatures.register("pine_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(PINE_CHECKED, 0.5f)),
                            PINE_CHECKED));

    public static void registerConfiguredFeatures() {
        Meadow.LOGGER.debug("Registering the ModConfiguredFeatures for " + Meadow.MOD_ID);
    }

}
