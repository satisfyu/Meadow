package net.satisfyu.meadow.entity.custom.cow.shearable.highland_cattle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.cow.shearable.ShearableCowEntity;


public class HighlandCattleEntity extends ShearableCowEntity {
    public HighlandCattleEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world, ModBlocks.HIGHLAND_WOOL);
    }

    @Override
    public CowEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.HIGHLAND_CATTLE.create(serverWorld);
    }
}


