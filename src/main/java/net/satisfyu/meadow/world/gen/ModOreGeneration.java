package net.satisfyu.meadow.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.satisfyu.meadow.world.feature.ModOrePlacedFeatures;

public class ModOreGeneration {

    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_LIMESTONE_LOWER.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_LIMESTONE_UPPER.getKey().get());


        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SALT_MIDDLE.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SALT_UPPER.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SALT_SMALL.getKey().get());
    }

    public static void addOres(GenerationSettings.Builder generationSettings) {
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_LIMESTONE_LOWER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_LIMESTONE_UPPER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SALT_MIDDLE);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SALT_UPPER);
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModOrePlacedFeatures.ORE_SALT_SMALL);
    }
}
