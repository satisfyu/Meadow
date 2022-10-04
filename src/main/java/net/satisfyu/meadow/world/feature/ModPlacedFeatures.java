package net.satisfyu.meadow.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> ALPINE_FLOWER_1 = PlacedFeatures.register("alpine_flower_1_placed",
            ModConfiguredFeatures.ALPINE_FLOWER_1, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALPINE_FLOWER_2 = PlacedFeatures.register("alpine_flower_2_placed",
            ModConfiguredFeatures.ALPINE_FLOWER_2, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALPINE_FLOWER_3 = PlacedFeatures.register("alpine_flower_3_placed",
            ModConfiguredFeatures.ALPINE_FLOWER_3, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALPINE_FLOWER_4 = PlacedFeatures.register("alpine_flower_4_placed",
            ModConfiguredFeatures.ALPINE_FLOWER_4, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ERIOPHORUM = PlacedFeatures.register("eriophorum_placed",
            ModConfiguredFeatures.ERIOPHORUM, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALPINE_GRASS = PlacedFeatures.register("alpine_grass_placed",
            ModConfiguredFeatures.ALPINE_GRASS, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ALPINE_GRASS_TALL = PlacedFeatures.register("alpine_grass_tall_placed",
            ModConfiguredFeatures.ALPINE_GRASS_TALL, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> SMALL_FIR = PlacedFeatures.register("small_fir_placed",
            ModConfiguredFeatures.SMALL_FIR, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());



    public static final RegistryEntry<PlacedFeature> PINE_PLACED = PlacedFeatures.register("pine_placed",
            ModConfiguredFeatures.PINE_SPAWN,
            VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));


    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }
    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }

}
