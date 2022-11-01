package net.satisfyu.meadow.entity.custom.chicken.chicken1;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;

public class Chicken1Entity extends ChickenEntity {
    public Chicken1Entity(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Chicken1Entity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.CHICKEN1.create(serverWorld);
    }
}
