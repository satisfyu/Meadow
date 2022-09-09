package net.satisfyu.meadow.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.satisfyu.meadow.entity.cow.albino_cow.AlbinoCowEntity;
import net.satisfyu.meadow.entity.cow.ashen_cow.AshenCowEntity;
import net.satisfyu.meadow.entity.cow.cookie_cow.CookieCowEntity;
import net.satisfyu.meadow.entity.cow.cream_cow.CreamCowEntity;
import net.satisfyu.meadow.entity.cow.dairy_cow.DairyCowEntity;
import net.satisfyu.meadow.entity.cow.dark_cow.DarkCowEntity;
import net.satisfyu.meadow.entity.cow.pinto_cow.PintoCowEntity;
import net.satisfyu.meadow.entity.cow.sunset_cow.SunsetCowEntity;
import net.satisfyu.meadow.mixin.SpawnMobAccessor;

import java.util.function.Predicate;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class ModEntities {
    private ModEntities() {}

    public static final EntityType<AlbinoCowEntity> ALBINO_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "albino_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AlbinoCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<AshenCowEntity> ASHEN_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "ashen_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AshenCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<CookieCowEntity> COOKIE_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "cookie_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CookieCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<CreamCowEntity> CREAM_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "cream_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CreamCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<DairyCowEntity> DAIRY_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "dairy_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DairyCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<DarkCowEntity> DARK_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "dark_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DarkCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<PintoCowEntity> PINTO_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "pinto_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PintoCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<SunsetCowEntity> SUNSET_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "sunset_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SunsetCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );


    public static void register(){
        FabricDefaultAttributeRegistry.register(ALBINO_COW, CowEntity.createCowAttributes());
        SpawnMobAccessor.callRegister(ALBINO_COW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.COW), SpawnGroup.CREATURE, ALBINO_COW, 8, 4, 4);

        FabricDefaultAttributeRegistry.register(ASHEN_COW, CowEntity.createCowAttributes());
        SpawnMobAccessor.callRegister(ASHEN_COW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST), SpawnGroup.CREATURE, ASHEN_COW, 8, 4, 4);

        registerCow(COOKIE_COW, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));

        registerCow(CREAM_COW, BiomeSelectors.includeByKey(BiomeKeys.WOODED_BADLANDS).or(BiomeSelectors.tag(BiomeTags.IS_SAVANNA)));

        registerCow(DAIRY_COW, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));

        registerCow(DARK_COW, BiomeSelectors.spawnsOneOf(EntityType.COW));

        registerCow(PINTO_COW, BiomeSelectors.spawnsOneOf(EntityType.COW));

        registerCow(SUNSET_COW, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
    }

    public static void registerCow(EntityType entityType, Predicate<BiomeSelectionContext> biomes){
        FabricDefaultAttributeRegistry.register(entityType, CowEntity.createCowAttributes());
        SpawnMobAccessor.callRegister(entityType, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(biomes, SpawnGroup.CREATURE, entityType, 8, 4, 4);
    }
}
