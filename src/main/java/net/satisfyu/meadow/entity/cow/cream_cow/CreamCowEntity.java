package net.satisfyu.meadow.entity.cow.cream_cow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;


public class CreamCowEntity extends CowEntity {
    public CreamCowEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public CowEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.CREAM_COW.create(serverWorld);
    }
}


