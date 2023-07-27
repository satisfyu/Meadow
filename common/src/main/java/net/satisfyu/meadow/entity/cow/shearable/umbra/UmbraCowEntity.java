package net.satisfyu.meadow.entity.cow.shearable.umbra;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.entity.cow.shearable.ShearableCowEntity;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;


public class UmbraCowEntity extends ShearableCowEntity {
    public UmbraCowEntity(EntityType<? extends Cow> entityType, Level world) {
        super(entityType, world, ObjectRegistry.UMBRA_WOOL.get());
    }

    @Override
    public Cow getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.UMBRA_COW.get().create(serverWorld);
    }

}


