package net.satisfyu.meadow.fabric.world;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Predicate;

import static net.satisfyu.meadow.world.MeadowPlacedFeature.*;


public class MeadowBiomeModification {

    public static void init() {
        BiomeModification world = BiomeModifications.create(new MeadowIdentifier("world_features"));
        Predicate<BiomeSelectionContext> meadowBiomes = getMeadowSelector("meadow_biomes");

        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FLOWERS_MEADOW_PATCH_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GRASS_PATCH_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TREES_MEADOW_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BUSH_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MEADOW_BOULDERS_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, LIMESTONE_SLAB_KEY));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getEffects().setGrassColor(9286496));
        world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getEffects().setFoliageColor(5866311));

        //world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, LIMESTONE_ORE));
        //world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SALT_ORE_LOWER));
        //world.add(ModificationPhase.ADDITIONS, meadowBiomes, ctx -> ctx.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, SALT_ORE_UPPER));
    }

    private static Predicate<BiomeSelectionContext> getMeadowSelector(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, new MeadowIdentifier(path)));
    }


}