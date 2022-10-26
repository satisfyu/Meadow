package net.satisfyu.meadow.entity.custom.sheep.fuzzy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepEntity;

public class FuzzySheepEntity extends MeadowSheepEntity {
    public FuzzySheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, ModBlocks.ROCKY_SHEEP_WOOL, new Identifier(Meadow.MOD_ID, "entities/sheep/rocky"));
    }

    @Override
    public SheepEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.FUZZY_SHEEP.create(serverWorld);
    }
}
