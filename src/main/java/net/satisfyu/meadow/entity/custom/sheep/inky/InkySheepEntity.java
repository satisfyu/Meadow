package net.satisfyu.meadow.entity.custom.sheep.inky;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.entity.custom.sheep.MeadowSheepEntity;

public class InkySheepEntity extends MeadowSheepEntity {
    public InkySheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world, ModBlocks.INKY_WOOL, new Identifier(Meadow.MOD_ID, "entities/sheep/inky"));
    }
}
