package net.satisfyu.meadow.entity.sheep.long_nosed;

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

public class LongNosedSheepEntity extends MeadowSheepEntity {
    public LongNosedSheepEntity(EntityType<? extends Sheep> entityType, Level world) {
        super(entityType, world, ObjectRegistry.HIGHLAND_WOOL.get(), new ResourceLocation(Meadow.MOD_ID, "entities/sheep/long_nosed"));
    }

    @Override
    public Sheep getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.LONG_NOSED_SHEEP.get().create(serverWorld);
    }
}
