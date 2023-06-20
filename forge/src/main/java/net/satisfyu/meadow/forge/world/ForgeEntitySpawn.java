package net.satisfyu.meadow.forge.world;

import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.satisfyu.meadow.registry.EntityRegistry;

public class ForgeEntitySpawn {
    public static void registerEntitySpawn() {
        SpawnRestriction.register(EntityRegistry.BROWN_BEAR.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn);
    }
}