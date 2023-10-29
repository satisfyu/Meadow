package net.satisfyu.meadow.registry;

import com.google.common.collect.Lists;
import de.cristelknight.doapi.Util;
import de.cristelknight.doapi.common.block.ChairBlock;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.*;
import net.satisfyu.meadow.item.*;
import net.satisfyu.meadow.util.GeneralUtil;
import net.satisfyu.meadow.util.MeadowIdentifier;
import net.satisfyu.meadow.util.WoodenCauldronBehavior;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Meadow.MOD_ID, Registries.ITEM);
    public static final Registrar<Item> ITEMS_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Meadow.MOD_ID, Registries.BLOCK);
    public static final Registrar<Block> BLOCKS_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Block> ALPINE_SALT_ORE = registerBlock("alpine_salt_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_COAL_ORE = registerBlock("alpine_coal_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_LAPIS_ORE = registerBlock("alpine_lapis_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_GOLD_ORE = registerBlock("alpine_gold_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_EMERALD_ORE = registerBlock("alpine_emerald_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_IRON_ORE = registerBlock("alpine_iron_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_COPPER_ORE = registerBlock("alpine_copper_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_DIAMOND_ORE = registerBlock("alpine_diamond_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> ALPINE_REDSTONE_ORE = registerBlock("alpine_redstone_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f).requiresCorrectToolForDrops()));
    public static final RegistrySupplier<Block> LIMESTONE = registerBlock("limestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LIMESTONE_STAIRS = registerBlock("limestone_stairs", () -> new StairBlock(LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_SLAB = registerBlock("limestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE = registerBlock("cobbled_limestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_STAIRS = registerBlock("cobbled_limestone_stairs", () -> new StairBlock(COBBLED_LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_SLAB = registerBlock("cobbled_limestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs", () -> new StairBlock(LIMESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE = registerBlock("mossy_cobbled_limestone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_COBBLESTONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_STAIRS = registerBlock("mossy_cobbled_limestone_stairs", () -> new StairBlock(MOSSY_COBBLED_LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_SLAB = registerBlock("mossy_cobbled_limestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICKS = registerBlock("mossy_limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_STAIRS = registerBlock("mossy_limestone_brick_stairs", () -> new StairBlock(LIMESTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_SLAB = registerBlock("mossy_limestone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MOSSY_LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_LIMESTONE_BRICKS = registerBlock("cracked_limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<Block> CHISELED_LIMESTONE_BRICKS = registerBlock("chiseled_limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final RegistrySupplier<Block> POLISHED_LIMESTONE_BRICKS = registerBlock("polished_limestone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistrySupplier<Block> LIMESTONE_WALL = registerBlock("limestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_WALL = registerBlock("cobbled_limestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_WALL = registerBlock("mossy_cobbled_limestone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_WALL = registerBlock("mossy_limestone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MOSSY_LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> STOVE = registerBlock("stove_tiles", () -> new MainStoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<Block> STOVE_WOOD = registerBlock("stove_tiles_wood", () -> new StoveBlockWood(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(state -> state.getValue(StoveBlockWood.LIT) ? 13 : 0).randomTicks(), Direction.UP));
    public static final RegistrySupplier<Block> STOVE_LID = registerBlock("stove_tiles_lid", () -> new StoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS), Direction.UP));
    public static final RegistrySupplier<Block> STOVE_BENCH = registerBlock("stove_tiles_bench", () -> new TiledBench(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    public static final RegistrySupplier<Block> PINE_LOG = registerBlock("pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_WOOD = registerBlock("pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_PINE_WOOD = registerBlock("stripped_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_PINE_LOG = registerBlock("stripped_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_BEAM = registerBlock("pine_beam", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_PLANKS = registerBlock("pine_planks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f, 3.0f).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final RegistrySupplier<Block> PINE_STAIRS = registerBlock("pine_stairs", () -> new StairBlock(PINE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.ACACIA_STAIRS)));
    public static final RegistrySupplier<Block> PINE_SLAB = registerBlock("pine_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_SLAB)));
    public static final RegistrySupplier<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().noCollission().strength(0.5f).sound(SoundType.WOOD).mapColor(PINE_PLANKS.get().defaultMapColor()), BlockSetType.OAK));
    public static final RegistrySupplier<Block> PINE_BUTTON = registerBlock("pine_button", () -> createWoodenButtonBlock(BlockSetType.OAK, FeatureFlags.VANILLA));
    public static final RegistrySupplier<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR), BlockSetType.OAK));
    public static final RegistrySupplier<Block> PINE_DOOR = registerBlock("pine_door", () -> new DoorBlock(BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(PINE_PLANKS.get().defaultMapColor()), BlockSetType.OAK));
    public static final RegistrySupplier<Block> PINE_BARN_TRAPDOOR = registerBlock("pine_barn_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_TRAPDOOR), BlockSetType.OAK));
    public static final RegistrySupplier<Block> PINE_BARN_DOOR = registerBlock("pine_barn_door", () -> new DoorBlock(BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.WOOD).noOcclusion().mapColor(PINE_PLANKS.get().defaultMapColor()), BlockSetType.OAK));
    public static final RegistrySupplier<Block> PINE_FENCE = registerBlock("pine_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD).mapColor(PINE_PLANKS.get().defaultMapColor()), WoodType.OAK));
    public static final RegistrySupplier<Block> PINE_RAILING = registerBlock("pine_railing", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK = registerBlock("shutter_block", () -> new ShutterBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_POPPY = registerBlock("shutter_block_poppy", () -> new ShutterBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_FIR = registerBlock("shutter_block_fir", () -> new ShutterBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_BERRY = registerBlock("shutter_block_berry", () -> new ShutterBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> SHELF = registerBlock("shelf", () -> new ShelfBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundRegistry.SHELF_OPEN, SoundRegistry.SHELF_CLOSED));
    public static final RegistrySupplier<Block> CHEESE_RACK = registerBlock("cheese_rack", () -> new CheeseRackBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> CHAIR = registerBlock("chair", () -> new ChairBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> TABLE = registerBlock("table", () -> new TableBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> BENCH = registerBlock("bench", () -> new BenchBlock(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> STONE_TABLE = registerBlock("stone_table", () -> new TableBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f, 0.0f).sound(SoundType.STONE).noOcclusion()));
    public static final RegistrySupplier<Block> STONE_BENCH = registerBlock("stone_bench", () -> new BenchBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(4f, 0.0f).sound(SoundType.STONE)));
    public static final RegistrySupplier<Block> WINDOW = registerBlock("window", () -> new WindowBlock_1(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistrySupplier<Block> WINDOW_2 = registerBlock("window_2", () -> new WindowBlock_2(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistrySupplier<Block> WINDOW_3 = registerBlock("window_3", () -> new WindowBlock_3(BlockBehaviour.Properties.copy(Blocks.GLASS).strength(0.3f).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistrySupplier<Block> FLECKED_WOOL = registerBlock("flecked_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> FLECKED_CARPET = registerBlock("flecked_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> FLECKED_BED = registerBlock("flecked_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> HIGHLAND_WOOL = registerBlock("highland_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> HIGHLAND_CARPET = registerBlock("highland_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> HIGHLAND_BED = registerBlock("highland_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> PATCHED_WOOL = registerBlock("patched_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> PATCHED_CARPET = registerBlock("patched_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> PATCHED_BED = registerBlock("patched_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> ROCKY_WOOL = registerBlock("rocky_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> ROCKY_CARPET = registerBlock("rocky_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> ROCKY_BED = registerBlock("rocky_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> UMBRA_WOOL = registerBlock("umbra_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> UMBRA_CARPET = registerBlock("umbra_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> UMBRA_BED = registerBlock("umbra_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> INKY_WOOL = registerBlock("inky_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> INKY_CARPET = registerBlock("inky_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> INKY_BED = registerBlock("inky_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> WARPED_WOOL = registerBlock("warped_wool", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> WARPED_CARPET = registerBlock("warped_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> WARPED_BED = registerBlock("warped_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> STRAW_BED = registerBlock("straw_bed", () -> new MeadowBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion()));
    public static final RegistrySupplier<Block> CHEESE_FORM = registerBlock("cheese_form", () -> new CheeseFormBlock(BlockBehaviour.Properties.of().noOcclusion().strength(0.1f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> FONDUE = registerBlock("fondue", () -> new FondueBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.METAL).lightLevel((blockState) -> 13)));
    public static final RegistrySupplier<Block> COOKING_CAULDRON = registerBlock("cooking_pot", () -> new CookingCauldronBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.METAL).lightLevel(state -> state.getValue(CookingCauldronBlock.HANGING) ? 13 : 0)));
    public static final RegistrySupplier<Block> FRAME = registerBlock("frame", () -> new FrameBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().lightLevel((blockState) -> 13).strength(3.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistrySupplier<Block> WOODCUTTER = registerBlock("woodcutter", () -> new WoodcutterBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> WOODEN_CAULDRON = registerBlock("wooden_cauldron", () -> new WoodenCauldronBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(2.0f).noOcclusion().sound(SoundType.WOOD).mapColor(MapColor.PODZOL)));
    public static final RegistrySupplier<Block> WHEELBARROW = registerBlock("wheelbarrow", () -> new WheelBarrowBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> WOODEN_WATER_CAULDRON = registerBlockWithoutItem("wooden_water_cauldron", () -> new LayeredCauldronBlock(BlockBehaviour.Properties.copy(ObjectRegistry.WOODEN_CAULDRON.get()), LayeredCauldronBlock.RAIN, WoodenCauldronBehavior.WATER));
    public static final RegistrySupplier<Block> WOODEN_POWDER_SNOW_CAULDRON = registerBlockWithoutItem("wooden_powder_snow_cauldron", () -> new WoodenPowderSnowCauldronBlock(BlockBehaviour.Properties.copy(ObjectRegistry.WOODEN_CAULDRON.get()), LayeredCauldronBlock.SNOW, WoodenCauldronBehavior.POWDER_SNOW));
    public static final RegistrySupplier<Block> FIRE_LOG = registerBlock("fire_log", () -> new FireLog(BlockBehaviour.Properties.of().strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> CAN = registerBlock("can", () -> new CanBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(0.8f).noOcclusion().sound(SoundType.METAL)));
    public static final RegistrySupplier<Block> CLIMBING_ROPE = registerBlockWithoutItem("climbing_rope", () -> new ClimbingRopeBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).noCollission()));
    public static final RegistrySupplier<Block> WATERING_CAN = registerBlockWithoutItem("watering_can", () -> new WateringCanBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Item> WATERING_CAN_ITEM = registerItem("watering_can", () -> new WateringCanItem(ObjectRegistry.WATERING_CAN.get(), getSettings().durability(25)));
    public static final RegistrySupplier<Block> CLIMBING_ROPE_TOPMOUNT = registerBlock("climbing_rope_topmount", () -> new ClimbingRopeTopmountBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).noCollission()));
    public static final RegistrySupplier<Block> OIL_LANTERN = registerBlock("oil_lantern", () -> new OilLantern(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5f).sound(SoundType.LANTERN).lightLevel((blockState) -> 13).noOcclusion()));
    public static final RegistrySupplier<Block> CAMERA = registerBlock("camera", () -> new CameraBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> DOORMAT = registerBlock("doormat", () -> new DoormatBlock(BlockBehaviour.Properties.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> FLOWER_POT_BIG = registerBlock("flower_pot_big", () -> new BigFlowerPotBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> WOODEN_FLOWER_POT = registerBlock("wooden_flower_pot", () -> new WoodenFlowerPotBlock(Blocks.AIR, BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final RegistrySupplier<Block> WOODEN_FLOWER_BOX = registerBlock("flower_box", () -> new FlowerBoxBlock(BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Item> FUR_HELMET = registerItem("fur_helmet", () -> new FurHead(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.EPIC)));
    public static final RegistrySupplier<Item> FUR_CHESTPLATE = registerItem("fur_chestplate", () -> new FurChest(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> FUR_LEGGINGS = registerItem("fur_leggings", () -> new FurLegs(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Item> FUR_BOOTS = registerItem("fur_boots", () -> new FurBoots(MaterialRegistry.FUR_ARMOR, getSettings().rarity(Rarity.RARE)));
    public static final RegistrySupplier<Block> SMALL_FIR = registerBlock("small_fir", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> PINE_SAPLING = registerBlock("pine_sapling", () -> new SaplingBlock(new AbstractTreeGrower() {
        @Override
        protected @NotNull ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random, boolean bees) {
            return GeneralUtil.configuredFeatureKey("pine");
        }
    }, BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING)));
    public static final RegistrySupplier<Block> ALPINE_POPPY = registerBlock("alpine_poppy", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> DELPHINIUM = registerBlock("delphinium", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> SAXIFRAGE = registerBlock("saxifrage", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ENZIAN = registerBlock("enzian", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> FIRE_LILY = registerBlock("fire_lily", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ERIOPHORUM = registerBlock("eriophorum", () -> new FlowerBlock(MobEffects.HEAL, 1, BlockBehaviour.Properties.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ERIOPHORUM_TALL = registerBlock("eriophorum_tall", () -> new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> PINE_LEAVES = registerBlock("pine_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false)));
    public static final RegistrySupplier<Block> ALPINE_BIRCH_LEAVES_HANGING = registerBlock("alpine_birch_leaves_hanging", () -> new LeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion().isViewBlocking((state, world, pos) -> false).isSuffocating((state, world, pos) -> false)));
    public static final RegistrySupplier<Item> CHEESECAKE_SLICE = registerItem("cheesecake_slice", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(7).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> CHEESE_TART_SLICE = registerItem("cheese_tart_slice", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(7).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_CHEESE = registerItem("piece_of_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_SHEEP_CHEESE = registerItem("piece_of_sheep_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_GRAIN_CHEESE = registerItem("piece_of_grain_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_AMETHYST_CHEESE = registerItem("piece_of_amethyst_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_BUFFALO_CHEESE = registerItem("piece_of_buffalo_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_GOAT_CHEESE = registerItem("piece_of_goat_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> PIECE_OF_WARPED_CHEESE = registerItem("piece_of_warped_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 1), 1.0F).build())));
    public static final RegistrySupplier<Block> CHEESECAKE = registerBlock("cheesecake", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.CHEESECAKE_SLICE, true));
    public static final RegistrySupplier<Block> CHEESE_TART = registerBlock("cheese_tart", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.CHEESE_TART_SLICE, true));
    public static final RegistrySupplier<Block> CHEESE_BLOCK = registerBlock("cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_CHEESE, true));
    public static final RegistrySupplier<Block> SHEEP_CHEESE_BLOCK = registerBlock("sheep_cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_SHEEP_CHEESE, false));
    public static final RegistrySupplier<Block> GRAIN_CHEESE_BLOCK = registerBlock("grain_cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_GRAIN_CHEESE, false));
    public static final RegistrySupplier<Block> AMETHYST_CHEESE_BLOCK = registerBlock("amethyst_cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_AMETHYST_CHEESE, true));
    public static final RegistrySupplier<Block> BUFFALO_CHEESE_BLOCK = registerBlock("buffalo_cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_BUFFALO_CHEESE, false));
    public static final RegistrySupplier<Block> GOAT_CHEESE_BLOCK = registerBlock("goat_cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_GOAT_CHEESE, false));
    public static final RegistrySupplier<Block> WARPED_CHEESE_BLOCK = registerBlock("warped_cheese_block", () -> new WarpedCheeseBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_WARPED_CHEESE, false));
    public static final RegistrySupplier<Item> ALPINE_SALT = registerItem("alpine_salt", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> RENNET = registerItem("rennet", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> CHEESE_SANDWICH = registerItem("cheese_sandwich", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.4f).build())));
    public static final RegistrySupplier<Item> CHEESE_ROLL = registerItem("cheese_roll", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.4f).build())));
    public static final RegistrySupplier<Item> CHEESE_STICK = registerItem("cheese_stick", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.8f).build())));
    public static final RegistrySupplier<Item> RAW_BEAR_MEAT = registerItem("raw_bear_meat", () -> new CraftingIngredientItem(getSettings().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())));
    public static final RegistrySupplier<Item> COOKED_BEAR_MEAT = registerItem("cooked_bear_meat", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Block> ROASTED_HAM = registerBlock("roasted_ham", () -> new MealBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), false));
    public static final RegistrySupplier<Item> HAM_CHEESE = registerItem("ham_cheese", () -> new Item(getSettings().food(new FoodProperties.Builder().nutrition(8).saturationMod(1.6f).build())));
    public static final RegistrySupplier<Item> WOODEN_BUCKET = registerItem("wooden_bucket", () -> new WoodenBucket(Fluids.EMPTY, getSettings().stacksTo(16)));
    public static final RegistrySupplier<Item> WOODEN_WATER_BUCKET = registerItem("wooden_water_bucket", () -> new WoodenBucket(Fluids.WATER, getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_MILK_BUCKET = registerItem("wooden_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_SHEEP_MILK_BUCKET = registerItem("wooden_sheep_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_BUFFALO_MILK_BUCKET = registerItem("wooden_buffalo_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_GOAT_MILK_BUCKET = registerItem("wooden_goat_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_WARPED_MILK_BUCKET = registerItem("wooden_warped_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_GRAIN_MILK_BUCKET = registerItem("wooden_grain_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WOODEN_AMETHYST_MILK_BUCKET = registerItem("wooden_amethyst_milk_bucket", () -> new WoodenMilkBucket(getSettings().stacksTo(1).craftRemainder(ObjectRegistry.WOODEN_BUCKET.get())));
    public static final RegistrySupplier<Item> WATER_BUFFALO_SPAWN_EGG_ITEM = registerItem("water_buffalo_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.WATER_BUFFALO, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> WOOLY_COW_SPAWN_EGG_ITEM = registerItem("wooly_cow_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.SHEARABLE_MEADOW_VAR_COW, -1, -1, getSettings()));
    public static final RegistrySupplier<Item> BROWN_BEAR_SPAWN_EGG_ITEM = registerItem("brown_bear_spawn_egg", () -> new ArchitecturySpawnEggItem(EntityRegistry.BROWN_BEAR, -1, -1, getSettings()));
    public static final RegistrySupplier<Block> POTTED_ENZIAN = registerBlockWithoutItem("potted_enzian", () -> new FlowerPotBlock(ObjectRegistry.ENZIAN.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_FIRE_LILY = registerBlockWithoutItem("potted_fire_lily", () -> new FlowerPotBlock(ObjectRegistry.FIRE_LILY.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_ALPINE_POPPY = registerBlockWithoutItem("potted_alpine_poppy", () -> new FlowerPotBlock(ObjectRegistry.ALPINE_POPPY.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_SAXIFRAGE = registerBlockWithoutItem("potted_saxifrage", () -> new FlowerPotBlock(ObjectRegistry.SAXIFRAGE.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_DELPHINIUM = registerBlockWithoutItem("potted_delphinium", () -> new FlowerPotBlock(ObjectRegistry.DELPHINIUM.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_ERIOPHORUM = registerBlockWithoutItem("potted_eriophorum", () -> new FlowerPotBlock(ObjectRegistry.ERIOPHORUM.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_PINE_SAPLING = registerBlockWithoutItem("potted_pine_sapling", () -> new FlowerPotBlock(ObjectRegistry.PINE_SAPLING.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> W_POTTED_PINE_SAPLING = registerBlockWithoutItem("wooden_potted_pine_sapling", () -> new WoodenFlowerPotBlock(PINE_SAPLING.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_OAK_SAPLING = registerBlockWithoutItem("wooden_potted_oak_sapling", () -> new WoodenFlowerPotBlock(Blocks.OAK_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ALPINE_POPPY = registerBlockWithoutItem("wooden_potted_alpine_poppy", () -> new WoodenFlowerPotBlock(ALPINE_POPPY.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_SPRUCE_SAPLING = registerBlockWithoutItem("wooden_potted_spruce_sapling", () -> new WoodenFlowerPotBlock(Blocks.SPRUCE_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BIRCH_SAPLING = registerBlockWithoutItem("wooden_potted_birch_sapling", () -> new WoodenFlowerPotBlock(Blocks.BIRCH_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_JUNGLE_SAPLING = registerBlockWithoutItem("wooden_potted_jungle_sapling", () -> new WoodenFlowerPotBlock(Blocks.JUNGLE_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ACACIA_SAPLING = registerBlockWithoutItem("wooden_potted_acacia_sapling", () -> new WoodenFlowerPotBlock(Blocks.ACACIA_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_DARK_OAK_SAPLING = registerBlockWithoutItem("wooden_potted_dark_oak_sapling", () -> new WoodenFlowerPotBlock(Blocks.DARK_OAK_SAPLING, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_MANGROVE_PROPAGULE = registerBlockWithoutItem("wooden_potted_mangrove_propagule", () -> new WoodenFlowerPotBlock(Blocks.MANGROVE_PROPAGULE, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_DANDELION = registerBlockWithoutItem("wooden_potted_dandelion", () -> new WoodenFlowerPotBlock(Blocks.DANDELION, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_POPPY = registerBlockWithoutItem("wooden_potted_poppy", () -> new WoodenFlowerPotBlock(Blocks.POPPY, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BLUE_ORCHID = registerBlockWithoutItem("wooden_potted_blue_orchid", () -> new WoodenFlowerPotBlock(Blocks.BLUE_ORCHID, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ALLIUM = registerBlockWithoutItem("wooden_potted_allium", () -> new WoodenFlowerPotBlock(Blocks.ALLIUM, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_AZURE_BLUET = registerBlockWithoutItem("wooden_potted_azure_bluet", () -> new WoodenFlowerPotBlock(Blocks.AZURE_BLUET, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_RED_TULIP = registerBlockWithoutItem("wooden_potted_red_tulip", () -> new WoodenFlowerPotBlock(Blocks.RED_TULIP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ORANGE_TULIP = registerBlockWithoutItem("wooden_potted_orange_tulip", () -> new WoodenFlowerPotBlock(Blocks.ORANGE_TULIP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WHITE_TULIP = registerBlockWithoutItem("wooden_potted_white_tulip", () -> new WoodenFlowerPotBlock(Blocks.WHITE_TULIP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_PINK_TULIP = registerBlockWithoutItem("wooden_potted_pink_tulip", () -> new WoodenFlowerPotBlock(Blocks.PINK_TULIP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_OXEYE_DAISY = registerBlockWithoutItem("wooden_potted_oxeye_daisy", () -> new WoodenFlowerPotBlock(Blocks.OXEYE_DAISY, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_CORNFLOWER = registerBlockWithoutItem("wooden_potted_cornflower", () -> new WoodenFlowerPotBlock(Blocks.CORNFLOWER, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_LILY_OF_THE_VALLEY = registerBlockWithoutItem("wooden_potted_lily_of_the_valley", () -> new WoodenFlowerPotBlock(Blocks.LILY_OF_THE_VALLEY, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WITHER_ROSE = registerBlockWithoutItem("wooden_potted_wither_rose", () -> new WoodenFlowerPotBlock(Blocks.WITHER_ROSE, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_AZALEA = registerBlockWithoutItem("wooden_potted_azalea", () -> new WoodenFlowerPotBlock(Blocks.AZALEA, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_FLOWERING_AZALEA = registerBlockWithoutItem("wooden_potted_flowering_azalea", () -> new WoodenFlowerPotBlock(Blocks.FLOWERING_AZALEA, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_DELPHINIUM = registerBlockWithoutItem("wooden_potted_delphinium", () -> new WoodenFlowerPotBlock(DELPHINIUM.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_SAXIFRAGE = registerBlockWithoutItem("wooden_potted_saxifrage", () -> new WoodenFlowerPotBlock(SAXIFRAGE.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ENZIAN = registerBlockWithoutItem("wooden_potted_enzian", () -> new WoodenFlowerPotBlock(ENZIAN.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_FIRE_LILY = registerBlockWithoutItem("wooden_potted_fire_lily", () -> new WoodenFlowerPotBlock(FIRE_LILY.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ERIOPHORUM = registerBlockWithoutItem("wooden_potted_eriophorum", () -> new WoodenFlowerPotBlock(ERIOPHORUM.get(), BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> ALPINE_OAK_LOG = registerBlock("alpine_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> ALPINE_BIRCH_LOG = registerBlock("alpine_birch_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_LEAVES_2 = registerBlockWithoutItem("pine_leaves_2", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_LEAVES)));

    public static void init() {
        Meadow.LOGGER.debug("Registering Blocks and Items for " + Meadow.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
        createStandards();
        registerCompostable();
    }

    public static void registerCompostable() {
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_GOAT_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_GRAIN_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_AMETHYST_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_SHEEP_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PIECE_OF_WARPED_CHEESE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHEESECAKE_SLICE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHEESE_TART_SLICE.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHEESE_SANDWICH.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHEESE_ROLL.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHEESE_STICK.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.CHEESE_BLOCK.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.WARPED_CHEESE_BLOCK.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.AMETHYST_CHEESE_BLOCK.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GOAT_CHEESE_BLOCK.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SHEEP_CHEESE_BLOCK.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.GRAIN_CHEESE_BLOCK.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.ENZIAN.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.DELPHINIUM.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.ALPINE_POPPY.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SAXIFRAGE.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.ERIOPHORUM.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.SMALL_FIR.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.PINE_SAPLING.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.ERIOPHORUM_TALL.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ObjectRegistry.FIRE_LILY.get(), 0.3f);
    }



    public static final List<Supplier<Block>> STANDARD_BLOCKS = Lists.newArrayList();
    public static final List<Supplier<Block>> STANDARD_WALL_BLOCKS = Lists.newArrayList();
    public static final List<Supplier<Block>> STANDARD_FLOOR_BLOCKS = Lists.newArrayList();
    public static Supplier<Block> MEADOW_STANDARD;

    private static void createStandards() {
        MEADOW_STANDARD = BLOCKS.register(Meadow.MOD_ID("meadow_standard"), () -> new MeadowStandardBlock(properties(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD)));
        Supplier<Block> adjWall = BLOCKS.register(Meadow.MOD_ID("meadow_wall_standard"), () -> new MeadowStandardWallBlock(properties(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD).dropsLike(MEADOW_STANDARD.get())));

        ITEMS.register(Meadow.MOD_ID("meadow_standard"), () -> new StandingAndWallBlockItem(MEADOW_STANDARD.get(), adjWall.get(), new Item.Properties().stacksTo(16).rarity(Rarity.UNCOMMON), Direction.DOWN));
        STANDARD_BLOCKS.add(MEADOW_STANDARD);
        STANDARD_BLOCKS.add(adjWall);
    }

    public static BlockBehaviour.Properties properties(float strength) {
        return properties(strength, strength);
    }

    public static BlockBehaviour.Properties properties(float breakSpeed, float explosionResist) {
        return BlockBehaviour.Properties.of().strength(breakSpeed, explosionResist);
    }


    public static void commonInit() {
        registerFuels();
    }
    

    public static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        return Util.registerWithItem(BLOCKS, BLOCKS_REGISTRAR, ITEMS, ITEMS_REGISTRAR, new MeadowIdentifier(name), block);
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
                FLECKED_WOOL.get(), HIGHLAND_WOOL.get(), PATCHED_WOOL.get(), ROCKY_WOOL.get(), UMBRA_WOOL.get(), INKY_WOOL.get(), WARPED_WOOL.get(),
                ALPINE_OAK_LOG.get(), ALPINE_BIRCH_LOG.get(), PINE_LEAVES.get(), ALPINE_BIRCH_LEAVES_HANGING.get());
        FuelRegistry.register(4000, FIRE_LOG.get());
    }

    private static ButtonBlock createWoodenButtonBlock(BlockSetType blockSetType, FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            settings = settings.requiredFeatures(requiredFeatures);
        }

        return new ButtonBlock(settings, blockSetType, 30, true);
    }


    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }
}
