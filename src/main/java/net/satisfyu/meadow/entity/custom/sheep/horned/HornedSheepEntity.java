package net.satisfyu.meadow.entity.custom.sheep.horned;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepEntity;

public class HornedSheepEntity extends MeadowSheepEntity {
    public HornedSheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, Blocks.WHITE_WOOL, new Identifier("entities/sheep/white"));
    }
}
