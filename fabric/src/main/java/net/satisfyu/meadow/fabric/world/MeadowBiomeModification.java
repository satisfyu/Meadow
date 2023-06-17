package net.satisfyu.meadow.fabric.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Predicate;

import static net.satisfyu.meadow.world.MeadowPlacedFeature.*;


public class MeadowBiomeModification {

    public static void init() {
        BiomeModification world = BiomeModifications.create(new MeadowIdentifier("world_features"));
        Predicate<BiomeSelectionContext> meadowBiomes = getMeadowSelector("meadow_biomes");

        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, FLOWERS_MEADOW_PATCH_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, GRASS_PATCH_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, GRASS_PATCH_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TREES_MEADOW_KEY));

    }

    private static Predicate<BiomeSelectionContext> getMeadowSelector(String path) {
        return BiomeSelectors.tag(TagKey.of(Registry.BIOME_KEY, new MeadowIdentifier(path)));
    }


}
