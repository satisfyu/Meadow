package net.satisfyu.meadow.entity.custom.chicken.chicken3;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;

public class Chicken3Entity extends ChickenEntity {
    public Chicken3Entity(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public Chicken3Entity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.CHICKEN3.create(serverWorld);
    }
}
