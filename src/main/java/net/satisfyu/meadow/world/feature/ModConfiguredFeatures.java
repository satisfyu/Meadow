package net.satisfyu.meadow.world.feature;

import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> DELPHINIUM =
            ConfiguredFeatures.register("delphinium", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.DELPHINIUM)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ALPINE_POPPY =
            ConfiguredFeatures.register("alpine_poppy", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ALPINE_POPPY)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SAXIFRAGE =
            ConfiguredFeatures.register("saxifrage", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SAXIFRAGE)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ENZIAN =
            ConfiguredFeatures.register("enzian", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ENZIAN)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> TALL_ERIOPHORUM =
            ConfiguredFeatures.register("tall_eriophorum", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ENZIAN)))));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FIRE_LILY =
            ConfiguredFeatures.register("fire_lily", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FIRE_LILY)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ERIOPHORUM =
            ConfiguredFeatures.register("eriophorum", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ERIOPHORUM)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ALPINE_GRASS =
            ConfiguredFeatures.register("alpine_grass", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ALPINE_GRASS)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ALPINE_GRASS_TALL =
            ConfiguredFeatures.register("alpine_grass_tall", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ALPINE_GRASS_TALL)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SMALL_FIR =
            ConfiguredFeatures.register("small_fir", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SMALL_FIR)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_MOUNTAIN_LAVENDER =
            ConfiguredFeatures.register("wild_mountain_lavender", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(16, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_MOUNTAIN_LAVENDER)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_JUNIPER =
            ConfiguredFeatures.register("wild_juniper", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(16, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_JUNIPER)))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_YARROW =
            ConfiguredFeatures.register("wild_yarrow", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(16, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_YARROW)))));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> PINE_TREE =
            ConfiguredFeatures.register("pine_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.PINE_LOG),
                    new StraightTrunkPlacer(6, 4, 0),
                    BlockStateProvider.of(ModBlocks.PINE_LEAVES),
                    new SpruceFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
                    new TwoLayersFeatureSize(2, 0, 2)).build());

    public static final RegistryEntry<PlacedFeature> DOGWOOD_CHECKED = PlacedFeatures.register("dogwood_checked",
            ModConfiguredFeatures.PINE_TREE, List.of(PlacedFeatures.wouldSurvive(ModBlocks.PINE_SAPLING)));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> PINE_SPAWN =
            ConfiguredFeatures.register("pine_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(DOGWOOD_CHECKED, 0.5f)),
                            DOGWOOD_CHECKED));




    public static void registerConfiguredFeatures() {
        Meadow.LOGGER.debug("Registering the ModConfiguredFeatures for " + Meadow.MOD_ID);
    }

}
