package net.satisfyu.meadow.entity.custom.sheep.horned;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepEntity;

public class HornedSheepEntity extends MeadowSheepEntity {
    public HornedSheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, Blocks.WHITE_WOOL, new Identifier("entities/sheep/white"));
    }

    @Override
    public SheepEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.HORNED_SHEEP.create(serverWorld);
    }
}
