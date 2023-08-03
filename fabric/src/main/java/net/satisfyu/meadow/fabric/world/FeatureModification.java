package net.satisfyu.meadow.fabric.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.TagRegistry;

import java.util.function.Predicate;

public class FeatureModification {

    public static void init() {
        int meadowSpawnWeight = 15;
        int meadowPackSizeMin = 4;
        int meadowPackSizeMax = 5;

        int bearSpawnWeight = 5;
        int bearPackSizeMin = 2;
        int bearPackSizeMax = 3;

        Predicate<BiomeSelectionContext> nether = (ctx -> ctx.hasTag(BiomeTags.IS_NETHER) || ctx.hasTag(BiomeTags.HAS_NETHER_FOSSIL) || ctx.hasTag(BiomeTags.HAS_NETHER_FORTRESS));
        Predicate<BiomeSelectionContext> bear = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.HAS_WOODLAND_MANSION) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> sheep = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.HAS_VILLAGE_PLAINS) || ctx.hasTag(BiomeTags.IS_HILL) || ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> cow = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.HAS_VILLAGE_PLAINS) || ctx.hasTag(BiomeTags.IS_HILL) || ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> mountain = (ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_HILL));
        Predicate<BiomeSelectionContext> sunset_cow = (ctx -> ctx.hasTag(BiomeTags.IS_SAVANNA) || ctx.hasTag(BiomeTags.HAS_VILLAGE_SAVANNA));
        Predicate<BiomeSelectionContext> buffalo = (ctx -> ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_SAVANNA));
        Predicate<BiomeSelectionContext> dark_cow = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.HAS_WOODLAND_MANSION));
        Predicate<BiomeSelectionContext> meadow = (ctx -> ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(BiomeTags.HAS_WOODLAND_MANSION));

        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.DAIRY_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.COOKIE_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(cow, MobCategory.CREATURE, EntityRegistry.CREAM_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(cow, MobCategory.CREATURE, EntityRegistry.PINTO_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(dark_cow, MobCategory.CREATURE, EntityRegistry.UMBRA_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(dark_cow, MobCategory.CREATURE, EntityRegistry.DARK_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(bear, MobCategory.CREATURE, EntityRegistry.ALBINO_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(sunset_cow, MobCategory.CREATURE, EntityRegistry.FLECKED_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.FUZZY_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(sheep, MobCategory.CREATURE, EntityRegistry.INKY_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(sheep, MobCategory.CREATURE, EntityRegistry.PATCHED_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.HIGHLAND_CATTLE.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(mountain, MobCategory.CREATURE, EntityRegistry.ROCKY_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(mountain, MobCategory.CREATURE, EntityRegistry.HORNED_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.MEADOW_CHICKEN.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(nether, MobCategory.CREATURE, EntityRegistry.WARPED_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(bear, MobCategory.CREATURE, EntityRegistry.BROWN_BEAR.get(),
                bearSpawnWeight, bearPackSizeMin, bearPackSizeMax);

        BiomeModifications.addSpawn(buffalo, MobCategory.CREATURE, EntityRegistry.WATER_BUFFALO.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(sunset_cow, MobCategory.CREATURE, EntityRegistry.SUNSET_COW.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
    }
}
