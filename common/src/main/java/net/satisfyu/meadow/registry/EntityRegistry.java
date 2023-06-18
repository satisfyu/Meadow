package net.satisfyu.meadow.registry;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.bear.BrownBearEntity;
import net.satisfyu.meadow.entity.blockentities.ChairEntity;
import net.satisfyu.meadow.entity.buffalo.WaterBuffaloEntity;
import net.satisfyu.meadow.entity.chicken.MeadowChickenEntity;
import net.satisfyu.meadow.entity.cow.albino_cow.AlbinoCowEntity;
import net.satisfyu.meadow.entity.cow.cookie_cow.CookieCowEntity;
import net.satisfyu.meadow.entity.cow.cream_cow.CreamCowEntity;
import net.satisfyu.meadow.entity.cow.dairy_cow.DairyCowEntity;
import net.satisfyu.meadow.entity.cow.dark_cow.DarkCowEntity;
import net.satisfyu.meadow.entity.cow.pinto_cow.PintoCowEntity;
import net.satisfyu.meadow.entity.cow.shearable.highland_cattle.HighlandCattleEntity;
import net.satisfyu.meadow.entity.cow.shearable.umbra.UmbraCowEntity;
import net.satisfyu.meadow.entity.cow.shearable.warped.WarpedCowEntity;
import net.satisfyu.meadow.entity.cow.sunset_cow.SunsetCowEntity;
import net.satisfyu.meadow.entity.sheep.flecked.FleckedSheepEntity;
import net.satisfyu.meadow.entity.sheep.fuzzy.FuzzySheepEntity;
import net.satisfyu.meadow.entity.sheep.horned.HornedSheepEntity;
import net.satisfyu.meadow.entity.sheep.inky.InkySheepEntity;
import net.satisfyu.meadow.entity.sheep.long_nosed.LongNosedSheepEntity;
import net.satisfyu.meadow.entity.sheep.patched.PatchedSheepEntity;
import net.satisfyu.meadow.entity.sheep.rocky.RockySheepEntity;
import net.satisfyu.meadow.mixin.SpawnMobAccessor;

