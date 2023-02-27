package net.satisfyu.meadow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.custom.FireLog;
import net.satisfyu.meadow.block.custom.WoodenFlowerPotBlock;
import net.satisfyu.meadow.item.ModItems;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    
    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    
    protected ModBlockLootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    
    @Override
    protected void generateBlockLootTables() {
        this.addDrop(ModBlocks.ALPINE_SALT_ORE, b -> oreDrops(b, ModItems.ALPINE_SALT));
        this.addDrop(ModBlocks.ALPINE_COAL_ORE, b -> oreDrops(b, Items.COAL));
        this.addDrop(ModBlocks.ALPINE_LAPIS_ORE, b -> oreDrops(b, Items.LAPIS_LAZULI));
        this.addDrop(ModBlocks.ALPINE_GOLD_ORE, b -> oreDrops(b, Items.RAW_GOLD));
        this.addDrop(ModBlocks.ALPINE_EMERALD_ORE, b -> oreDrops(b, Items.EMERALD));
        this.addDrop(ModBlocks.ALPINE_IRON_ORE, b -> oreDrops(b, Items.RAW_IRON));
        this.addDrop(ModBlocks.ALPINE_BIRCH_LOG, Blocks.BIRCH_LOG);
        this.addDrop(ModBlocks.ALPINE_OAK_LOG, Blocks.OAK_LOG);
        this.addDrop(ModBlocks.FIRE_LOG, ModBlockLootTableGenerator::fireLogStageDrops);
        this.addDrop(ModBlocks.BOWL_CORNFLAKES, Items.BOWL);
        this.addDrop(ModBlocks.BOWL_EMPTY, Items.BOWL);
        this.addDrop(ModBlocks.BOWL_HONEY, Items.BOWL);
        this.addDrop(ModBlocks.BOWL_MILK, Items.BOWL);
        this.addDrop(ModBlocks.BOWL_EMPTY_NO_FORK, Items.BOWL);
        this.addDrop(ModBlocks.FIR, ModBlocks.FIR_CONE);
        this.addDropWithSilkTouch(ModBlocks.WINDOW);
        this.addDropWithSilkTouch(ModBlocks.WINDOW_2);
        this.addDropWithSilkTouch(ModBlocks.WINDOW_3);
        this.addModPottedPlantDrop(ModBlocks.POTTED_ENZIAN);
        this.addModPottedPlantDrop(ModBlocks.POTTED_FIRE_LILY);
        this.addModPottedPlantDrop(ModBlocks.POTTED_ALPINE_POPPY);
        this.addModPottedPlantDrop(ModBlocks.POTTED_SAXIFRAGE);
        this.addModPottedPlantDrop(ModBlocks.POTTED_DELPHINIUM);
        this.addModPottedPlantDrop(ModBlocks.POTTED_ERIOPHORUM);
        this.addModPottedPlantDrop(ModBlocks.POTTED_PINE_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_OAK_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_SPRUCE_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_BIRCH_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_JUNGLE_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_ACACIA_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_DARK_OAK_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.POTTED_MANGROVE_PROPAGULE);
        this.addModPottedPlantDrop(ModBlocks.POTTED_DANDELION);
        this.addModPottedPlantDrop(ModBlocks.POTTED_POPPY);
        this.addModPottedPlantDrop(ModBlocks.POTTED_BLUE_ORCHID);
        this.addModPottedPlantDrop(ModBlocks.POTTED_ALLIUM);
        this.addModPottedPlantDrop(ModBlocks.POTTED_AZURE_BLUET);
        this.addModPottedPlantDrop(ModBlocks.POTTED_RED_TULIP);
        this.addModPottedPlantDrop(ModBlocks.POTTED_ORANGE_TULIP);
        this.addModPottedPlantDrop(ModBlocks.POTTED_WHITE_TULIP);
        this.addModPottedPlantDrop(ModBlocks.POTTED_PINK_TULIP);
        this.addModPottedPlantDrop(ModBlocks.POTTED_OXEYE_DAISY);
        this.addModPottedPlantDrop(ModBlocks.POTTED_CORNFLOWER);
        this.addModPottedPlantDrop(ModBlocks.POTTED_LILY_OF_THE_VALLEY);
        this.addModPottedPlantDrop(ModBlocks.POTTED_WITHER_ROSE);
        this.addModPottedPlantDrop(ModBlocks.POTTED_AZALEA);
        this.addModPottedPlantDrop(ModBlocks.POTTED_FLOWERING_AZALEA);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_PINE_SAPLING);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_DELPHINIUM);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_ERIOPHORUM);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_SAXIFRAGE);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_ALPINE_POPPY);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_FIRE_LILY);
        this.addModPottedPlantDrop(ModBlocks.W_POTTED_ENZIAN);
        this.addDrop(ModBlocks.LIMESTONE);
        this.addDrop(ModBlocks.LIMESTONE_STAIRS);
        this.addDrop(ModBlocks.LIMESTONE_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ModBlocks.LIMESTONE_WALL);
        this.addDrop(ModBlocks.COBBLED_LIMESTONE);
        this.addDrop(ModBlocks.COBBLED_LIMESTONE_STAIRS);
        this.addDrop(ModBlocks.COBBLED_LIMESTONE_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ModBlocks.COBBLED_LIMESTONE_WALL);
        this.addDrop(ModBlocks.MOSSY_COBBLED_LIMESTONE);
        this.addDrop(ModBlocks.MOSSY_COBBLED_LIMESTONE_STAIRS);
        this.addDrop(ModBlocks.MOSSY_COBBLED_LIMESTONE_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ModBlocks.MOSSY_COBBLED_LIMESTONE_WALL);
        this.addDrop(ModBlocks.LIMESTONE_BRICKS);
        this.addDrop(ModBlocks.LIMESTONE_BRICK_STAIRS);
        this.addDrop(ModBlocks.LIMESTONE_BRICK_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ModBlocks.LIMESTONE_BRICK_WALL);
        this.addDrop(ModBlocks.MOSSY_LIMESTONE_BRICKS);
        this.addDrop(ModBlocks.MOSSY_LIMESTONE_BRICK_STAIRS);
        this.addDrop(ModBlocks.MOSSY_LIMESTONE_BRICK_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ModBlocks.MOSSY_LIMESTONE_BRICK_WALL);
        this.addDrop(ModBlocks.CRACKED_LIMESTONE_BRICKS);
        this.addDrop(ModBlocks.CHISELED_LIMESTONE_BRICKS);
        this.addDrop(ModBlocks.POLISHED_LIMESTONE_BRICKS);
        this.addDrop(ModBlocks.FURNACE_COBBLESTONE);
        this.addDrop(ModBlocks.PINE_LEAVES, b -> leavesDrop(b, ModBlocks.PINE_SAPLING, SAPLING_DROP_CHANCE));
        this.addDrop(ModBlocks.PINE_LEAVES_2, b -> leavesDrop(b, ModBlocks.PINE_SAPLING, SAPLING_DROP_CHANCE));
        this.addDrop(ModBlocks.ALPINE_BIRCH_LEAVES_HANGING, b -> leavesDrop(b, Blocks.BIRCH_SAPLING, SAPLING_DROP_CHANCE));
        this.addDrop(ModBlocks.ERIOPHORUM_TALL, b -> dropsWithProperty(b, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.addDrop(ModBlocks.SMALL_FIR, b -> dropsWithProperty(b, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.addDrop(ModBlocks.ALPINE_GRASS_TALL, b -> dropsWithProperty(b, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        this.addDrop(ModBlocks.FRAME);
        this.addDrop(ModBlocks.STOVE);
        this.addDrop(ModBlocks.STOVE_WOOD);
        this.addDrop(ModBlocks.STOVE_LID);
        //this.addDrop(ModBlocks.STOVE_BENCH);
        this.addDrop(ModBlocks.PINE_LOG);
        this.addDrop(ModBlocks.PINE_WOOD);
        this.addDrop(ModBlocks.STRIPPED_PINE_WOOD);
        this.addDrop(ModBlocks.STRIPPED_PINE_LOG);
        this.addDrop(ModBlocks.PINE_BEAM);
        this.addDrop(ModBlocks.PINE_PLANKS);
        this.addDrop(ModBlocks.PINE_STAIRS);
        this.addDrop(ModBlocks.PINE_SLAB, BlockLootTableGenerator::slabDrops);
        this.addDrop(ModBlocks.PINE_PRESSURE_PLATE);
        this.addDrop(ModBlocks.PINE_BUTTON);
        this.addDrop(ModBlocks.PINE_TRAPDOOR);
        this.addDrop(ModBlocks.PINE_DOOR, BlockLootTableGenerator::doorDrops);
        this.addDrop(ModBlocks.PINE_FENCE);
        this.addDrop(ModBlocks.PINE_RAILING);
        this.addDrop(ModBlocks.PINE_FENCE_GATE);
        this.addDrop(ModBlocks.PINE_SIGN);
        this.addDrop(ModBlocks.PINE_WALL_SIGN);
        this.addDrop(ModBlocks.SHELF);
        this.addDrop(ModBlocks.CHEESE_RACK);
        this.addDrop(ModBlocks.TABLE);
        this.addDrop(ModBlocks.CHAIR);
        //this.addDrop(ModBlocks.BENCH);
        this.addDrop(ModBlocks.FLECKED_WOOL);
        this.addDrop(ModBlocks.FLECKED_CARPET);
        this.addDrop(ModBlocks.FLECKED_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.HIGHLAND_WOOL);
        this.addDrop(ModBlocks.HIGHLAND_CARPET);
        this.addDrop(ModBlocks.HIGHLAND_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.PATCHED_WOOL);
        this.addDrop(ModBlocks.PATCHED_CARPET);
        this.addDrop(ModBlocks.PATCHED_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.ROCKY_SHEEP_WOOL);
        this.addDrop(ModBlocks.ROCKY_CARPET);
        this.addDrop(ModBlocks.WOOLY_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.UMBRA_WOOL);
        this.addDrop(ModBlocks.UMBRA_CARPET);
        this.addDrop(ModBlocks.UMBRA_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.INKY_WOOL);
        this.addDrop(ModBlocks.INKY_CARPET);
        this.addDrop(ModBlocks.INKY_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.OAT_BALL);
        this.addDrop(ModBlocks.OATBLOCK_RUG);
        this.addDrop(ModBlocks.STRAW_BED, b -> dropsWithProperty(b, BedBlock.PART, BedPart.HEAD));
        this.addDrop(ModBlocks.OAT_BAG);
        this.addDrop(ModBlocks.CAMERA);
        this.addDrop(ModBlocks.DOORMAT);
        this.addDrop(ModBlocks.WOODEN_FLOWER_POT);
        this.addDrop(ModBlocks.CHEESE_FORM);
        this.addDrop(ModBlocks.WOODEN_CAULDRON);
        this.addDrop(ModBlocks.WOODCUTTER);
        this.addDrop(ModBlocks.WOODEN_WATER_CAULDRON);
        this.addDrop(ModBlocks.WOODEN_POWDER_SNOW_CAULDRON);
        this.addDrop(ModBlocks.FRAME);
        this.addDrop(ModBlocks.COOKING_POT);
        this.addDrop(ModBlocks.FONDUE);
        this.addDrop(ModBlocks.CAN);
        this.addDrop(ModBlocks.CLIMBING_ROPE_TOPMOUNT);
        this.addDrop(ModBlocks.OIL_LANTERN);
        this.addDrop(ModBlocks.FIR_CONE);
        this.addDrop(ModBlocks.PINE_SAPLING);
        this.addDrop(ModBlocks.OAT_CROP);
        this.addDrop(ModBlocks.YARROW_CROP);
        this.addDrop(ModBlocks.MOUNTAIN_LAVENDER_CROP);
        this.addDrop(ModBlocks.JUNIPER_CROP);
        this.addDrop(ModBlocks.ALPINE_GRASS, BlockLootTableGenerator::grassDrops);
        this.addDrop(ModBlocks.WILD_MOUNTAIN_LAVENDER);
        this.addDrop(ModBlocks.WILD_JUNIPER);
        this.addDrop(ModBlocks.WILD_YARROW);
        this.addDrop(ModBlocks.DELPHINIUM);
        this.addDrop(ModBlocks.ALPINE_POPPY);
        this.addDrop(ModBlocks.SAXIFRAGE);
        this.addDrop(ModBlocks.ENZIAN);
        this.addDrop(ModBlocks.FIRE_LILY);
        this.addDrop(ModBlocks.ERIOPHORUM);
    }
    
    private void addModPottedPlantDrop(Block block) {
        if (block instanceof FlowerPotBlock) {
            this.addDrop(block, flowerPot -> pottedPlantDrops(((FlowerPotBlock)flowerPot).getContent()));
        } else if (block instanceof WoodenFlowerPotBlock) {
            this.addDrop(block, flowerPot -> wPottedPlantDrops(((WoodenFlowerPotBlock)flowerPot).getContent()));
        }
    }
    
    private static LootTable.Builder wPottedPlantDrops(ItemConvertible plant) {
        return LootTable.builder().pool(addSurvivesExplosionCondition(ModBlocks.WOODEN_FLOWER_POT, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(ModBlocks.WOODEN_FLOWER_POT)))).pool(addSurvivesExplosionCondition(plant, LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(ItemEntry.builder(plant))));
    }
    
    private static LootTable.Builder fireLogStageDrops(Block block) {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with(applyExplosionDecay(block, ItemEntry.builder(block)
              .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(FireLog.STAGE, 1))))
              .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(FireLog.STAGE, 2))))
              .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(3.0F)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(FireLog.STAGE, 3)))))));
    }
    
}