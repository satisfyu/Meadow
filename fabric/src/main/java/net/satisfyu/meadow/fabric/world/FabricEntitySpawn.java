package net.satisfyu.meadow.fabric.world;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.satisfyu.meadow.registry.EntityRegistry;

public class FabricEntitySpawn {
    public static void registerEntitySpawn() {
        SpawnPlacements.register(EntityRegistry.BROWN_BEAR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.MEADOW_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.FLECKED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.FUZZY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.HORNED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.INKY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.LONG_NOSED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.PATCHED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.ROCKY_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.WARPED_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.HIGHLAND_CATTLE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(EntityRegistry.UMBRA_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(EntityRegistry.MEADOW_VAR_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);


        SpawnPlacements.register(EntityRegistry.WATER_BUFFALO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);
    }
}
