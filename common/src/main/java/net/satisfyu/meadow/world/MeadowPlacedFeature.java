package net.satisfyu.meadow.world;


import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.satisfyu.meadow.util.MeadowIdentifier;


public class MeadowPlacedFeature {

    public static final ResourceKey<PlacedFeature> FLOWERS_MEADOW_PATCH_KEY = registerKey("meadow_flowers");
    public static final ResourceKey<PlacedFeature> GRASS_PATCH_KEY = registerKey("grass_patch");
    public static final ResourceKey<PlacedFeature> TREES_MEADOW_KEY = registerKey("meadow_trees");
    public static final ResourceKey<PlacedFeature> BUSH_KEY = registerKey("bush_checked");
    public static final ResourceKey<PlacedFeature> MEADOW_BOULDERS_KEY = registerKey("meadow_boulders");
    public static final ResourceKey<PlacedFeature> LIMESTONE_SLAB_KEY = registerKey("limestone_slab");


    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new MeadowIdentifier(name));
    }
}

