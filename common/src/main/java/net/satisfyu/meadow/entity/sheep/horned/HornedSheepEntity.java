package net.satisfyu.meadow.entity.sheep.horned;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.sheep.MeadowSheepEntity;
import net.satisfyu.meadow.registry.EntityRegistry;

public class HornedSheepEntity extends MeadowSheepEntity {
    public HornedSheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, Blocks.WHITE_WOOL, new Identifier("entities/sheep/white"));
    }

    @Override
    public SheepEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return EntityRegistry.HORNED_SHEEP.get().create(serverWorld);
    }
}
