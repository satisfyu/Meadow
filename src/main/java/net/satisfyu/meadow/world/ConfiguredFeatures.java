package net.satisfyu.meadow.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.List;
import java.util.function.Predicate;

public class ConfiguredFeatures {


    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_YARROW_PATCH =
            net.minecraft.world.gen.feature.ConfiguredFeatures.register(MeadowIdentifier.asString("wild_yarrow"), Feature.RANDOM_PATCH, net.minecraft.world.gen.feature.ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_YARROW.getDefaultState())), List.of(Blocks.GRASS_BLOCK), 36));
    public static final RegistryEntry<PlacedFeature> WILD_YARROW_PATCH_CHANCE = PlacedFeatures.register(MeadowIdentifier.asString("wild_yarrow_patch_chance"), WILD_YARROW_PATCH, RarityFilterPlacementModifier.of(92), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());


    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> JUNIPER_WILD_PATCH =
            net.minecraft.world.gen.feature.ConfiguredFeatures.register(MeadowIdentifier.asString("juniper_wild_patch"), Feature.RANDOM_PATCH, net.minecraft.world.gen.feature.ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_JUNIPER.getDefaultState())), List.of(Blocks.GRASS_BLOCK), 36));
    public static final RegistryEntry<PlacedFeature> JUNIPER_WILD_PATCH_CHANCE = PlacedFeatures.register(MeadowIdentifier.asString("juniper_wild_patch_chance"), JUNIPER_WILD_PATCH, RarityFilterPlacementModifier.of(92), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());


    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LAVENDER_WILD_PATCH =
            net.minecraft.world.gen.feature.ConfiguredFeatures.register(MeadowIdentifier.asString("lavender_wild_patch"), Feature.RANDOM_PATCH, net.minecraft.world.gen.feature.ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_MOUNTAIN_LAVENDER.getDefaultState())), List.of(Blocks.GRASS_BLOCK), 36));
    public static final RegistryEntry<PlacedFeature> LAVENDER_WILD_PATCH_CHANCE = PlacedFeatures.register(MeadowIdentifier.asString("lavender_wild_patch_chance"), LAVENDER_WILD_PATCH, RarityFilterPlacementModifier.of(92), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FIR_PATCH =
            net.minecraft.world.gen.feature.ConfiguredFeatures.register(MeadowIdentifier.asString("fir_patch"), Feature.RANDOM_PATCH, net.minecraft.world.gen.feature.ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FIR.getDefaultState())), List.of(Blocks.GRASS_BLOCK), 36));
    public static final RegistryEntry<PlacedFeature> FIR_PATCH_CHANCE = PlacedFeatures.register(MeadowIdentifier.asString("fir_patch_chance"), FIR_PATCH, RarityFilterPlacementModifier.of(92), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());


    public static void init() {
        BiomeModification world = BiomeModifications.create(new MeadowIdentifier("world_features"));
        Predicate<BiomeSelectionContext> meadowWetBiomes = BiomeSelectors.includeByKey(ModRegion.MEADOW_CLEARING_KEY, ModRegion.MEADOW_FOREST_KEY);
        Predicate<BiomeSelectionContext> meadowDryBiomes = BiomeSelectors.includeByKey(BiomeKeys.MEADOW);


        world.add(ModificationPhase.ADDITIONS, meadowWetBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, FIR_PATCH_CHANCE .value()));
        world.add(ModificationPhase.ADDITIONS, meadowWetBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, JUNIPER_WILD_PATCH_CHANCE .value()));
        world.add(ModificationPhase.ADDITIONS, meadowDryBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, JUNIPER_WILD_PATCH_CHANCE .value()));
        world.add(ModificationPhase.ADDITIONS, meadowWetBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, WILD_YARROW_PATCH_CHANCE .value()));
        world.add(ModificationPhase.ADDITIONS, meadowDryBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, WILD_YARROW_PATCH_CHANCE .value()));
        world.add(ModificationPhase.ADDITIONS, meadowDryBiomes, ctx -> ctx.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, LAVENDER_WILD_PATCH_CHANCE.value()));

    }
}

