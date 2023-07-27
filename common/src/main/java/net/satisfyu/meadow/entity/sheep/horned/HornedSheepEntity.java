package net.satisfyu.meadow.entity.sheep.horned;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.satisfyu.meadow.entity.sheep.MeadowSheepEntity;
import net.satisfyu.meadow.registry.EntityRegistry;

public class HornedSheepEntity extends MeadowSheepEntity {
    public HornedSheepEntity(EntityType<? extends Sheep> entityType, Level world) {
        super(entityType, world, Blocks.WHITE_WOOL, new ResourceLocation("entities/sheep/white"));
    }

    @Override
    public Sheep getBreedOffspring(ServerLevel serverWorld, AgeableMob passiveEntity) {
        return EntityRegistry.HORNED_SHEEP.get().create(serverWorld);
    }
}
