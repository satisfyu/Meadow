package net.satisfyu.meadow.fabric.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.tag.BiomeTags;
import net.satisfyu.meadow.fabric.MeadowFabric;
import net.satisfyu.meadow.fabric.client.MeadowFabricSpawnConfig;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.TagRegistry;

import java.util.function.Predicate;

public class FeatureModification {

    public static void init() {
        MeadowFabricSpawnConfig config = MeadowFabric.config.get();

        Predicate<BiomeSelectionContext> nether = (ctx -> ctx.hasTag(BiomeTags.IS_NETHER) || ctx.hasTag(BiomeTags.NETHER_FOSSIL_HAS_STRUCTURE) || ctx.hasTag(BiomeTags.NETHER_FORTRESS_HAS_STRUCTURE));
        Predicate<BiomeSelectionContext> bear = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.WOODLAND_MANSION_HAS_STRUCTURE) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> sheep = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE) || ctx.hasTag(BiomeTags.IS_HILL) || ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> cow = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE) || ctx.hasTag(BiomeTags.IS_HILL) || ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> mountain = (ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_HILL));
        Predicate<BiomeSelectionContext> sunset_cow = (ctx -> ctx.hasTag(BiomeTags.IS_SAVANNA) || ctx.hasTag(BiomeTags.VILLAGE_SAVANNA_HAS_STRUCTURE));
        Predicate<BiomeSelectionContext> buffalo = (ctx -> ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_SAVANNA));
        Predicate<BiomeSelectionContext> dark_cow = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.WOODLAND_MANSION_HAS_STRUCTURE));
        Predicate<BiomeSelectionContext> meadow = (ctx -> ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(BiomeTags.WOODLAND_MANSION_HAS_STRUCTURE));

        BiomeModifications.addSpawn(meadow, SpawnGroup.CREATURE, EntityRegistry.DAIRY_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, SpawnGroup.CREATURE, EntityRegistry.COOKIE_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(cow, SpawnGroup.CREATURE, EntityRegistry.CREAM_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(cow, SpawnGroup.CREATURE, EntityRegistry.PINTO_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(dark_cow, SpawnGroup.CREATURE, EntityRegistry.UMBRA_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(dark_cow, SpawnGroup.CREATURE, EntityRegistry.DARK_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(bear, SpawnGroup.CREATURE, EntityRegistry.ALBINO_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(sunset_cow, SpawnGroup.CREATURE, EntityRegistry.FLECKED_SHEEP.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, SpawnGroup.CREATURE, EntityRegistry.FUZZY_SHEEP.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(sheep, SpawnGroup.CREATURE, EntityRegistry.INKY_SHEEP.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(sheep, SpawnGroup.CREATURE, EntityRegistry.PATCHED_SHEEP.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, SpawnGroup.CREATURE, EntityRegistry.HIGHLAND_CATTLE.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(mountain, SpawnGroup.CREATURE, EntityRegistry.ROCKY_SHEEP.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(mountain, SpawnGroup.CREATURE, EntityRegistry.HORNED_SHEEP.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, SpawnGroup.CREATURE, EntityRegistry.MEADOW_CHICKEN.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(nether, SpawnGroup.CREATURE, EntityRegistry.WARPED_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(bear, SpawnGroup.CREATURE, EntityRegistry.BROWN_BEAR.get(),
                config.spawning.bearSpawnWeight, config.spawning.bearPackSizeMin, config.spawning.bearPackSizeMax);

        BiomeModifications.addSpawn(buffalo, SpawnGroup.CREATURE, EntityRegistry.WATER_BUFFALO.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);

        BiomeModifications.addSpawn(sunset_cow, SpawnGroup.CREATURE, EntityRegistry.SUNSET_COW.get(),
                config.spawning.meadowSpawnWeight, config.spawning.meadowPackSizeMin, config.spawning.meadowPackSizeMax);
    }
}
