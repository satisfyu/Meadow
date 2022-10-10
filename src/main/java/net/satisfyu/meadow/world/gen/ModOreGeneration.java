package net.satisfyu.meadow.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.satisfyu.meadow.world.feature.ModPlacedFeatures;

public class ModOreGeneration {

    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_LOWER.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_UPPER.getKey().get());


        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SALT_MIDDLE.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SALT_UPPER.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SALT_SMALL.getKey().get());
    }
}
