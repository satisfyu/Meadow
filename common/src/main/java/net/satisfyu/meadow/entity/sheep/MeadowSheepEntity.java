package net.satisfyu.meadow.entity.sheep;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTables;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;

import java.util.Map;

public class MeadowSheepEntity extends SheepEntity {

    private final Block woolBlock;

    private final Identifier lootTable;
    public MeadowSheepEntity(EntityType<? extends SheepEntity> entityType, World world, Block woolBlock, Identifier lootTable) {
        super(entityType, world);
        this.woolBlock = woolBlock;
        this.lootTable = lootTable;
    }

    private final Map<DyeColor, ItemConvertible> DROPS = Util.make(Maps.newEnumMap(DyeColor.class), map -> {
        map.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
        map.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
        map.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
        map.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
        map.put(DyeColor.LIME, Blocks.LIME_WOOL);
        map.put(DyeColor.PINK, Blocks.PINK_WOOL);
        map.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
        map.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
        map.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
        map.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
        map.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
        map.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
        map.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
        map.put(DyeColor.RED, Blocks.RED_WOOL);
        map.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
    });


    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        if(!DROPS.containsValue(woolBlock)) DROPS.put(DyeColor.WHITE, woolBlock);
        this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(DROPS.get(this.getColor()), 1);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
            }
        }

    }

    @Override
    public Identifier getLootTableId() {
        if (this.isSheared()) {
            return this.getType().getLootTableId();
        }
        switch (this.getColor()) {
            default: {
                return lootTable;
            }
            case ORANGE: {
                return LootTables.ORANGE_SHEEP_ENTITY;
            }
            case MAGENTA: {
                return LootTables.MAGENTA_SHEEP_ENTITY;
            }
            case LIGHT_BLUE: {
                return LootTables.LIGHT_BLUE_SHEEP_ENTITY;
            }
            case YELLOW: {
                return LootTables.YELLOW_SHEEP_ENTITY;
            }
            case LIME: {
                return LootTables.LIME_SHEEP_ENTITY;
            }
            case PINK: {
                return LootTables.PINK_SHEEP_ENTITY;
            }
            case GRAY: {
                return LootTables.GRAY_SHEEP_ENTITY;
            }
            case LIGHT_GRAY: {
                return LootTables.LIGHT_GRAY_SHEEP_ENTITY;
            }
            case CYAN: {
                return LootTables.CYAN_SHEEP_ENTITY;
            }
            case PURPLE: {
                return LootTables.PURPLE_SHEEP_ENTITY;
            }
            case BLUE: {
                return LootTables.BLUE_SHEEP_ENTITY;
            }
            case BROWN: {
                return LootTables.BROWN_SHEEP_ENTITY;
            }
            case GREEN: {
                return LootTables.GREEN_SHEEP_ENTITY;
            }
            case RED: {
                return LootTables.RED_SHEEP_ENTITY;
            }
            case BLACK:
        }
        return LootTables.BLACK_SHEEP_ENTITY;
    }
}
