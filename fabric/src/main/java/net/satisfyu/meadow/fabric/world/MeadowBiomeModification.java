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
        Predicate<BiomeSelectionContext> meadowForestBiomes = getMeadowSelector("meadow_forest_biomes");
        Predicate<BiomeSelectionContext> meadowClearingBiomes = getMeadowSelector("meadow_clearing_biomes");

        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, BIRCH_PINE_CHECKED_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, BIRCH_PINE_HANGING_CHECKED_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_FLOWERS));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_TREES));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_TREES));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, OAK_PINE_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, OLD_BIRCH_TREES_BEES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, OLD_BIRCH_TREES_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ERIOPHORUM_TALL));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_CLEARING_TREES));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, OAK_PINE_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PINE_HANGING_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, SPRUCE_PINE_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TALL_FERN));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, BUSH_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, COBBLED_LIMESTONE_ROCK));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ERIOPHORUM_TALL));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, FIR));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, FOREST_ROCK_LIMESTONE));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_FERN));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_FOREST_TREES));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ORANGE_SPRUCE_PINE_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PINE_CHECKED));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, SMALL_FIR));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_LIMESTONE_LOWER));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_LIMESTONE_UPPER));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_SALT_LOWER));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_SALT_SMALL));
        world.add(ModificationPhase.ADDITIONS, meadowForestBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_SALT_UPPER));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_LIMESTONE_LOWER));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_LIMESTONE_UPPER));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_SALT_LOWER));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_SALT_SMALL));
        world.add(ModificationPhase.ADDITIONS, meadowClearingBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_SALT_UPPER));

    }

    private static Predicate<BiomeSelectionContext> getMeadowSelector(String path) {
        return BiomeSelectors.tag(TagKey.of(Registry.BIOME_KEY, new MeadowIdentifier(path)));
    }



}
