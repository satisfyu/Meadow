package net.satisfyu.meadow.world;


import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.satisfyu.meadow.util.MeadowIdentifier;


public class MeadowPlacedFeature {
    public static final RegistryKey<PlacedFeature> BIRCH_PINE_CHECKED_KEY = registerKey("birch_pine_checked");
    public static final RegistryKey<PlacedFeature> BIRCH_PINE_HANGING_CHECKED_KEY = registerKey("birch_pine_hanging_checked");
    public static final RegistryKey<PlacedFeature> BUSH_CHECKED = registerKey("bush_checked");
    public static final RegistryKey<PlacedFeature> COBBLED_LIMESTONE_ROCK = registerKey("cobbled_limestone_rock");
    public static final RegistryKey<PlacedFeature> ERIOPHORUM_TALL = registerKey("eriophorum_tall");
    public static final RegistryKey<PlacedFeature> FIR = registerKey("fir");
    public static final RegistryKey<PlacedFeature> FOREST_ROCK_LIMESTONE = registerKey("forest_rock_limestone");
    public static final RegistryKey<PlacedFeature> MEADOW_CLEARING_TREES = registerKey("meadow_clearing_trees");
    public static final RegistryKey<PlacedFeature> MEADOW_FERN = registerKey("meadow_fern");
    public static final RegistryKey<PlacedFeature> MEADOW_FLOWERS = registerKey("meadow_flowers");
    public static final RegistryKey<PlacedFeature> MEADOW_FOREST_TREES = registerKey("meadow_forest_trees");
    public static final RegistryKey<PlacedFeature> MEADOW_TREES = registerKey("meadow_trees");
    public static final RegistryKey<PlacedFeature> OAK_PINE_CHECKED = registerKey("oak_pine_checked");
    public static final RegistryKey<PlacedFeature> OLD_BIRCH_TREES_CHECKED = registerKey("old_birch_trees_checked");
    public static final RegistryKey<PlacedFeature> OLD_BIRCH_TREES_BEES_CHECKED = registerKey("old_birch_trees_bees_checked");
    public static final RegistryKey<PlacedFeature> ORANGE_SPRUCE_PINE_CHECKED = registerKey("orange_spruce_pine_checked");
    public static final RegistryKey<PlacedFeature> PINE_CHECKED = registerKey("pine_checked");
    public static final RegistryKey<PlacedFeature> PINE_HANGING_CHECKED = registerKey("pine_hanging_checked");
    public static final RegistryKey<PlacedFeature> SMALL_FIR = registerKey("small_fir");
    public static final RegistryKey<PlacedFeature> TALL_FERN = registerKey("tall_fern");
    public static final RegistryKey<PlacedFeature> SPRUCE_PINE_CHECKED = registerKey("spruce_pine_checked");
    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE_LOWER = registerKey("ore_limestone_lower");
    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE_UPPER = registerKey("ore_limestone_upper");
    public static final RegistryKey<PlacedFeature> ORE_SALT_LOWER = registerKey("ore_salt_lower");
    public static final RegistryKey<PlacedFeature> ORE_SALT_SMALL = registerKey("ore_salt_small");
    public static final RegistryKey<PlacedFeature> ORE_SALT_UPPER = registerKey("ore_salt_upper");

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new MeadowIdentifier(name));
    }
}

