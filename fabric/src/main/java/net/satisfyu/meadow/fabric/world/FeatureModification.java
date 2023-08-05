package net.satisfyu.meadow.fabric.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import net.satisfyu.meadow.world.CommonSpawnUtil;

import java.util.function.Predicate;

public class FeatureModification {



    public static void init() {
        int meadowSpawnWeight = 3;
        int meadowPackSizeMin = 2;
        int meadowPackSizeMax = 3;

        int bearSpawnWeight = 5;
        int bearPackSizeMin = 2;
        int bearPackSizeMax = 3;

        Predicate<BiomeSelectionContext> bear = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.HAS_WOODLAND_MANSION) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> sheep = (ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.HAS_VILLAGE_PLAINS) || ctx.hasTag(BiomeTags.IS_HILL) || ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA));
        Predicate<BiomeSelectionContext> mountain = (ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_HILL));
        Predicate<BiomeSelectionContext> sunset_cow = (ctx -> ctx.hasTag(BiomeTags.IS_SAVANNA) || ctx.hasTag(BiomeTags.HAS_VILLAGE_SAVANNA));
        Predicate<BiomeSelectionContext> buffalo = (ctx -> ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_SAVANNA));
        Predicate<BiomeSelectionContext> meadow = (ctx -> ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(BiomeTags.HAS_WOODLAND_MANSION));

        Predicate<BiomeSelectionContext> meadowVarCows = (ctx -> ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(TagRegistry.SPAWNS_DARK_COW) ||
                ctx.hasTag(TagRegistry.SPAWNS_SUNSET_COW) || ctx.hasTag(TagRegistry.SPAWNS_BEAR));

        BiomeModifications.addSpawn(meadowVarCows, MobCategory.CREATURE, EntityType.COW, CommonSpawnUtil.cowSpawnWeight, CommonSpawnUtil.cowPackSizeMin, CommonSpawnUtil.cowPackSizeMax);

        Predicate<BiomeSelectionContext> shearableVarCows = (ctx -> ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(TagRegistry.SPAWNS_DARK_COW));
        BiomeModifications.addSpawn(shearableVarCows, MobCategory.CREATURE, EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get(),
                10, meadowPackSizeMin, meadowPackSizeMax);

        /*
        BiomeModifications.addSpawn(sunset_cow, MobCategory.CREATURE, EntityRegistry.FLECKED_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.FUZZY_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
        BiomeModifications.addSpawn(sheep, MobCategory.CREATURE, EntityRegistry.INKY_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
        BiomeModifications.addSpawn(sheep, MobCategory.CREATURE, EntityRegistry.PATCHED_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
        BiomeModifications.addSpawn(mountain, MobCategory.CREATURE, EntityRegistry.ROCKY_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
         */

        BiomeModifications.addSpawn(mountain, MobCategory.CREATURE, EntityRegistry.HORNED_SHEEP.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
        BiomeModifications.addSpawn(meadow, MobCategory.CREATURE, EntityRegistry.MEADOW_CHICKEN.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
        BiomeModifications.addSpawn(bear, MobCategory.CREATURE, EntityRegistry.BROWN_BEAR.get(),
                bearSpawnWeight, bearPackSizeMin, bearPackSizeMax);
        BiomeModifications.addSpawn(buffalo, MobCategory.CREATURE, EntityRegistry.WATER_BUFFALO.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
    }
}
