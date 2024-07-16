package net.satisfy.meadow.fabric.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.satisfy.meadow.registry.EntityRegistry;
import net.satisfy.meadow.registry.TagRegistry;
import net.satisfy.meadow.world.CommonSpawnUtil;

import java.util.function.Predicate;

public class FeatureModification {
    public static void init() {
        int meadowSpawnWeight = 3;
        int meadowPackSizeMin = 2;
        int meadowPackSizeMax = 3;


        Predicate<BiomeSelectionContext> buffalo = (ctx -> ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_SAVANNA));


        Predicate<BiomeSelectionContext> meadowVarCows = (ctx -> {
            if(BiomeSelectors.spawnsOneOf(EntityType.COW).test(ctx)) return false;
            return ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(TagRegistry.SPAWNS_DARK_COW) || ctx.hasTag(TagRegistry.SPAWNS_SUNSET_COW) || ctx.hasTag(TagRegistry.SPAWNS_BEAR);
        });
        BiomeModifications.addSpawn(meadowVarCows, MobCategory.CREATURE, EntityType.COW,
                CommonSpawnUtil.cowSpawnWeight, CommonSpawnUtil.cowPackSizeMin, CommonSpawnUtil.cowPackSizeMax);

        Predicate<BiomeSelectionContext> meadowVarChicken = (ctx -> {
            if(BiomeSelectors.spawnsOneOf(EntityType.CHICKEN).test(ctx)) return false;
            return ctx.hasTag(TagRegistry.IS_MEADOW);
        });
        BiomeModifications.addSpawn(meadowVarChicken, MobCategory.CREATURE, EntityType.CHICKEN,
                CommonSpawnUtil.chickenSpawnWeight, CommonSpawnUtil.chickenPackSizeMin, CommonSpawnUtil.chickenPackSizeMax);

        Predicate<BiomeSelectionContext> meadowVarSheep = (ctx -> {
            if(BiomeSelectors.spawnsOneOf(EntityType.SHEEP).test(ctx)) return false;
            return ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(TagRegistry.SPAWNS_SUNSET_COW) || ctx.hasTag(TagRegistry.SPAWNS_ROCKY_SHEEP);
        });
        BiomeModifications.addSpawn(meadowVarSheep, MobCategory.CREATURE, EntityType.SHEEP,
                CommonSpawnUtil.sheepSpawnWeight, CommonSpawnUtil.sheepPackSizeMin, CommonSpawnUtil.sheepPackSizeMax);


        Predicate<BiomeSelectionContext> shearableVarCows = (ctx -> ctx.hasTag(TagRegistry.IS_MEADOW) || ctx.hasTag(TagRegistry.SPAWNS_DARK_COW));
        BiomeModifications.addSpawn(shearableVarCows, MobCategory.CREATURE, EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get(),
                10, meadowPackSizeMin, meadowPackSizeMax);

        BiomeModifications.addSpawn(buffalo, MobCategory.CREATURE, EntityRegistry.WATER_BUFFALO.get(),
                meadowSpawnWeight, meadowPackSizeMin, meadowPackSizeMax);
    }
}
