package net.satisfyu.meadow.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Sheep;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.bear.BrownBearEntity;
import net.satisfyu.meadow.entity.buffalo.WaterBuffaloEntity;
import net.satisfyu.meadow.entity.chicken.MeadowChickenEntity;
import net.satisfyu.meadow.entity.cow.MeadowVarCow;
import net.satisfyu.meadow.entity.cow.shearable.highland_cattle.HighlandCattleEntity;
import net.satisfyu.meadow.entity.cow.shearable.umbra.UmbraCowEntity;
import net.satisfyu.meadow.entity.cow.shearable.warped.WarpedCowEntity;
import net.satisfyu.meadow.entity.sheep.flecked.FleckedSheepEntity;
import net.satisfyu.meadow.entity.sheep.fuzzy.FuzzySheepEntity;
import net.satisfyu.meadow.entity.sheep.horned.HornedSheepEntity;
import net.satisfyu.meadow.entity.sheep.inky.InkySheepEntity;
import net.satisfyu.meadow.entity.sheep.long_nosed.LongNosedSheepEntity;
import net.satisfyu.meadow.entity.sheep.patched.PatchedSheepEntity;
import net.satisfyu.meadow.entity.sheep.rocky.RockySheepEntity;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Supplier;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<MeadowVarCow>> MEADOW_VAR_COW = create("meadow_cow",
            () -> EntityType.Builder.of(MeadowVarCow::new, MobCategory.CREATURE).sized(1.4f, 1.4f).build(new MeadowIdentifier("meadow_cow").toString())
    );

    public static final RegistrySupplier<EntityType<BrownBearEntity>> BROWN_BEAR = create(
            "brown_bear",
            () -> EntityType.Builder.of(BrownBearEntity::new, MobCategory.CREATURE).sized(1.4f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "brown_bear").toString())
    );

    public static final RegistrySupplier<EntityType<WaterBuffaloEntity>> WATER_BUFFALO = create("water_buffalo",
            () -> EntityType.Builder.of(WaterBuffaloEntity::new, MobCategory.CREATURE).sized(0.9f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "water_buffalo").toString())
    );

    public static final RegistrySupplier<EntityType<HighlandCattleEntity>> HIGHLAND_CATTLE = create("highland_cattle",
            () -> EntityType.Builder.of(HighlandCattleEntity::new, MobCategory.CREATURE).sized(0.9f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "highland_cattle").toString())
    );

    public static final RegistrySupplier<EntityType<UmbraCowEntity>> UMBRA_COW = create("umbra_cow",
            () -> EntityType.Builder.of(UmbraCowEntity::new, MobCategory.CREATURE).sized(0.9f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "umbra_cow").toString())
    );

    public static final RegistrySupplier<EntityType<WarpedCowEntity>> WARPED_COW = create("warped_cow",
            () -> EntityType.Builder.of(WarpedCowEntity::new, MobCategory.CREATURE).sized(0.9f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "warped_cow").toString())
    );
    public static final RegistrySupplier<EntityType<FleckedSheepEntity>> FLECKED_SHEEP = create("flecked_sheep",
            () -> EntityType.Builder.of(FleckedSheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "flecked_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<FuzzySheepEntity>> FUZZY_SHEEP = create("fuzzy_sheep",
            () -> EntityType.Builder.of(FuzzySheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "fuzzy_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<HornedSheepEntity>> HORNED_SHEEP = create("horned_sheep",
            () -> EntityType.Builder.of(HornedSheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "horned_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<InkySheepEntity>> INKY_SHEEP = create("inky_sheep",
            () -> EntityType.Builder.of(InkySheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "inky_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<LongNosedSheepEntity>> LONG_NOSED_SHEEP = create("long_nosed_sheep",
            () -> EntityType.Builder.of(LongNosedSheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "long_nosed_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<PatchedSheepEntity>> PATCHED_SHEEP = create("patched_sheep",
            () -> EntityType.Builder.of(PatchedSheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "patched_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<RockySheepEntity>> ROCKY_SHEEP = create("rocky_sheep",
            () -> EntityType.Builder.of(RockySheepEntity::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "rocky_sheep").toString())
    );

    public static final RegistrySupplier<EntityType<MeadowChickenEntity>> MEADOW_CHICKEN = create("chicken_1",
            () -> EntityType.Builder.of(MeadowChickenEntity::new, MobCategory.CREATURE).sized(0.4f, 0.7f).build(new ResourceLocation(Meadow.MOD_ID, "chicken_1").toString())
    );


    public static void init() {
        Meadow.LOGGER.debug("Registering Mod Entities for " + Meadow.MOD_ID);
        ENTITY_TYPES.register();

        EntityAttributeRegistry.register(BROWN_BEAR, PolarBear::createAttributes);
        registerChicken(MEADOW_CHICKEN);
        registerSheep(FLECKED_SHEEP);
        registerSheep(FUZZY_SHEEP);
        registerSheep(HORNED_SHEEP);
        registerSheep(INKY_SHEEP);
        registerSheep(LONG_NOSED_SHEEP);
        registerSheep(PATCHED_SHEEP);
        registerSheep(ROCKY_SHEEP);
        registerCow(WARPED_COW);
        registerCow(HIGHLAND_CATTLE);
        registerCow(UMBRA_COW);
        registerCow(MEADOW_VAR_COW);
        registerCow(WATER_BUFFALO);
    }

    public static void registerCow(Supplier<? extends EntityType<? extends Animal>> typeSupplier) {
        EntityAttributeRegistry.register(typeSupplier, Cow::createAttributes);
    }

    public static void registerSheep(Supplier<? extends EntityType<? extends Animal>> typeSupplier) {
        EntityAttributeRegistry.register(typeSupplier, Sheep::createAttributes);
    }

    public static void registerChicken(Supplier<? extends EntityType<? extends Animal>> typeSupplier) {
        EntityAttributeRegistry.register(typeSupplier, Chicken::createAttributes);
    }


    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new ResourceLocation(Meadow.MOD_ID, path), type);
    }
}
