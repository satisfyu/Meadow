package net.satisfy.meadow.fabric.world;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.satisfy.meadow.registry.EntityRegistry;

public class FabricEntitySpawn {
    public static void registerEntitySpawn() {
        SpawnPlacements.register(EntityRegistry.WATER_BUFFALO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Animal::checkAnimalSpawnRules);

        SpawnPlacements.register(EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }
}
