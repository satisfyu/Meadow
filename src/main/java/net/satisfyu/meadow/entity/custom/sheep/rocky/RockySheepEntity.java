package net.satisfyu.meadow.entity.custom.sheep.rocky;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepEntity;

public class RockySheepEntity extends MeadowSheepEntity {
    public RockySheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, ModBlocks.ROCKY_SHEEP_WOOL, new Identifier(Meadow.MOD_ID, "entities/sheep/rocky"));
    }
}
