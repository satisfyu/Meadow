package net.satisfyu.meadow.entity.custom.sheep.flecked;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepEntity;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class FleckedSheepEntity extends MeadowSheepEntity {
    public FleckedSheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, ObjectRegistry.FLECKED_WOOL.get(), new Identifier(Meadow.MOD_ID, "entities/sheep/flecked"));
    }

    @Override
    public SheepEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.FLECKED_SHEEP.get().create(serverWorld);
    }
}
