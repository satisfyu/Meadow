package net.satisfyu.meadow.entity.cow.shearable.umbra;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.cow.shearable.ShearableCowEntity;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;


public class UmbraCowEntity extends ShearableCowEntity {
    public UmbraCowEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world, ObjectRegistry.UMBRA_WOOL.get());
    }

    @Override
    public CowEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return EntityRegistry.UMBRA_COW.get().create(serverWorld);
    }

}


