package net.satisfyu.meadow.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.satisfyu.meadow.block.FireLog;
import net.satisfyu.meadow.block.WoodenFlowerPotBlock;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    
    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    
    protected ModBlockLootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    
    @Override
    protected void generateBlockLootTables() {
        this.addDrop(ObjectRegistry.ALPINE_COAL_ORE.get(), b -> oreDrops(b, (Item) ObjectRegistry.ALPINE_SALT));
        this.addDrop(ObjectRegistry.ALPINE_COAL_ORE.get(), b -> oreDrops(b, Items.COAL));
        this.addDrop(ObjectRegistry.ALPINE_LAPIS_ORE.get(), b -> oreDrops(b, Items.LAPIS_LAZULI));
        this.addDrop(ObjectRegistry.ALPINE_GOLD_ORE.get(), b -> oreDrops(b, Items.RAW_GOLD));
        this.addDrop(ObjectRegistry.ALPINE_EMERALD_ORE.get(), b -> oreDrops(b, Items.EMERALD));
        this.addDrop(ObjectRegistry.ALPINE_IRON_ORE.get(), b -> oreDrops(b, Items.RAW_IRON));
        this.addDrop(ObjectRegistry.ALPINE_BIRCH_LOG.get(), Blocks.BIRCH_LOG);
        this.addDrop(ObjectRegistry.ALPINE_OAK_LOG.get(), Blocks.OAK_LOG);
        this.addDrop(ObjectRegistry.FIRE_LOG.get(), ModBlockLootTableGenerator::fireLogStageDrops);
        this.addDropWithSilkTouch(ObjectRegistry.WINDOW.get());
        this.addDropWithSilkTouch(ObjectRegistry.WINDOW_2.get());
        this.addDropWithSilkTouch(ObjectRegistry.WINDOW_3.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_ENZIAN.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_FIRE_LILY.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_ALPINE_POPPY.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_SAXIFRAGE.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_DELPHINIUM.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_ERIOPHORUM.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_PINE_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_OAK_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_SPRUCE_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_BIRCH_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_JUNGLE_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_ACACIA_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_DARK_OAK_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_MANGROVE_PROPAGULE.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_DANDELION.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_POPPY.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_BLUE_ORCHID.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_ALLIUM.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_AZURE_BLUET.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_RED_TULIP.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_ORANGE_TULIP.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_WHITE_TULIP.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_PINK_TULIP.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_OXEYE_DAISY.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_CORNFLOWER.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_LILY_OF_THE_VALLEY.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_WITHER_ROSE.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_AZALEA.get());
        this.addModPottedPlantDrop(ObjectRegistry.POTTED_FLOWERING_AZALEA.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_PINE_SAPLING.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_DELPHINIUM.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_ERIOPHORUM.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_SAXIFRAGE.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_ALPINE_POPPY.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_FIRE_LILY.get());
        this.addModPottedPlantDrop(ObjectRegistry.W_POTTED_ENZIAN.get());
        this.addDrop(ObjectRegistry.LIMESTONE.get());
        this.addDrop(ObjectRegistry.LIMESTONE_STAIRS.get());
        this.addDrop(ObjectRegistry.LIMESTONE_SLAB.get(), BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.LIMESTONE_WALL.get());
        this.addDrop(ObjectRegistry.COBBLED_LIMESTONE.get());
        this.addDrop(ObjectRegistry.COBBLED_LIMESTONE_STAIRS.get());
        this.addDrop(ObjectRegistry.COBBLED_LIMESTONE_SLAB.get(), BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.COBBLED_LIMESTONE_WALL.get());
        this.addDrop(ObjectRegistry.MOSSY_COBBLED_LIMESTONE.get());
        this.addDrop(ObjectRegistry.MOSSY_COBBLED_LIMESTONE_STAIRS.get());
        this.addDrop(ObjectRegistry.MOSSY_COBBLED_LIMESTONE_SLAB.get(), BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.MOSSY_COBBLED_LIMESTONE_WALL.get());
        this.addDrop(ObjectRegistry.LIMESTONE_BRICKS.get());
        this.addDrop(ObjectRegistry.LIMESTONE_BRICK_STAIRS.get());
        this.addDrop(ObjectRegistry.LIMESTONE_BRICK_SLAB.get(), BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.LIMESTONE_BRICK_WALL.get());
        this.addDrop(ObjectRegistry.MOSSY_LIMESTONE_BRICKS.get());
        this.addDrop(ObjectRegistry.MOSSY_LIMESTONE_BRICK_STAIRS.get());
        this.addDrop(ObjectRegistry.MOSSY_LIMESTONE_BRICK_SLAB.get(), BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.MOSSY_LIMESTONE_BRICK_WALL.get());
        this.addDrop(ObjectRegistry.CRACKED_LIMESTONE_BRICKS.get());
        this.addDrop(ObjectRegistry.CHISELED_LIMESTONE_BRICKS.get());
        this.addDrop(ObjectRegistry.POLISHED_LIMESTONE_BRICKS.get());
        this.addDrop(ObjectRegistry.FURNACE_COBBLESTONE.get());
        this.addDrop(ObjectRegistry.PINE_LEAVES.get(), b -> leavesDrop(b, ObjectRegistry.PINE_SAPLING.get(), SAPLING_DROP_CHANCE));
        this.addDrop(ObjectRegistry.PINE_LEAVES_2.get(), b -> leavesDrop(b, ObjectRegistry.PINE_SAPLING.get(), SAPLING_DROP_CHANCE));
        this.addDrop(ObjectRegistry.ALPINE_BIRCH_LEAVES_HANGING.get(), b -> leavesDrop(b, Blocks.BIRCH_SAPLING, SAPLING_DROP_CHANCE));
        this.addDrop(ObjectRegistry.ERIOPHORUM_TALL.get(), b -> dropsWithProperty(b, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.addDrop(ObjectRegistry.SMALL_FIR.get(), b -> dropsWithProperty(b, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.addDrop(ObjectRegistry.FRAME.get());
        this.addDrop(ObjectRegistry.STOVE.get());
        this.addDrop(ObjectRegistry.STOVE_WOOD.get());
        this.addDrop(ObjectRegistry.STOVE_LID.get());
        //this.addDrop(ObjectRegistry.STOVE_BENCH);
        this.addDrop(ObjectRegistry.PINE_LOG.get());
        this.addDrop(ObjectRegistry.PINE_WOOD.get());
        this.addDrop(ObjectRegistry.STRIPPED_PINE_WOOD.get());
        this.addDrop(ObjectRegistry.STRIPPED_PINE_LOG.get());
        this.addDrop(ObjectRegistry.PINE_BEAM.get());
        this.addDrop(ObjectRegistry.PINE_PLANKS.get());
        this.addDrop(ObjectRegistry.PINE_STAIRS.get());
        this.addDrop(ObjectRegistry.PINE_SLAB.get(), BlockLootTableGenerator::slabDrops);
        this.addDrop(ObjectRegistry.PINE_PRESSURE_PLATE.get());
        this.addDrop(ObjectRegistry.PINE_BUTTON.get());
        this.addDrop(ObjectRegistry.PINE_TRAPDOOR.get());
        this.addDrop(ObjectRegistry.PINE_DOOR.get(), BlockLootTableGenerator::doorDrops);
        this.addDrop(ObjectRegistry.PINE_FENCE.get());
        this.addDrop(ObjectRegistry.PINE_RAILING.get());
        this.addDrop(ObjectRegistry.PINE_FENCE_GATE.get());
        this.addDrop(ObjectRegistry.SHELF.get());
        this.addDrop(ObjectRegistry.CHEESE_RACK.get());
        this.addDrop(ObjectRegistry.TABLE.get());
        this.addDrop(ObjectRegistry.CHAIR.get());
        this.addDrop(ObjectRegistry.BENCH.get());
        this.addDrop(ObjectRegistry.FLECKED_WOOL.get());
        this.addDrop(ObjectRegistry.FLECKED_CARPET.get());
        this.addDrop(ObjectRegistry.FLECKED_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.HIGHLAND_WOOL.get());
        this.addDrop(ObjectRegistry.HIGHLAND_CARPET.get());
        this.addDrop(ObjectRegistry.HIGHLAND_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.PATCHED_WOOL.get());
        this.addDrop(ObjectRegistry.PATCHED_CARPET.get());
        this.addDrop(ObjectRegistry.PATCHED_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.ROCKY_SHEEP_WOOL.get());
        this.addDrop(ObjectRegistry.ROCKY_CARPET.get());
        this.addDrop(ObjectRegistry.WOOLY_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.UMBRA_WOOL.get());
        this.addDrop(ObjectRegistry.UMBRA_CARPET.get());
        this.addDrop(ObjectRegistry.UMBRA_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.INKY_WOOL.get());
        this.addDrop(ObjectRegistry.INKY_CARPET.get());
        this.addDrop(ObjectRegistry.INKY_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.STRAW_BED.get(), b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ObjectRegistry.CAMERA.get());
        this.addDrop(ObjectRegistry.DOORMAT.get());
        this.addDrop(ObjectRegistry.WOODEN_FLOWER_POT.get());
        this.addDrop(ObjectRegistry.CHEESE_FORM.get());
        this.addDrop(ObjectRegistry.WOODEN_CAULDRON.get());
        this.addDrop(ObjectRegistry.WOODCUTTER.get());
        this.addDrop(ObjectRegistry.WOODEN_WATER_CAULDRON.get());
        this.addDrop(ObjectRegistry.WOODEN_POWDER_SNOW_CAULDRON.get());
        this.addDrop(ObjectRegistry.FRAME.get());
        this.addDrop(ObjectRegistry.FONDUE.get());
        this.addDrop(ObjectRegistry.CAN.get());
        this.addDrop(ObjectRegistry.CLIMBING_ROPE_TOPMOUNT.get());
        this.addDrop(ObjectRegistry.OIL_LANTERN.get());
        this.addDrop(ObjectRegistry.PINE_SAPLING.get());
        this.addDrop(ObjectRegistry.DELPHINIUM.get());
        this.addDrop(ObjectRegistry.ALPINE_POPPY.get());
        this.addDrop(ObjectRegistry.SAXIFRAGE.get());
        this.addDrop(ObjectRegistry.ENZIAN.get());
        this.addDrop(ObjectRegistry.FIRE_LILY.get());
        this.addDrop(ObjectRegistry.ERIOPHORUM.get());
    }
    
    private void addModPottedPlantDrop(Block block) {
        if (block instanceof FlowerPotBlock) {
            this.addDrop(block, flowerPot -> pottedPlantDrops(((FlowerPotBlock)flowerPot).getContent()));
        } else if (block instanceof WoodenFlowerPotBlock) {
            this.addDrop(block, flowerPot -> wPottedPlantDrops(((WoodenFlowerPotBlock)flowerPot).getContent()));
        }
    }
    
    private static LootTable.Builder wPottedPlantDrops(ItemConvertible plant) {
        return LootTable.builder().pool(addSurvivesExplosionCondition(ObjectRegistry.WOODEN_FLOWER_POT.get(), LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(ObjectRegistry.WOODEN_FLOWER_POT.get())))).pool(addSurvivesExplosionCondition(plant, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(plant))));
    }
    
    private static LootTable.Builder fireLogStageDrops(Block block) {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(applyExplosionDecay(block, ItemEntry.builder(block)
              .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(FireLog.STAGE, 1))))
              .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(FireLog.STAGE, 2))))
              .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(3.0F)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(FireLog.STAGE, 3)))))));
    }
    
}