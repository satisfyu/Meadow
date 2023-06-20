package net.satisfyu.meadow.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.satisfyu.meadow.Meadow;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.satisfyu.meadow.fabric.client.MeadowFabricSpawnConfig;
import net.satisfyu.meadow.fabric.villager.FabricVillager;
import net.satisfyu.meadow.fabric.world.FeatureModification;
import net.satisfyu.meadow.fabric.world.MeadowBiomeModification;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.util.MeadowIdentifier;


import static net.satisfyu.meadow.Meadow.MOD_ID;


public class MeadowFabric implements ModInitializer {

    public static ConfigHolder<MeadowFabricSpawnConfig> config;

    @Override
    public void onInitialize() {
        config = AutoConfig.register(MeadowFabricSpawnConfig.class, Toml4jConfigSerializer::new);

        Meadow.init();
        FeatureModification.init();
        Meadow.commonSetup();
        FabricVillager.init();
        MeadowBiomeModification.init();

        SpawnRestriction.register(EntityRegistry.BROWN_BEAR.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.MEADOW_CHICKEN.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.FLECKED_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.FUZZY_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.HORNED_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.INKY_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.LONG_NOSED_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.PATCHED_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.ROCKY_SHEEP.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.WARPED_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.HIGHLAND_CATTLE.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.UMBRA_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.COOKIE_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.ALBINO_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.CREAM_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.DAIRY_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.WATER_BUFFALO.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.DARK_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.PINTO_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);
        SpawnRestriction.register(EntityRegistry.SUNSET_COW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, AnimalEntity::isValidNaturalSpawn);


        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("patched"), container, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("optifine_support"), container, ResourcePackActivationType.NORMAL);
        });
    }
}

