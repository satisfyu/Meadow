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
import net.minecraft.entity.passive.*;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.cheeseForm.CheeseFormBlockEntity;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlockEntity;
import net.satisfyu.meadow.block.cookingPot.CookingPotBlockEntity;
import net.satisfyu.meadow.entity.custom.bear.brown.BrownBearEntity;
import net.satisfyu.meadow.entity.custom.chair.ChairEntity;
import net.satisfyu.meadow.entity.custom.chicken.chicken1.Chicken1Entity;
import net.satisfyu.meadow.entity.custom.chicken.chicken2.Chicken2Entity;
import net.satisfyu.meadow.entity.custom.chicken.chicken3.Chicken3Entity;
import net.satisfyu.meadow.entity.custom.cow.albino_cow.AlbinoCowEntity;
import net.satisfyu.meadow.entity.custom.cow.ashen_cow.AshenCowEntity;
import net.satisfyu.meadow.entity.custom.cow.cookie_cow.CookieCowEntity;
import net.satisfyu.meadow.entity.custom.cow.cream_cow.CreamCowEntity;
import net.satisfyu.meadow.entity.custom.cow.dairy_cow.DairyCowEntity;
import net.satisfyu.meadow.entity.custom.cow.dark_cow.DarkCowEntity;
import net.satisfyu.meadow.entity.custom.cow.pinto_cow.PintoCowEntity;
import net.satisfyu.meadow.entity.custom.cow.shearable.highland_cattle.HighlandCattleEntity;
import net.satisfyu.meadow.entity.custom.cow.shearable.umbra.UmbraCowEntity;
import net.satisfyu.meadow.entity.custom.cow.sunset_cow.SunsetCowEntity;
import net.satisfyu.meadow.entity.custom.sheep.flecked.FleckedSheepEntity;
import net.satisfyu.meadow.entity.custom.sheep.fuzzy.FuzzySheepEntity;
import net.satisfyu.meadow.entity.custom.sheep.horned.HornedSheepEntity;
import net.satisfyu.meadow.entity.custom.sheep.inky.InkySheepEntity;
import net.satisfyu.meadow.entity.custom.sheep.long_nosed.LongNosedSheepEntity;
import net.satisfyu.meadow.entity.custom.sheep.patched.PatchedSheepEntity;
import net.satisfyu.meadow.entity.custom.sheep.rocky.RockySheepEntity;
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

    public static final EntityType<BrownBearEntity> BROWN_BEAR = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "brown_bear"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BrownBearEntity::new).dimensions(EntityDimensions.fixed(1.4f, 1.4f)).build()
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

    public static final EntityType<HighlandCattleEntity> HIGHLAND_CATTLE = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "highland_cattle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HighlandCattleEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<UmbraCowEntity> UMBRA_COW = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "umbra_cow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, UmbraCowEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.4f)).build()
    );

    public static final EntityType<FleckedSheepEntity> FLECKED_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "flecked_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FleckedSheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );

    public static final EntityType<FuzzySheepEntity> FUZZY_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "fuzzy_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FuzzySheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );

    public static final EntityType<HornedSheepEntity> HORNED_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "horned_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HornedSheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );

    public static final EntityType<InkySheepEntity> INKY_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "inky_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, InkySheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );

    public static final EntityType<LongNosedSheepEntity> LONG_NOSED_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "long_nosed_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LongNosedSheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );

    public static final EntityType<PatchedSheepEntity> PATCHED_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "patched_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PatchedSheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );


    public static final EntityType<RockySheepEntity> ROCKY_SHEEP = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "rocky_sheep"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RockySheepEntity::new).dimensions(EntityDimensions.fixed(0.9f, 1.3f)).build()
    );

    public static final EntityType<Chicken1Entity> CHICKEN1 = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "chicken_1"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Chicken1Entity::new).dimensions(EntityDimensions.fixed(0.4f, 0.7f)).build()
    );

    public static final EntityType<Chicken2Entity> CHICKEN2 = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "chicken_2"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Chicken2Entity::new).dimensions(EntityDimensions.fixed(0.4f, 0.7f)).build()
    );

    public static final EntityType<Chicken3Entity> CHICKEN3 = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(MOD_ID, "chicken_3"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Chicken3Entity::new).dimensions(EntityDimensions.fixed(0.4f, 0.7f)).build()
    );

    public static final BlockEntityType<CookingCauldronBlockEntity> COOKING_CAULDRON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":cooking_cauldron", FabricBlockEntityTypeBuilder.create(CookingCauldronBlockEntity::new, ModBlocks.COOKING_CAULDRON).build());

    public static final BlockEntityType<CheeseFormBlockEntity> CHEESE_FORM_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":cheese_form", FabricBlockEntityTypeBuilder.create(CheeseFormBlockEntity::new, ModBlocks.CHEESE_FORM).build());

    public static final BlockEntityType<CookingPotBlockEntity> COOKING_POT_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":cooking_pot", FabricBlockEntityTypeBuilder.create(CookingPotBlockEntity::new, ModBlocks.COOKING_POT).build());


    public static void registerEntities(){
        Meadow.LOGGER.debug("Registering Mod Entities for " + Meadow.MOD_ID);

        FabricDefaultAttributeRegistry.register(BROWN_BEAR, PolarBearEntity.createPolarBearAttributes());
        SpawnMobAccessor.callRegister(BROWN_BEAR, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), SpawnGroup.CREATURE, BROWN_BEAR, 1, 1, 2);


        registerChicken(CHICKEN1, BiomeSelectors.spawnsOneOf(EntityType.CHICKEN));
        registerChicken(CHICKEN2, BiomeSelectors.spawnsOneOf(EntityType.CHICKEN));
        registerChicken(CHICKEN3, BiomeSelectors.spawnsOneOf(EntityType.CHICKEN));

        registerSheep(FLECKED_SHEEP, BiomeSelectors.spawnsOneOf(EntityType.SHEEP));
        registerSheep(FUZZY_SHEEP, BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.BIRCH_FOREST));
        registerSheep(HORNED_SHEEP, BiomeSelectors.includeByKey(BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_SLOPES));
        registerSheep(INKY_SHEEP, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerSheep(LONG_NOSED_SHEEP, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerSheep(PATCHED_SHEEP, BiomeSelectors.spawnsOneOf(EntityType.SHEEP));
        registerSheep(ROCKY_SHEEP, BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.GROVE, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.TAIGA, BiomeKeys.SNOWY_TAIGA));

        registerCow(HIGHLAND_CATTLE, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerCow(UMBRA_COW, BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.TAIGA, BiomeKeys.FOREST, BiomeKeys.DARK_FOREST));
        registerCow(COOKIE_COW, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_FOREST)));
        registerCow(ALBINO_COW, BiomeSelectors.spawnsOneOf(EntityType.COW));
        registerCow(CREAM_COW, BiomeSelectors.includeByKey(BiomeKeys.BADLANDS).or(BiomeSelectors.tag(BiomeTags.IS_BADLANDS)));
        registerCow(DAIRY_COW, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerCow(ASHEN_COW, BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST));
        registerCow(DARK_COW, BiomeSelectors.spawnsOneOf(EntityType.COW));
        registerCow(PINTO_COW, BiomeSelectors.spawnsOneOf(EntityType.COW));
        registerCow(SUNSET_COW, BiomeSelectors.includeByKey(BiomeKeys.SAVANNA).or(BiomeSelectors.tag(BiomeTags.IS_SAVANNA)));
    }

    public static void registerCow(EntityType entityType, Predicate<BiomeSelectionContext> biomes){
        FabricDefaultAttributeRegistry.register(entityType, CowEntity.createCowAttributes());
        SpawnMobAccessor.callRegister(entityType, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(biomes, SpawnGroup.CREATURE, entityType, 8, 4, 4);
    }

    public static void registerSheep(EntityType entityType, Predicate<BiomeSelectionContext> biomes){
        FabricDefaultAttributeRegistry.register(entityType, SheepEntity.createSheepAttributes());
        SpawnMobAccessor.callRegister(entityType, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(biomes, SpawnGroup.CREATURE, entityType, 2, 2, 4);
    }

    public static void registerChicken(EntityType entityType, Predicate<BiomeSelectionContext> biomes){
        FabricDefaultAttributeRegistry.register(entityType, ChickenEntity.createChickenAttributes());
        SpawnMobAccessor.callRegister(entityType, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        BiomeModifications.addSpawn(biomes, SpawnGroup.CREATURE, entityType, 10, 4, 4);
    }
}