import java.util.function.Supplier;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registry.ENTITY_TYPE_KEY);
    public static final RegistrySupplier<EntityType<ChairEntity>> CHAIR = create("chair",
            () -> EntityType.Builder.create(ChairEntity::new, SpawnGroup.MISC).setDimensions(0.001F, 0.001F).build(new Identifier(Meadow.MOD_ID, "chair").toString())
    );

    public static final RegistrySupplier<EntityType<AlbinoCowEntity>> ALBINO_COW = create("albino_cow",
            () -> EntityType.Builder.create(AlbinoCowEntity::new, SpawnGroup.CREATURE).setDimensions(1.4f, 1.4f).build(new Identifier(Meadow.MOD_ID, "albino_cow").toString())
    );

    public static final RegistrySupplier<EntityType<BrownBearEntity>> BROWN_BEAR = create(
            "brown_bear",
            () -> EntityType.Builder.create(BrownBearEntity::new, SpawnGroup.CREATURE).setDimensions(1.4f, 1.4f).build(new Identifier(Meadow.MOD_ID, "brown_bear").toString())
    );

    public static final RegistrySupplier<EntityType<WaterBuffaloEntity>> WATER_BUFFALO =create("water_buffalo",
            () -> EntityType.Builder.create(WaterBuffaloEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "water_buffalo").toString())
    );

    public static final RegistrySupplier<EntityType<CookieCowEntity>> COOKIE_COW = create( "cookie_cow",
            () -> EntityType.Builder.create(CookieCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "cookie_cow").toString())
    );

    public static final RegistrySupplier<EntityType<CreamCowEntity>> CREAM_COW = create( "cream_cow",
            () -> EntityType.Builder.create(CreamCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "cream_cow").toString())
    );

    public static final RegistrySupplier<EntityType<DairyCowEntity>> DAIRY_COW = create("dairy_cow",
            () -> EntityType.Builder.create(DairyCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "dairy_cow").toString())
    );

    public static final RegistrySupplier<EntityType<DarkCowEntity>> DARK_COW = create("dark_cow",
            () -> EntityType.Builder.create(DarkCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "dark_cow").toString())
    );

    public static final RegistrySupplier<EntityType<PintoCowEntity>> PINTO_COW = create("pinto_cow",
            () -> EntityType.Builder.create(PintoCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "pinto_cow").toString())
    );

    public static final RegistrySupplier<EntityType<SunsetCowEntity>> SUNSET_COW = create("sunset_cow",
            () -> EntityType.Builder.create(SunsetCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "sunset_cow").toString())
    );

    public static final RegistrySupplier<EntityType<HighlandCattleEntity>> HIGHLAND_CATTLE = create("highland_cattle",
            () -> EntityType.Builder.create(HighlandCattleEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "highland_cattle").toString())
    );

    public static final RegistrySupplier<EntityType<UmbraCowEntity>> UMBRA_COW = create("umbra_cow",
            () -> EntityType.Builder.create(UmbraCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "umbra_cow").toString())
    );

    public static final RegistrySupplier<EntityType<WarpedCowEntity>> WARPED_COW = create("warped_cow",
            () -> EntityType.Builder.create(WarpedCowEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.4f).build(new Identifier(Meadow.MOD_ID, "warped_cow").toString())
    );
    public static final RegistrySupplier<EntityType<FleckedSheepEntity>> FLECKED_SHEEP = create("flecked_sheep",
            () -> EntityType.Builder.create(FleckedSheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "flecked_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<FuzzySheepEntity>> FUZZY_SHEEP = create("fuzzy_sheep",
            () -> EntityType.Builder.create(FuzzySheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "fuzzy_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<HornedSheepEntity>> HORNED_SHEEP = create("horned_sheep",
            () -> EntityType.Builder.create(HornedSheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "horned_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<InkySheepEntity>> INKY_SHEEP = create("inky_sheep",
            () -> EntityType.Builder.create(InkySheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "inky_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<LongNosedSheepEntity>> LONG_NOSED_SHEEP = create("long_nosed_sheep",
            () -> EntityType.Builder.create(LongNosedSheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "long_nosed_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<PatchedSheepEntity>> PATCHED_SHEEP = create("patched_sheep",
            () -> EntityType.Builder.create(PatchedSheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "patched_sheep").toString())
    );


    public static final RegistrySupplier<EntityType<RockySheepEntity>> ROCKY_SHEEP = create("rocky_sheep",
            () -> EntityType.Builder.create(RockySheepEntity::new, SpawnGroup.CREATURE).setDimensions(0.9f, 1.3f).build(new Identifier(Meadow.MOD_ID, "rocky_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<MeadowChickenEntity>> MEADOW_CHICKEN = create("chicken_1",
            () -> EntityType.Builder.create(MeadowChickenEntity::new, SpawnGroup.CREATURE).setDimensions(0.4f, 0.7f).build(new Identifier(Meadow.MOD_ID, "chicken_1").toString())
    );


    public static void init(){
        Meadow.LOGGER.debug("Registering Mod Entities for " + Meadow.MOD_ID);
        ENTITY_TYPES.register();


        EntityAttributeRegistry.register(BROWN_BEAR, PolarBearEntity::createPolarBearAttributes);
        //--------TODO
        //SpawnMobAccessor.callRegister(BROWN_BEAR.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        //BiomeModifications.addProperties(BiomeSelectors.includeByKey(ModSurfaceRules.MEADOW_CLEARING_KEY, ModSurfaceRules.MEADOW_FOREST_KEY, BiomeKeys.MEADOW), SpawnGroup.CREATURE, BROWN_BEAR, 1, 1, 2);


        registerChicken(MEADOW_CHICKEN); //, BiomeSelectors.spawnsOneOf(EntityType.CHICKEN));



        registerSheep(FLECKED_SHEEP); //, BiomeSelectors.spawnsOneOf(EntityType.SHEEP));
        registerSheep(FUZZY_SHEEP); //, BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.BIRCH_FOREST));
        registerSheep(HORNED_SHEEP); //, BiomeSelectors.includeByKey(BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_TAIGA, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_SLOPES));
        registerSheep(INKY_SHEEP); //, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerSheep(LONG_NOSED_SHEEP); //, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerSheep(PATCHED_SHEEP); //, BiomeSelectors.spawnsOneOf(EntityType.SHEEP));
        registerSheep(ROCKY_SHEEP); //, BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.GROVE, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.TAIGA, BiomeKeys.SNOWY_TAIGA));

        registerCow(WARPED_COW); //, BiomeSe.includeByKey(BiomeKeys.WARPED_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_NETHER)));
        registerCow(HIGHLAND_CATTLE); //, BiomeSelectors.includeByKey(BiomeKeys.MEADOW));
        registerCow(UMBRA_COW); //, BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.TAIGA, BiomeKeys.FOREST, BiomeKeys.DARK_FOREST));
        registerCow(COOKIE_COW); //, BiomeSelectors.includeByKey(BiomeKeys.MEADOW).or(BiomeSelectors.tag(BiomeTags.IS_FOREST)));
        registerCow(ALBINO_COW); //, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_FOREST)));
        registerCow(CREAM_COW); //, BiomeSelectors.includeByKey(BiomeKeys.BADLANDS).or(BiomeSelectors.tag(BiomeTags.IS_BADLANDS)));
        registerCow(DAIRY_COW); //, BiomeSelectors.spawnsOneOf(EntityType.COW));
        registerCow(WATER_BUFFALO); //, BiomeSelectors.includeByKey(BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA).or(BiomeSelectors.tag(BiomeTags.IS_RIVER)));
        registerCow(DARK_COW); //, BiomeSelectors.spawnsOneOf(EntityType.COW));
        registerCow(PINTO_COW); //, BiomeSelectors.spawnsOneOf(EntityType.COW));
        registerCow(SUNSET_COW); //, BiomeSelectors.includeByKey(BiomeKeys.SAVANNA).or(BiomeSelectors.tag(BiomeTags.IS_SAVANNA)));
    }

    public static void registerCow(Supplier<? extends EntityType<? extends AnimalEntity>> typeSupplier){
        EntityAttributeRegistry.register(typeSupplier, CowEntity::createCowAttributes);
        //SpawnMobAccessor.callRegister(typeSupplier.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        //BiomeModifications.addSpawn(biomes, SpawnGroup.CREATURE, typeSupplier, 8, 4, 4);
    }


    public static void registerSheep(Supplier<? extends EntityType<? extends AnimalEntity>> typeSupplier){
        EntityAttributeRegistry.register(typeSupplier, SheepEntity::createSheepAttributes);
        //SpawnMobAccessor.callRegister(typeSupplier.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        //BiomeModifications.addSpawn(biomes, SpawnGroup.CREATURE, typeSupplier, 2, 2, 4);
    }

    public static void registerChicken(Supplier<? extends EntityType<? extends AnimalEntity>> typeSupplier){
        EntityAttributeRegistry.register(typeSupplier, ChickenEntity::createChickenAttributes);
        //SpawnMobAccessor.callRegister(typeSupplier.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        //BiomeModifications.addProperties(biomes, SpawnGroup.CREATURE, typeSupplier, 10, 4, 4);
    }

    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new Identifier(Meadow.MOD_ID, path), type);
    }
}
