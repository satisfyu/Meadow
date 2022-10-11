package net.satisfyu.meadow.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.world.feature.ModPlacedFeatures;
import net.satisfyu.meadow.world.gen.ModOreGeneration;
import terrablender.api.Regions;

import static net.satisfyu.meadow.mixin.OverworldDefaultFeaturesMixin.callAddBasicFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> MEADOW_FOREST_KEY = register("meadow_forest");
    public static final Biome MEADOW_FOREST = createMeadowForest();
    public static final RegistryKey<Biome> MEADOW_CLEARING_KEY = register("meadow_clearing");
    public static final Biome MEADOW_CLEARING = createMeadowClearing();
    
    public static void initialize() {
        modifyExistingBiome();
        BuiltinRegistries.add(BuiltinRegistries.BIOME, MEADOW_FOREST_KEY.getValue(), MEADOW_FOREST);
        BuiltinRegistries.add(BuiltinRegistries.BIOME, MEADOW_CLEARING_KEY.getValue(), MEADOW_CLEARING);
    }

    public static void initializeTerraBlender() {
        Regions.register(new ModRegion(Identifier.of(Meadow.MOD_ID, "overworld"), 2));
    }
    
    private static void modifyExistingBiome() {
        ModOreGeneration.generateOres();
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_GRASS.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_GRASS_TALL.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PINE_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_1.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_2.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_3.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_4.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ERIOPHORUM.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SMALL_FIR.getKey().get());
    }

    private static void applyDefaults(SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, Biome.Builder biome) {
        callAddBasicFeatures(generationSettings);



        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);

        ModOreGeneration.addOres(generationSettings);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_GRASS_TALL);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PINE_PLACED);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_1);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_2);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_3);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ALPINE_FLOWER_4);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SMALL_FIR);






        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 8, 8));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 8, 8));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 8, 8));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 8, 8));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4));

        DefaultBiomeFeatures.addPlainsMobs(spawnSettings);




        BiomeEffects.Builder effects = new BiomeEffects.Builder();
        effects.skyColor(7907327);
        effects.fogColor(12638463);
        effects.waterColor(3557290);
        effects.waterFogColor(4501493);
        effects.grassColor(5866311);
        effects.foliageColor(5276209);
        effects.music(new MusicSound(new SoundEvent(Identifier.of("minecraft", "music.overworld.meadow")), 12000, 24000, false));

        biome.precipitation(Biome.Precipitation.RAIN)
                .temperature(0.5f)
                .downfall(0.3f)
                .effects(effects.build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build());
    }

    private static Biome createMeadowForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        Biome.Builder biome = new Biome.Builder();
        applyDefaults(spawnSettings, generationSettings, biome);

        return biome.build();
    }

    private static Biome createMeadowClearing() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        Biome.Builder biome = new Biome.Builder();
        applyDefaults(spawnSettings, generationSettings, biome);

        return biome.build();
    }

    private static RegistryKey<Biome> register(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(Meadow.MOD_ID, name));
    }
}
