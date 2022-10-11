package net.satisfyu.meadow.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

public class ModOrePlacedFeatures {

    public static final RegistryEntry<PlacedFeature> ORE_LIMESTONE_UPPER = PlacedFeatures.register("ore_limestone_upper", ModOreConfiguredFeatures.ORE_LIMESTONE, CountPlacementModifier.of(90), HeightRangePlacementModifier.uniform(YOffset.fixed(64), YOffset.fixed(128)));
    public static final RegistryEntry<PlacedFeature> ORE_LIMESTONE_LOWER = PlacedFeatures.register("ore_limestone_lower", ModOreConfiguredFeatures.ORE_LIMESTONE, CountPlacementModifier.of(2), HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60)));

    public static final RegistryEntry<PlacedFeature> ORE_SALT_UPPER = PlacedFeatures.register("ore_salt_upper", ModOreConfiguredFeatures.ORE_SALT, CountPlacementModifier.of(225), HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384)));
    public static final RegistryEntry<PlacedFeature> ORE_SALT_MIDDLE = PlacedFeatures.register("ore_salt_middle", ModOreConfiguredFeatures.ORE_SALT, CountPlacementModifier.of(25), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56)));
    public static final RegistryEntry<PlacedFeature> ORE_SALT_SMALL = PlacedFeatures.register("ore_salt_small", ModOreConfiguredFeatures.ORE_SALT_SMALL, CountPlacementModifier.of(25), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72)));
}
