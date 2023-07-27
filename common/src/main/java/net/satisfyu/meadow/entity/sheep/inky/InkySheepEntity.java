package net.satisfyu.meadow.entity.sheep.inky;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.entity.sheep.MeadowSheepEntity;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class InkySheepEntity extends MeadowSheepEntity {
    public InkySheepEntity(EntityType<? extends Sheep> entityType, Level world) {
        super(entityType, world, ObjectRegistry.INKY_WOOL.get(), new ResourceLocation(Meadow.MOD_ID, "entities/sheep/inky"));
    }

    @Override
    public Sheep getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.INKY_SHEEP.get().create(serverWorld);
    }
}
