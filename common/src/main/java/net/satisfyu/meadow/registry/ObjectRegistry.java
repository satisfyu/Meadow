package net.satisfyu.meadow.registry;

import de.cristelknight.doapi.Util;
import de.cristelknight.doapi.common.block.ChairBlock;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.*;
import net.satisfyu.meadow.item.*;
import net.satisfyu.meadow.util.MeadowIdentifier;
import net.satisfyu.meadow.util.WoodenCauldronBehavior;
import net.satisfyu.meadow.world.feature.tree.PineSaplingGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Meadow.MOD_ID, Registry.ITEM_KEY);
    public static final Registrar<Item> ITEMS_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Meadow.MOD_ID, Registry.BLOCK_KEY);
    public static final Registrar<Block> BLOCKS_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Block> ALPINE_SALT_ORE = registerBlock("alpine_salt_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_COAL_ORE = registerBlock("alpine_coal_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_LAPIS_ORE = registerBlock("alpine_lapis_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_GOLD_ORE = registerBlock("alpine_gold_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_EMERALD_ORE = registerBlock("alpine_emerald_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_IRON_ORE = registerBlock("alpine_iron_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_COPPER_ORE = registerBlock("alpine_copper_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_DIAMOND_ORE = registerBlock("alpine_diamond_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_REDSTONE_ORE = registerBlock("alpine_redstone_ore", () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> LIMESTONE = registerBlock("limestone", () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LIMESTONE_STAIRS = registerBlock("limestone_stairs", () -> new StairsBlock(LIMESTONE.get().getDefaultState(), AbstractBlock.Settings.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_SLAB = registerBlock("limestone_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE = registerBlock("cobbled_limestone", () -> new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_STAIRS = registerBlock("cobbled_limestone_stairs", () -> new StairsBlock(COBBLED_LIMESTONE.get().getDefaultState(), AbstractBlock.Settings.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_SLAB = registerBlock("cobbled_limestone_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs", () -> new StairsBlock(LIMESTONE_BRICKS.get().getDefaultState(), AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE = registerBlock("mossy_cobbled_limestone", () -> new Block(AbstractBlock.Settings.copy(Blocks.MOSSY_COBBLESTONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_STAIRS = registerBlock("mossy_cobbled_limestone_stairs", () -> new StairsBlock(MOSSY_COBBLED_LIMESTONE.get().getDefaultState(), AbstractBlock.Settings.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_SLAB = registerBlock("mossy_cobbled_limestone_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICKS = registerBlock("mossy_limestone_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_STAIRS = registerBlock("mossy_limestone_brick_stairs", () -> new StairsBlock(LIMESTONE_BRICKS.get().getDefaultState(), AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_SLAB = registerBlock("mossy_limestone_brick_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(MOSSY_LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_LIMESTONE_BRICKS = registerBlock("cracked_limestone_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<Block> CHISELED_LIMESTONE_BRICKS = registerBlock("chiseled_limestone_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final RegistrySupplier<Block> POLISHED_LIMESTONE_BRICKS = registerBlock("polished_limestone_bricks", () -> new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistrySupplier<Block> LIMESTONE_WALL = registerBlock("limestone_wall", () -> new WallBlock(AbstractBlock.Settings.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_WALL = registerBlock("cobbled_limestone_wall", () -> new WallBlock(AbstractBlock.Settings.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall", () -> new WallBlock(AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_WALL = registerBlock("mossy_cobbled_limestone_wall", () -> new WallBlock(AbstractBlock.Settings.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_WALL = registerBlock("mossy_limestone_brick_wall", () -> new WallBlock(AbstractBlock.Settings.copy(MOSSY_LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> STOVE = registerBlock("stove_tiles", () -> new MainStoveBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD)));
    public static final RegistrySupplier<Block> STOVE_WOOD = registerBlock("stove_tiles_wood", () -> new StoveBlockWood(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD).luminance(state -> state.get(StoveBlockWood.LIT) ? 13 : 0).ticksRandomly(), Direction.UP));
    public static final RegistrySupplier<Block> STOVE_LID = registerBlock("stove_tiles_lid", () -> new StoveBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD), Direction.UP));
    public static final RegistrySupplier<Block> STOVE_BENCH = registerBlock("stove_tiles_bench", () -> new TiledBench(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD)));
    public static final RegistrySupplier<Block> PINE_LOG = registerBlock("pine_log", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_WOOD = registerBlock("pine_wood", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_PINE_WOOD = registerBlock("stripped_pine_wood", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_PINE_LOG = registerBlock("stripped_pine_log", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_BEAM = registerBlock("pine_beam", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_PLANKS = registerBlock("pine_planks", () -> new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_ORANGE).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3.0f)));
    public static final RegistrySupplier<Block> PINE_STAIRS = registerBlock("pine_stairs", () -> new StairsBlock(PINE_PLANKS.get().getDefaultState(), AbstractBlock.Settings.copy(Blocks.ACACIA_STAIRS)));
    public static final RegistrySupplier<Block> PINE_SLAB = registerBlock("pine_slab", () -> new SlabBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB)));
    public static final RegistrySupplier<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_BUTTON = registerBlock("pine_button", () -> new WoodenButtonBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_BUTTON)), null);
    public static final RegistrySupplier<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor", () -> new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR)));
    public static final RegistrySupplier<Block> PINE_DOOR = registerBlock("pine_door", () -> new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> PINE_BARN_TRAPDOOR = registerBlock("pine_barn_trapdoor", () -> new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR)));
    public static final RegistrySupplier<Block> PINE_BARN_DOOR = registerBlock("pine_barn_door", () -> new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> PINE_FENCE = registerBlock("pine_fence", () -> new FenceBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate", () -> new FenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_RAILING = registerBlock("pine_railing", () -> new FenceBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK = registerBlock("shutter_block", () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_POPPY = registerBlock("shutter_block_poppy", () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)), null);
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_FIR = registerBlock("shutter_block_fir", () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)), null);
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_BERRY = registerBlock("shutter_block_berry", () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)), null);
    public static final RegistrySupplier<Block> SHELF = registerBlock("shelf", () -> new ShelfBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), SoundRegistry.SHELF_OPEN, SoundRegistry.SHELF_CLOSED));
    public static final RegistrySupplier<Block> CHEESE_RACK = registerBlock("cheese_rack", () -> new CheeseRackBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> CHAIR = registerBlock("chair", () -> new ChairBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> TABLE = registerBlock("table", () -> new TableBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> BENCH = registerBlock("bench", () -> new BenchBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> STONE_TABLE = registerBlock("stone_table", () -> new TableBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f, 0.0f).sounds(BlockSoundGroup.STONE).nonOpaque()));
    public static final RegistrySupplier<Block> STONE_BENCH = registerBlock("stone_bench", () -> new BenchBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f, 0.0f).sounds(BlockSoundGroup.STONE)));
    public static final RegistrySupplier<Block> WINDOW = registerBlock("window", () -> new WindowBlock_1(AbstractBlock.Settings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final RegistrySupplier<Block> WINDOW_2 = registerBlock("window_2", () -> new WindowBlock_2(AbstractBlock.Settings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final RegistrySupplier<Block> WINDOW_3 = registerBlock("window_3", () -> new WindowBlock_3(AbstractBlock.Settings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final RegistrySupplier<Block> FLECKED_WOOL = registerBlock("flecked_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> FLECKED_CARPET = registerBlock("flecked_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> FLECKED_BED = registerBlock("flecked_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> HIGHLAND_WOOL = registerBlock("highland_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> HIGHLAND_CARPET = registerBlock("highland_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> HIGHLAND_BED = registerBlock("highland_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> PATCHED_WOOL = registerBlock("patched_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> PATCHED_CARPET = registerBlock("patched_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> PATCHED_BED = registerBlock("patched_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> ROCKY_SHEEP_WOOL = registerBlock("rocky_sheep_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> ROCKY_CARPET = registerBlock("rocky_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> ROCKY_BED = registerBlock("rocky_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> UMBRA_WOOL = registerBlock("umbra_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> UMBRA_CARPET = registerBlock("umbra_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> UMBRA_BED = registerBlock("umbra_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> INKY_WOOL = registerBlock("inky_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> INKY_CARPET = registerBlock("inky_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> INKY_BED = registerBlock("inky_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> WARPED_WOOL = registerBlock("warped_wool", () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> WARPED_CARPET = registerBlock("warped_carpet", () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> WARPED_BED = registerBlock("warped_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> STRAW_BED = registerBlock("straw_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> CHEESE_FORM = registerBlock("cheese_form", () -> new CheeseFormBlock(AbstractBlock.Settings.of(Material.DECORATION).nonOpaque().strength(0.1f).sounds(BlockSoundGroup.SCAFFOLDING)));
    public static final RegistrySupplier<Block> FONDUE = registerBlock("fondue", () -> new FondueBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.METAL).luminance((blockState) -> 13)));
    public static final RegistrySupplier<Block> COOKING_CAULDRON = registerBlock("cooking_pot", () -> new CookingCauldronBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.METAL).luminance(state -> state.get(CookingCauldronBlock.HANGING) ? 13 : 0)));
    public static final RegistrySupplier<Block> FRAME = registerBlock("frame", () -> new FrameBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().luminance((blockState) -> 13).strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> WOODCUTTER = registerBlock("woodcutter", () -> new WoodcutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> WOODEN_CAULDRON = registerBlock("wooden_cauldron", () -> new WoodenCauldronBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.SPRUCE_BROWN).requiresTool().strength(2.0f).nonOpaque().sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> WHEELBARROW = registerBlock("wheelbarrow", () -> new WheelBarrowBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> WOODEN_WATER_CAULDRON = registerBlockWithoutItem("wooden_water_cauldron", () -> new LeveledCauldronBlock(AbstractBlock.Settings.copy(ObjectRegistry.WOODEN_CAULDRON.get()), LeveledCauldronBlock.RAIN_PREDICATE, WoodenCauldronBehavior.WATER_CAULDRON_BEHAVIOR));
    public static final RegistrySupplier<Block> WOODEN_POWDER_SNOW_CAULDRON = registerBlockWithoutItem("wooden_powder_snow_cauldron", () -> new PowderSnowCauldronBlock(AbstractBlock.Settings.copy(ObjectRegistry.WOODEN_CAULDRON.get()), LeveledCauldronBlock.SNOW_PREDICATE, WoodenCauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR));
    public static final RegistrySupplier<Block> FIRE_LOG = registerBlock("fire_log", () -> new FireLog(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> CAN = registerBlock("can", () -> new CanBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(0.8f).nonOpaque().sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> CLIMBING_ROPE = registerBlockWithoutItem("climbing_rope", () -> new ClimbingRopeBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).noCollision()));
    public static final RegistrySupplier<Block> WATERING_CAN = registerBlockWithoutItem("watering_can", () -> new WateringCanBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Item> WATERING_CAN_ITEM = registerItem("watering_can", () -> new WateringCanItem(ObjectRegistry.WATERING_CAN.get(), getSettings().maxDamage(25)));
    public static final RegistrySupplier<Block> CLIMBING_ROPE_TOPMOUNT = registerBlock("climbing_rope_topmount", () -> new ClimbingRopeTopmountBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).noCollision()));
    public static final RegistrySupplier<Block> OIL_LANTERN = registerBlock("oil_lantern", () -> new OilLantern(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN).luminance((blockState) -> 13).nonOpaque()));
    public static final RegistrySupplier<Block> CAMERA = registerBlock("camera", () -> new CameraBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> DOORMAT = registerBlock("doormat", () -> new DoormatBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> FLOWER_POT_BIG = registerBlock("flower_pot_big", () -> new BigFlowerPotBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> WOODEN_FLOWER_POT = registerBlock("wooden_flower_pot", () -> new WoodenFlowerPotBlock(Blocks.AIR, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
    public static final RegistrySupplier<Block> WOODEN_FLOWER_BOX = registerBlock("flower_box", () -> new FlowerBoxBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Item> FUR_HELMET = registerItem("fur_helmet", () -> new FurHead(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> FUR_CHESTPLATE = registerItem("fur_chestplate", () -> new FurChest(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> FUR_LEGGINGS = registerItem("fur_leggings", () -> new FurLegs(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> FUR_BOOTS = registerItem("fur_boots", () -> new FurBoots(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Block> SMALL_FIR = registerBlock("small_fir", () -> new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> PINE_SAPLING = registerBlock("pine_sapling", () -> new SaplingBlock(new PineSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.SPRUCE_SAPLING)));
    public static final RegistrySupplier<Block> ALPINE_POPPY = registerBlock("alpine_poppy", () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> DELPHINIUM = registerBlock("delphinium", () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> SAXIFRAGE = registerBlock("saxifrage", () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ENZIAN = registerBlock("enzian", () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> FIRE_LILY = registerBlock("fire_lily", () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ERIOPHORUM = registerBlock("eriophorum", () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ERIOPHORUM_TALL = registerBlock("eriophorum_tall", () -> new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> PINE_LEAVES = registerBlock("pine_leaves", () -> new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().blockVision((state, world, pos) -> false).suffocates((state, world, pos) -> false)));
    public static final RegistrySupplier<Block> ALPINE_BIRCH_LEAVES_HANGING = registerBlock("alpine_birch_leaves_hanging", () -> new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().blockVision((state, world, pos) -> false).suffocates((state, world, pos) -> false)));
    public static final RegistrySupplier<Item> CHEESECAKE_SLICE = registerItem("cheesecake_slice", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> CHEESE_TART_SLICE = registerItem("cheese_tart_slice", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_CHEESE = registerItem("piece_of_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_SHEEP_CHEESE = registerItem("piece_of_sheep_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_GRAIN_CHEESE = registerItem("piece_of_grain_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_AMETHYST_CHEESE = registerItem("piece_of_amethyst_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_BUFFALO_CHEESE = registerItem("piece_of_buffalo_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_GOAT_CHEESE = registerItem("piece_of_goat_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_WARPED_CHEESE = registerItem("piece_of_warped_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 1), 1.0F).build())));
    public static final RegistrySupplier<Block> CHEESECAKE = registerBlock("cheesecake", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.CHEESECAKE_SLICE, true));
    public static final RegistrySupplier<Block> CHEESE_TART = registerBlock("cheese_tart", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.CHEESE_TART_SLICE, true));
    public static final RegistrySupplier<Block> CHEESE_BLOCK = registerBlock("cheese_block", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_CHEESE, true));
    public static final RegistrySupplier<Block> SHEEP_CHEESE_BLOCK = registerBlock("sheep_cheese_block", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_SHEEP_CHEESE, false));
    public static final RegistrySupplier<Block> GRAIN_CHEESE_BLOCK = registerBlock("grain_cheese_block", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_GRAIN_CHEESE, false));
    public static final RegistrySupplier<Block> AMETHYST_CHEESE_BLOCK = registerBlock("amethyst_cheese_block", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_AMETHYST_CHEESE, true));
    public static final RegistrySupplier<Block> BUFFALO_CHEESE_BLOCK = registerBlock("buffalo_cheese_block", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_BUFFALO_CHEESE, false));
    public static final RegistrySupplier<Block> GOAT_CHEESE_BLOCK = registerBlock("goat_cheese_block", () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_GOAT_CHEESE, false));
    public static final RegistrySupplier<Block> WARPED_CHEESE_BLOCK = registerBlock("warped_cheese_block", () -> new WarpedCheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_WARPED_CHEESE, false));
    public static final RegistrySupplier<Item> ALPINE_SALT = registerItem("alpine_salt", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> RENNET = registerItem("rennet", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> CHEESE_SANDWICH = registerItem("cheese_sandwich", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> CHEESE_ROLL = registerItem("cheese_roll", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> CHEESE_STICK = registerItem("cheese_stick", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.5f).build())));
    public static final RegistrySupplier<Item> RAW_BEAR_MEAT = registerItem("raw_bear_meat", () -> new CraftingIngredientItem(getSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));
    public static final RegistrySupplier<Item> COOKED_BEAR_MEAT = registerItem("cooked_bear_meat", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> BEAR_STEW = registerItem("bear_stew", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.3f).build())));
    public static final RegistrySupplier<Item> HAM_CHEESE = registerItem("ham_cheese", () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> WOODEN_BUCKET = registerItem("wooden_bucket", () -> new WoodenBucket(Fluids.EMPTY, getSettings().maxCount(16)));
    public static final RegistrySupplier<Item> WOODEN_WATER_BUCKET = registerItem("wooden_water_bucket", () -> new WoodenBucket(Fluids.WATER, getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_MILK_BUCKET = registerItem("wooden_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_SHEEP_MILK_BUCKET = registerItem("wooden_sheep_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_BUFFALO_MILK_BUCKET = registerItem("wooden_buffalo_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_GOAT_MILK_BUCKET = registerItem("wooden_goat_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_WARPED_MILK_BUCKET = registerItem("wooden_warped_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_GRAIN_MILK_BUCKET = registerItem("wooden_grain_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_AMETHYST_MILK_BUCKET = registerItem("wooden_amethyst_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> ALBINO_COW_SPAWN_EGG_ITEM = registerItem("albino_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.ALBINO_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> WATER_BUFFALO_SPAWN_EGG_ITEM = registerItem("water_buffalo_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.WATER_BUFFALO, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> COOKIE_COW_SPAWN_EGG_ITEM = registerItem("cookie_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.COOKIE_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> CREAM_COW_SPAWN_EGG_ITEM = registerItem("cream_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.CREAM_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> DAIRY_COW_SPAWN_EGG_ITEM = registerItem("dairy_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.DAIRY_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> DARK_COW_SPAWN_EGG_ITEM = registerItem("dark_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.DARK_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> PINTO_COW_SPAWN_EGG_ITEM = registerItem("pinto_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.PINTO_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> SUNSET_COW_SPAWN_EGG_ITEM = registerItem("sunset_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.SUNSET_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> UMBRA_COW_SPAWN_EGG_ITEM = registerItem("umbra_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.UMBRA_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> HIGHLAND_CATTLE_SPAWN_EGG_ITEM = registerItem("highland_cattle_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.HIGHLAND_CATTLE, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> WARPED_COW_SPAWN_EGG_ITEM = registerItem("warped_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.WARPED_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> BROWN_BEAR_SPAWN_EGG_ITEM = registerItem("brown_bear_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.BROWN_BEAR, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> MEADOW_CHICKEN_SPAWN_EGG_ITEM = registerItem("meadow_chicken_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.MEADOW_CHICKEN, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> FLECKED_SHEEP_SPAWN_EGG_ITEM = registerItem("flecked_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.FLECKED_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> FUZZY_SHEEP_SPAWN_EGG_ITEM = registerItem("fuzzy_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.FUZZY_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> HORNED_SHEEP_SPAWN_EGG_ITEM = registerItem("horned_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.HORNED_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> INKY_SHEEP_SPAWN_EGG_ITEM = registerItem("inky_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.INKY_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> LONG_NOSED_SHEEP_SPAWN_EGG_ITEM = registerItem("long_nosed_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.LONG_NOSED_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> PATCHED_SHEEP_SPAWN_EGG_ITEM = registerItem("patched_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.PATCHED_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> ROCKY_SHEEP_SPAWN_EGG_ITEM = registerItem("rocky_sheep_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.ROCKY_SHEEP, -1, -1, getSettings()));
    public static final RegistrySupplier<Block> POTTED_ENZIAN = registerBlockWithoutItem("potted_enzian", () -> new FlowerPotBlock(ObjectRegistry.ENZIAN.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_FIRE_LILY = registerBlockWithoutItem("potted_fire_lily", () -> new FlowerPotBlock(ObjectRegistry.FIRE_LILY.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_ALPINE_POPPY = registerBlockWithoutItem("potted_alpine_poppy", () -> new FlowerPotBlock(ObjectRegistry.ALPINE_POPPY.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_SAXIFRAGE = registerBlockWithoutItem("potted_saxifrage", () -> new FlowerPotBlock(ObjectRegistry.SAXIFRAGE.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_DELPHINIUM = registerBlockWithoutItem("potted_delphinium", () -> new FlowerPotBlock(ObjectRegistry.DELPHINIUM.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_ERIOPHORUM = registerBlockWithoutItem("potted_eriophorum", () -> new FlowerPotBlock(ObjectRegistry.ERIOPHORUM.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_PINE_SAPLING = registerBlockWithoutItem("potted_pine_sapling", () -> new FlowerPotBlock(ObjectRegistry.PINE_SAPLING.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> W_POTTED_PINE_SAPLING = registerBlockWithoutItem("wooden_potted_pine_sapling", () -> new WoodenFlowerPotBlock(PINE_SAPLING.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_OAK_SAPLING = registerBlockWithoutItem("wooden_potted_oak_sapling", () -> new WoodenFlowerPotBlock(Blocks.OAK_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ALPINE_POPPY = registerBlockWithoutItem("wooden_potted_alpine_poppy", () -> new WoodenFlowerPotBlock(ALPINE_POPPY.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_SPRUCE_SAPLING = registerBlockWithoutItem("wooden_potted_spruce_sapling", () -> new WoodenFlowerPotBlock(Blocks.SPRUCE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BIRCH_SAPLING = registerBlockWithoutItem("wooden_potted_birch_sapling", () -> new WoodenFlowerPotBlock(Blocks.BIRCH_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_JUNGLE_SAPLING = registerBlockWithoutItem("wooden_potted_jungle_sapling", () -> new WoodenFlowerPotBlock(Blocks.JUNGLE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ACACIA_SAPLING = registerBlockWithoutItem("wooden_potted_acacia_sapling", () -> new WoodenFlowerPotBlock(Blocks.ACACIA_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_DARK_OAK_SAPLING = registerBlockWithoutItem("wooden_potted_dark_oak_sapling", () -> new WoodenFlowerPotBlock(Blocks.DARK_OAK_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_MANGROVE_PROPAGULE = registerBlockWithoutItem("wooden_potted_mangrove_propagule", () -> new WoodenFlowerPotBlock(Blocks.MANGROVE_PROPAGULE, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_DANDELION = registerBlockWithoutItem("wooden_potted_dandelion", () -> new WoodenFlowerPotBlock(Blocks.DANDELION, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_POPPY = registerBlockWithoutItem("wooden_potted_poppy", () -> new WoodenFlowerPotBlock(Blocks.POPPY, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BLUE_ORCHID = registerBlockWithoutItem("wooden_potted_blue_orchid", () -> new WoodenFlowerPotBlock(Blocks.BLUE_ORCHID, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ALLIUM = registerBlockWithoutItem("wooden_potted_allium", () -> new WoodenFlowerPotBlock(Blocks.ALLIUM, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_AZURE_BLUET = registerBlockWithoutItem("wooden_potted_azure_bluet", () -> new WoodenFlowerPotBlock(Blocks.AZURE_BLUET, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_RED_TULIP = registerBlockWithoutItem("wooden_potted_red_tulip", () -> new WoodenFlowerPotBlock(Blocks.RED_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ORANGE_TULIP = registerBlockWithoutItem("wooden_potted_orange_tulip", () -> new WoodenFlowerPotBlock(Blocks.ORANGE_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WHITE_TULIP = registerBlockWithoutItem("wooden_potted_white_tulip", () -> new WoodenFlowerPotBlock(Blocks.WHITE_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_PINK_TULIP = registerBlockWithoutItem("wooden_potted_pink_tulip", () -> new WoodenFlowerPotBlock(Blocks.PINK_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_OXEYE_DAISY = registerBlockWithoutItem("wooden_potted_oxeye_daisy", () -> new WoodenFlowerPotBlock(Blocks.OXEYE_DAISY, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_CORNFLOWER = registerBlockWithoutItem("wooden_potted_cornflower", () -> new WoodenFlowerPotBlock(Blocks.CORNFLOWER, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_LILY_OF_THE_VALLEY = registerBlockWithoutItem("wooden_potted_lily_of_the_valley", () -> new WoodenFlowerPotBlock(Blocks.LILY_OF_THE_VALLEY, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WITHER_ROSE = registerBlockWithoutItem("wooden_potted_wither_rose", () -> new WoodenFlowerPotBlock(Blocks.WITHER_ROSE, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_AZALEA = registerBlockWithoutItem("wooden_potted_azalea", () -> new WoodenFlowerPotBlock(Blocks.AZALEA, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_FLOWERING_AZALEA = registerBlockWithoutItem("wooden_potted_flowering_azalea", () -> new WoodenFlowerPotBlock(Blocks.FLOWERING_AZALEA, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_DELPHINIUM = registerBlockWithoutItem("wooden_potted_delphinium", () -> new WoodenFlowerPotBlock(DELPHINIUM.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_SAXIFRAGE = registerBlockWithoutItem("wooden_potted_saxifrage", () -> new WoodenFlowerPotBlock(SAXIFRAGE.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ENZIAN = registerBlockWithoutItem("wooden_potted_enzian", () -> new WoodenFlowerPotBlock(ENZIAN.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_FIRE_LILY = registerBlockWithoutItem("wooden_potted_fire_lily", () -> new WoodenFlowerPotBlock(FIRE_LILY.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ERIOPHORUM = registerBlockWithoutItem("wooden_potted_eriophorum", () -> new WoodenFlowerPotBlock(ERIOPHORUM.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> ALPINE_OAK_LOG = registerBlock("alpine_oak_log", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), null);
    public static final RegistrySupplier<Block> ALPINE_BIRCH_LOG = registerBlock("alpine_birch_log", () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), null);
    public static final RegistrySupplier<Block> PINE_LEAVES_2 = registerBlockWithoutItem("pine_leaves_2", () -> new LeavesBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_LEAVES)));

    public static void init() {
        Meadow.LOGGER.debug("Registering Blocks and Items for " + Meadow.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
    }

    public static void commonInit() {
        registerFuels();
    }

    public static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, Meadow.MEADOW_TAB);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block, @Nullable ItemGroup tab) {
        return Util.registerWithItem(BLOCKS, BLOCKS_REGISTRAR, ITEMS, ITEMS_REGISTRAR, new MeadowIdentifier(name), block, tab);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlockWithoutItem(String path, Supplier<T> block) {
        return Util.registerWithoutItem(BLOCKS, BLOCKS_REGISTRAR, new MeadowIdentifier(path), block);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return Util.registerItem(ITEMS, ITEMS_REGISTRAR, new MeadowIdentifier(path), itemSupplier);
    }


    private static void registerFuels() {
        Meadow.LOGGER.info("Registering Fuels for " + Meadow.MOD_ID);
        FuelRegistry.register(300, PINE_WOOD.get(), STRIPPED_PINE_LOG.get(), STRIPPED_PINE_WOOD.get(), PINE_BEAM.get(), PINE_PLANKS.get(), PINE_STAIRS.get(), PINE_SLAB.get(), PINE_FENCE.get(), PINE_RAILING.get(),
                FLECKED_WOOL.get(), HIGHLAND_WOOL.get(), PATCHED_WOOL.get(), ROCKY_SHEEP_WOOL.get(), UMBRA_WOOL.get(), INKY_WOOL.get(), WARPED_WOOL.get(),
                ALPINE_OAK_LOG.get(), ALPINE_BIRCH_LOG.get(), PINE_LEAVES.get(), ALPINE_BIRCH_LEAVES_HANGING.get());
        FuelRegistry.register(4000, PINE_LOG.get());
    }

    public static void registerArmor() {
        Registry.register(Registry.ITEM, new MeadowIdentifier("fur_helmet"), FUR_HELMET.get());
        Registry.register(Registry.ITEM, new MeadowIdentifier("fur_chestplate"), FUR_CHESTPLATE.get());
        Registry.register(Registry.ITEM, new MeadowIdentifier("fur_leggings"), FUR_LEGGINGS.get());
        Registry.register(Registry.ITEM, new MeadowIdentifier("fur_boots"), FUR_BOOTS.get());
    }




    private static Item.Settings getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Item.Settings getSettings(Consumer<Item.Settings> consumer) {
        Item.Settings settings = new Item.Settings().group(Meadow.MEADOW_TAB);
        consumer.accept(settings);
        return settings;
    }
}
