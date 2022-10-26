package net.satisfyu.meadow.entity.custom.bear.brown;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;

public class BrownBearEntity extends PolarBearEntity {
    public BrownBearEntity(EntityType<? extends PolarBearEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public BrownBearEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.BROWN_BEAR.create(serverWorld);
    }
}
