package net.satisfyu.meadow.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.*;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.bear.BrownBear;
import net.satisfyu.meadow.entity.buffalo.WaterBuffalo;
import net.satisfyu.meadow.entity.cow.shearable.ShearableVarCow;
import net.satisfyu.meadow.entity.sheep.horned.HornedSheep;

import java.util.function.Supplier;

public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Meadow.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<BrownBear>> BROWN_BEAR = create(
            "brown_bear",
            () -> EntityType.Builder.of(BrownBear::new, MobCategory.CREATURE).sized(1.4f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "brown_bear").toString())
    );

    public static final RegistrySupplier<EntityType<WaterBuffalo>> WATER_BUFFALO = create("water_buffalo",
            () -> EntityType.Builder.of(WaterBuffalo::new, MobCategory.CREATURE).sized(0.9f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "water_buffalo").toString())
    );

    public static final RegistrySupplier<EntityType<ShearableVarCow>> SHEARABLE_MEADOW_VAR_COW = create("wooly_cow",
            () -> EntityType.Builder.of(ShearableVarCow::new, MobCategory.CREATURE).sized(0.9f, 1.4f).build(new ResourceLocation(Meadow.MOD_ID, "wooly_cow").toString())
    );

    public static final RegistrySupplier<EntityType<HornedSheep>> HORNED_SHEEP = create("horned_sheep",
            () -> EntityType.Builder.of(HornedSheep::new, MobCategory.CREATURE).sized(0.9f, 1.3f).build(new ResourceLocation(Meadow.MOD_ID, "horned_sheep").toString())
    );



    public static void init() {
        Meadow.LOGGER.debug("Registering Mod Entities for " + Meadow.MOD_ID);
        ENTITY_TYPES.register();

        EntityAttributeRegistry.register(BROWN_BEAR, PolarBear::createAttributes);
        registerSheep(HORNED_SHEEP);

        registerCow(SHEARABLE_MEADOW_VAR_COW);
        registerCow(WATER_BUFFALO);
    }

    public static void registerCow(Supplier<? extends EntityType<? extends Animal>> typeSupplier) {
        EntityAttributeRegistry.register(typeSupplier, Cow::createAttributes);
    }

    public static void registerSheep(Supplier<? extends EntityType<? extends Animal>> typeSupplier) {
        EntityAttributeRegistry.register(typeSupplier, Sheep::createAttributes);
    }


    public static <T extends EntityType<?>> RegistrySupplier<T> create(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(new ResourceLocation(Meadow.MOD_ID, path), type);
    }
}
