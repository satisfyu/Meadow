package net.satisfyu.meadow.entity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.cheeseForm.CheeseFormBlockEntity;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlockEntity;
import net.satisfyu.meadow.entity.custom.chair.ChairEntity;
import net.satisfyu.meadow.entity.custom.cow.albino_cow.AlbinoCowEntity;
import net.satisfyu.meadow.entity.custom.cow.ashen_cow.AshenCowEntity;
import net.satisfyu.meadow.entity.custom.cow.cookie_cow.CookieCowEntity;
import net.satisfyu.meadow.entity.custom.cow.cream_cow.CreamCowEntity;
import net.satisfyu.meadow.entity.custom.cow.dairy_cow.DairyCowEntity;
import net.satisfyu.meadow.entity.custom.cow.dark_cow.DarkCowEntity;
import net.satisfyu.meadow.entity.custom.cow.pinto_cow.PintoCowEntity;
import net.satisfyu.meadow.entity.custom.cow.sunset_cow.SunsetCowEntity;
import net.satisfyu.meadow.mixin.SpawnMobAccessor;

import java.util.function.Predicate;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class ModEntities {
    private ModEntities() {}

    public static final EntityType<ChairEntity> CHAIR = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "chair"),
            FabricEntityTypeBuilder.<ChairEntity>create(SpawnGroup.MISC, ChairEntity::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).build()
    );

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

    public static final BlockEntityType<CookingCauldronBlockEntity> COOKING_CAULDRON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":cooking_cauldron", FabricBlockEntityTypeBuilder.create(CookingCauldronBlockEntity::new, ModBlocks.COOKING_CAULDRON).build(null));

    public static final BlockEntityType<CheeseFormBlockEntity> CHEESE_FORM_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":cheese_form", FabricBlockEntityTypeBuilder.create(CheeseFormBlockEntity::new, ModBlocks.CHEESE_FORM).build(null));


    public static void registerEntities(){

        registerCow(COOKIE_COW, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));

        registerCow(ALBINO_COW, BiomeSelectors.spawnsOneOf(EntityType.COW));

        registerCow(CREAM_COW, BiomeSelectors.includeByKey(BiomeKeys.WOODED_BADLANDS).or(BiomeSelectors.tag(BiomeTags.IS_SAVANNA)));

        registerCow(DAIRY_COW, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));

        registerCow(ASHEN_COW, BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST));

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
