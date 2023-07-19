package net.satisfyu.meadow.world;


import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.satisfyu.meadow.util.MeadowIdentifier;


public class MeadowPlacedFeature {

    public static final RegistryKey<PlacedFeature> FLOWERS_MEADOW_PATCH_KEY = registerKey("meadow_flowers");
    public static final RegistryKey<PlacedFeature> GRASS_PATCH_KEY = registerKey("grass_patch");
    public static final RegistryKey<PlacedFeature> TREES_MEADOW_KEY = registerKey("meadow_trees");
    public static final RegistryKey<PlacedFeature> BUSH_KEY = registerKey("bush_checked");
    public static final RegistryKey<PlacedFeature> MEADOW_BOULDERS_KEY = registerKey("meadow_boulders");
    public static final RegistryKey<PlacedFeature> LIMESTONE_SLAB_KEY = registerKey("limestone_slab");


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new MeadowIdentifier(name));
    }
}

