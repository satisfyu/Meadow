package net.satisfyu.meadow.registry;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.platform.Platform;
import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.MeadowIdentifier;
import net.satisfyu.meadow.block.cheeseForm.CheeseFormBlock;
import net.satisfyu.meadow.block.cheeseRack.CheeseRackBlock;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock;
import net.satisfyu.meadow.block.cookingPot.CookingPotBlock;
import net.satisfyu.meadow.block.custom.*;
import net.satisfyu.meadow.block.flowerPot.BigFlowerPotBlock;
import net.satisfyu.meadow.block.flowerPot.FlowerBoxBlock;
import net.satisfyu.meadow.block.flowerPot.WheelBarrowBlock;
import net.satisfyu.meadow.block.fondueBlock.FondueBlock;
import net.satisfyu.meadow.block.shelfBlock.ShelfBlock;
import net.satisfyu.meadow.block.windowShutter.ShutterBlock;
import net.satisfyu.meadow.block.woodCutter.WoodcutterBlock;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBehavior;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBlock;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.ModItemGroup;
import net.satisfyu.meadow.item.custom.*;
import net.satisfyu.meadow.sound.ModSounds;
import net.satisfyu.meadow.world.feature.custom.tree.PineSaplingGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ObjectRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Meadow.MOD_ID, Registry.ITEM_KEY);
    public static final Registrar<Item> ITEMS_REGISTRAR = ITEMS.getRegistrar();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Meadow.MOD_ID, Registry.BLOCK_KEY);
    public static final Registrar<Block> BLOCKS_REGISTRAR = BLOCKS.getRegistrar();

    public static final RegistrySupplier<Item> ALPINE_SALT = registerItem("alpine_salt",
            () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Block> ALPINE_SALT_ORE = registerBlock("alpine_salt_ore",
            () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_COAL_ORE = registerBlock("alpine_coal_ore",
            () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_LAPIS_ORE = registerBlock("alpine_lapis_ore",
            () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_GOLD_ORE = registerBlock("alpine_gold_ore",
            () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_EMERALD_ORE = registerBlock("alpine_emerald_ore",
            () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_IRON_ORE = registerBlock("alpine_iron_ore",
            () -> new OreBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f).requiresTool()));
    public static final RegistrySupplier<Block> ALPINE_OAK_LOG = registerBlock("alpine_oak_log",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), null);
    public static final RegistrySupplier<Block> ALPINE_BIRCH_LOG = registerBlock("alpine_birch_log",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), null);
    public static final RegistrySupplier<Block> ALPINE_BIRCH_LEAVES_HANGING = registerBlock("alpine_birch_leaves_hanging",
            () -> new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().blockVision((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)));
    public static final RegistrySupplier<Block> ALPINE_GRASS = registerBlock("alpine_grass",
            () -> new FernBlock(AbstractBlock.Settings.copy(Blocks.GRASS)));
    public static final RegistrySupplier<Block> ALPINE_GRASS_TALL = registerBlock("alpine_grass_tall",
            () -> new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.TALL_GRASS)));
    public static final RegistrySupplier<Block> ALPINE_POPPY = registerBlock("alpine_poppy",
            () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> LIMESTONE = registerBlock("limestone",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final RegistrySupplier<Block> LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            () -> new StairsBlock(LIMESTONE.get().getDefaultState(), AbstractBlock.Settings.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_SLAB = registerBlock("limestone_slab",
            () -> new SlabBlock(AbstractBlock.Settings.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_WALL = registerBlock("limestone_wall",
            () -> new WallBlock(AbstractBlock.Settings.copy(LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE = registerBlock("cobbled_limestone",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_STAIRS = registerBlock("cobbled_limestone_stairs",
            () -> new StairsBlock(COBBLED_LIMESTONE.get().getDefaultState(), AbstractBlock.Settings.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_SLAB = registerBlock("cobbled_limestone_slab",
            () -> new SlabBlock(AbstractBlock.Settings.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> COBBLED_LIMESTONE_WALL = registerBlock("cobbled_limestone_wall",
            () -> new WallBlock(AbstractBlock.Settings.copy(COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE = registerBlock("mossy_cobbled_limestone",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.MOSSY_COBBLESTONE)));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_STAIRS = registerBlock("mossy_cobbled_limestone_stairs",
            () -> new StairsBlock(MOSSY_COBBLED_LIMESTONE.get().getDefaultState(), AbstractBlock.Settings.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_SLAB = registerBlock("mossy_cobbled_limestone_slab",
            () -> new SlabBlock(AbstractBlock.Settings.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> MOSSY_COBBLED_LIMESTONE_WALL = registerBlock("mossy_cobbled_limestone_wall",
            () -> new WallBlock(AbstractBlock.Settings.copy(MOSSY_COBBLED_LIMESTONE.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs",
            () -> new StairsBlock(LIMESTONE_BRICKS.get().getDefaultState(), AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab",
            () -> new SlabBlock(AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall",
            () -> new WallBlock(AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICKS = registerBlock("mossy_limestone_bricks",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICKS)));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_STAIRS = registerBlock("mossy_limestone_brick_stairs",
            () -> new StairsBlock(LIMESTONE_BRICKS.get().getDefaultState(), AbstractBlock.Settings.copy(LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_SLAB = registerBlock("mossy_limestone_brick_slab",
            () -> new SlabBlock(AbstractBlock.Settings.copy(MOSSY_LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> MOSSY_LIMESTONE_BRICK_WALL = registerBlock("mossy_limestone_brick_wall",
            () -> new WallBlock(AbstractBlock.Settings.copy(MOSSY_LIMESTONE_BRICKS.get())));
    public static final RegistrySupplier<Block> CRACKED_LIMESTONE_BRICKS = registerBlock("cracked_limestone_bricks",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static final RegistrySupplier<Block> CHISELED_LIMESTONE_BRICKS = registerBlock("chiseled_limestone_bricks",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.CHISELED_STONE_BRICKS)));
    public static final RegistrySupplier<Block> POLISHED_LIMESTONE_BRICKS = registerBlock("polished_limestone_bricks",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
    public static final RegistrySupplier<Block> FURNACE_COBBLESTONE = registerBlock("furnace_cobblestone",
            () -> new CobblestoneFurnaceBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD).luminance((blockState) -> 13)));
    public static final RegistrySupplier<Block> STOVE = registerBlock("stove_tiles",
            () -> new MainStoveBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD)));
    public static final RegistrySupplier<Block> STOVE_WOOD = registerBlock("stove_tiles_wood",
            () -> new StoveBlockWood(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD).luminance(state -> state.get(StoveBlockWood.LIT) ? 13 : 0).ticksRandomly(), Direction.UP));
    public static final RegistrySupplier<Block> STOVE_LID = registerBlock("stove_tiles_lid",
            () -> new StoveBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD), Direction.UP));
    public static final RegistrySupplier<Block> STOVE_BENCH = registerBlock("stove_tiles_bench",
            () -> new TiledBench(AbstractBlock.Settings.copy(Blocks.ACACIA_WOOD)));
    public static final RegistrySupplier<Block> PINE_LOG = registerBlock("pine_log",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_WOOD = registerBlock("pine_wood",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_PINE_WOOD = registerBlock("stripped_pine_wood",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> STRIPPED_PINE_LOG = registerBlock("stripped_pine_log",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_BEAM = registerBlock("pine_beam",
            () -> new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)));
    public static final RegistrySupplier<Block> PINE_PLANKS = registerBlock("pine_planks",
            () -> new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_ORANGE).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3.0f)));
    public static final RegistrySupplier<Block> PINE_STAIRS = registerBlock("pine_stairs",
            () -> new StairsBlock(PINE_PLANKS.get().getDefaultState(), AbstractBlock.Settings.copy(Blocks.ACACIA_STAIRS)));
    public static final RegistrySupplier<Block> PINE_SLAB = registerBlock("pine_slab",
            () -> new SlabBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB)));
    public static final RegistrySupplier<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_BUTTON = registerBlock("pine_button",
            () -> new WoodenButtonBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_BUTTON)));
    public static final RegistrySupplier<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            () -> new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR)));
    public static final RegistrySupplier<Block> PINE_DOOR = registerBlock("pine_door",
            () -> new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> PINE_BARN_TRAPDOOR = registerBlock("pine_barn_trapdoor",
            () -> new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR)));
    public static final RegistrySupplier<Block> PINE_BARN_DOOR = registerBlock("pine_barn_door",
            () -> new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> PINE_FENCE = registerBlock("pine_fence",
            () -> new FenceBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_RAILING = registerBlock("pine_railing",
            () -> new FenceBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.get().getDefaultMapColor()).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> PINE_SAPLING = registerBlock("pine_sapling",
            () -> new SaplingBlock(new PineSaplingGenerator(),
                    AbstractBlock.Settings.copy(Blocks.SPRUCE_SAPLING)));
    public static final RegistrySupplier<Block> PINE_LEAVES = registerBlock("pine_leaves",
            () -> new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().blockVision((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)));
    public static final RegistrySupplier<Block> PINE_LEAVES_2 = registerBlockWithoutItem("pine_leaves_2",
            () -> new LeavesBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_LEAVES)));
    //public static final Identifier SIGN_TEXTURE_ID = new Identifier("entity/signs/pine");
    //public static final RegistrySupplier<Block> PINE_SIGN = registerBlockWithoutItem("pine_sign",
    //        () -> new TerraformSignBlock(SIGN_TEXTURE_ID, AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1.0f).sounds(BlockSoundGroup.WOOD)));
    //public static final RegistrySupplier<Block> PINE_WALL_SIGN = registerBlockWithoutItem("pine_wall_sign",
    //        () -> new TerraformWallSignBlock(SIGN_TEXTURE_ID, AbstractBlock.Settings.of(Material.WOOD).noCollision().strength(1.0f).sounds(BlockSoundGroup.WOOD).dropsLike(ObjectRegistry.PINE_SIGN)));
    public static final RegistrySupplier<Block> SHELF = registerBlock("shelf",
            () -> new ShelfBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD), ModSounds.SHELF_OPEN, ModSounds.SHELF_CLOSED));
    public static final RegistrySupplier<Block> CHEESE_RACK = registerBlock("cheese_rack", () -> new CheeseRackBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> TABLE = registerBlock("table",
            () -> new TableBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> CHAIR = registerBlock("chair",
            () -> new ChairBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> BENCH = registerBlock("bench",
            () -> new BenchBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> STONE_BENCH = registerBlock("stone_bench",
            () -> new BenchBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f, 0.0f).sounds(BlockSoundGroup.STONE)));
    public static final RegistrySupplier<Block> STONE_TABLE = registerBlock("stone_table",
            () -> new TableBlock(AbstractBlock.Settings.of(Material.STONE).strength(4f, 0.0f).sounds(BlockSoundGroup.STONE).nonOpaque()));
    public static final RegistrySupplier<Block> WINDOW = registerBlock("window",
            () -> new WindowBlock(AbstractBlock.Settings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final RegistrySupplier<Block> WINDOW_2 = registerBlock("window_2",
            () -> new WindowBlock2(AbstractBlock.Settings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final RegistrySupplier<Block> WINDOW_3 = registerBlock("window_3",
            () -> new WindowBlock3(AbstractBlock.Settings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK = registerBlock("shutter_block",
            () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_POPPY = registerBlock("shutter_block_poppy",
            () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)), null);
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_FIR = registerBlock("shutter_block_fir",
            () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)), null);
    public static final RegistrySupplier<Block> SHUTTER_BLOCK_BERRY = registerBlock("shutter_block_berry",
            () -> new ShutterBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.0F).sounds(BlockSoundGroup.WOOD)), null);
    public static final RegistrySupplier<Block> FLECKED_WOOL = registerBlock("flecked_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> FLECKED_CARPET = registerBlock("flecked_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> FLECKED_BED = registerBlock("flecked_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> HIGHLAND_WOOL = registerBlock("highland_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> HIGHLAND_CARPET = registerBlock("highland_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> HIGHLAND_BED = registerBlock("highland_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> PATCHED_WOOL = registerBlock("patched_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> PATCHED_CARPET = registerBlock("patched_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> PATCHED_BED = registerBlock("patched_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> ROCKY_SHEEP_WOOL = registerBlock("rocky_sheep_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> ROCKY_CARPET = registerBlock("rocky_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> WOOLY_BED = registerBlock("wooly_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> UMBRA_WOOL = registerBlock("umbra_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> UMBRA_CARPET = registerBlock("umbra_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> UMBRA_BED = registerBlock("umbra_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> INKY_WOOL = registerBlock("inky_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> INKY_CARPET = registerBlock("inky_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> INKY_BED = registerBlock("inky_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> WARPED_WOOL = registerBlock("warped_wool",
            () -> new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> WARPED_CARPET = registerBlock("warped_carpet",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> WARPED_BED = registerBlock("warped_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));

    public static final RegistrySupplier<Item> OAT = registerItem("oat", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Block> OAT_CROP = registerBlockWithoutItem("oat_crop",
            () -> new OatCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final RegistrySupplier<Item> OAT_SEEDS = registerItem("oat_seeds", () -> new AliasedBlockItem(ObjectRegistry.OAT_CROP.get(), getSettings()));
    public static final RegistrySupplier<Block> OAT_BALL = registerBlock("oat_ball",
            () -> new HayBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> OATBLOCK_RUG = registerBlock("oatblock_rug",
            () -> new CarpetBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> STRAW_BED = registerBlock("straw_bed", () -> new MeadowBedBlock(AbstractBlock.Settings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()));
    public static final RegistrySupplier<Block> OAT_BAG = registerBlock("oat_bag",
            () -> new HayBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Item> OAT_BREAD = registerItem("oat_bread",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(0.6f).build())));
    public static final RegistrySupplier<Block> CAMERA = registerBlock("camera",
            () -> new CameraBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
    public static final RegistrySupplier<Block> DOORMAT = registerBlock("doormat",
            () -> new DoormatBlock(AbstractBlock.Settings.copy(Blocks.BLACK_CARPET)));
    public static final RegistrySupplier<Block> FLOWER_POT_BIG = registerBlock("flower_pot_big", () -> new BigFlowerPotBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> WOODEN_FLOWER_POT = registerBlock("wooden_flower_pot", () -> new WoodenFlowerPotBlock(Blocks.AIR, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
    public static final RegistrySupplier<Block> WOODEN_FLOWER_BOX = registerBlock("flower_box", () -> new FlowerBoxBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> CHEESE_FORM = registerBlock("cheese_form",
            () -> new CheeseFormBlock(bowlSettings()));
    public static final RegistrySupplier<Block> WOODEN_CAULDRON = registerBlock("wooden_cauldron",
            () -> new WoodenCauldronBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.SPRUCE_BROWN).requiresTool().strength(2.0f).nonOpaque().sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> WOODCUTTER = registerBlock("woodcutter",
            () -> new WoodcutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> WHEELBARROW = registerBlock("wheelbarrow",
            () -> new WheelBarrowBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> WOODEN_WATER_CAULDRON = registerBlockWithoutItem("wooden_water_cauldron",
            () -> new LeveledCauldronBlock(AbstractBlock.Settings.copy(ObjectRegistry.WOODEN_CAULDRON.get()), LeveledCauldronBlock.RAIN_PREDICATE, WoodenCauldronBehavior.WATER_CAULDRON_BEHAVIOR));
    public static final RegistrySupplier<Block> WOODEN_POWDER_SNOW_CAULDRON = registerBlockWithoutItem("wooden_powder_snow_cauldron",
            () -> new PowderSnowCauldronBlock(AbstractBlock.Settings.copy(ObjectRegistry.WOODEN_CAULDRON.get()), LeveledCauldronBlock.SNOW_PREDICATE, WoodenCauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR));
    public static final RegistrySupplier<Block> FIRE_LOG = registerBlock("fire_log",
            () -> new FireLog(AbstractBlock.Settings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final RegistrySupplier<Block> FRAME = registerBlock("frame",
            () -> new FrameBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().luminance((blockState) -> 13).strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final RegistrySupplier<Block> COOKING_CAULDRON = registerBlock("cooking_cauldron",
            () -> new CookingCauldronBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.METAL).luminance(state -> state.get(CookingCauldronBlock.HANGING) ? 13 : 0)));
    public static final RegistrySupplier<Block> COOKING_POT = registerBlock("cooking_pot",
            () -> new CookingPotBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> FONDUE = registerBlock("fondue",
            () -> new FondueBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.METAL).luminance((blockState) -> 13)));
    public static final RegistrySupplier<Block> CAN = registerBlock("can",
            () -> new CanBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(0.8f).nonOpaque().sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> CLIMBING_ROPE = registerBlockWithoutItem("climbing_rope",
            () -> new ClimbingRopeBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).noCollision()));
    public static final RegistrySupplier<Block> CLIMBING_ROPE_TOPMOUNT = registerBlock("climbing_rope_topmount",
            () -> new ClimbingRopeTopmountBlock(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL).noCollision()));
    public static final RegistrySupplier<Block> OIL_LANTERN = registerBlock("oil_lantern",
            () -> new OilLantern(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN).luminance(state -> state.get(OilLantern.LUMINANCE)).nonOpaque().ticksRandomly()));
    public static final RegistrySupplier<Block> FIR_CONE = registerBlock("fir_cone",
            () -> new FirConeDecorationBlock(AbstractBlock.Settings.of(Material.LEAVES).noCollision()));//TODO .dropsLike(PINE_SIGN)

    public static final RegistrySupplier<Block> YARROW_CROP = registerBlockWithoutItem("yarrow_crop",
            () -> new YarrowCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final RegistrySupplier<Block> MOUNTAIN_LAVENDER_CROP = registerBlockWithoutItem("mountain_lavender_crop",
            () -> new MountainLavenderCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));
    public static final RegistrySupplier<Block> JUNIPER_CROP = registerBlockWithoutItem("juniper_crop",
            () -> new JuniperCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));

    public static final RegistrySupplier<Block> WILD_MOUNTAIN_LAVENDER = registerBlockWithoutItem("wild_mountain_lavender",
            () -> new FernBlock(AbstractBlock.Settings.copy(Blocks.GRASS)));
    public static final RegistrySupplier<Block> WILD_JUNIPER = registerBlockWithoutItem("wild_juniper",
            () -> new FernBlock(AbstractBlock.Settings.copy(Blocks.GRASS)));
    public static final RegistrySupplier<Block> WILD_YARROW = registerBlockWithoutItem("wild_yarrow",
            () -> new FernBlock(AbstractBlock.Settings.copy(Blocks.GRASS)));

    public static final RegistrySupplier<Block> DELPHINIUM = registerBlock("delphinium",
            () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));

    public static final RegistrySupplier<Block> SAXIFRAGE = registerBlock("saxifrage",
            () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ENZIAN = registerBlock("enzian",
            () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> FIRE_LILY = registerBlock("fire_lily",
            () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ERIOPHORUM = registerBlock("eriophorum",
            () -> new FlowerBlock(StatusEffects.INSTANT_HEALTH, 1, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final RegistrySupplier<Block> ERIOPHORUM_TALL = registerBlock("eriophorum_tall",
            () -> new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> SMALL_FIR = registerBlock("small_fir",
            () -> new TallPlantBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH)));
    public static final RegistrySupplier<Block> BOWL_CORNFLAKES = registerBlock("bowl_cornflakes",
            () -> new BowlBlock(bowlSettings()));
    public static final RegistrySupplier<Block> BOWL_EMPTY = registerBlockWithoutItem("bowl_empty",
            () -> new BowlBlock(bowlSettings()));
    public static final RegistrySupplier<Block> BOWL_HONEY = registerBlock("bowl_honey",
            () -> new BowlBlock(bowlSettings()));
    public static final RegistrySupplier<Block> BOWL_MILK = registerBlock("bowl_milk",
            () -> new BowlBlock(bowlSettings()));
    public static final RegistrySupplier<Block> BOWL_SWEETBERRIES = registerBlock("bowl_sweetberries",
            () -> new BowlBlock(bowlSettings()));
    public static final RegistrySupplier<Block> BOWL_EMPTY_NO_FORK = registerBlockWithoutItem("bowl_empty_no_fork",
            () -> new BowlBlock(bowlSettings()));
    public static final RegistrySupplier<Item> PIECE_OF_CHEESE = registerItem("piece_of_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> CHEESE_BLOCK = registerBlock("cheese_block",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_CHEESE, false));
    public static final RegistrySupplier<Item> PIECE_OF_SHEEP_CHEESE = registerItem("piece_of_sheep_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> SHEEP_CHEESE_BLOCK = registerBlock("sheep_cheese_block",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_SHEEP_CHEESE, false));
    public static final RegistrySupplier<Item> PIECE_OF_OAT_CHEESE = registerItem("piece_of_oat_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> OAT_CHEESE_BLOCK = registerBlock("oat_cheese_block",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_OAT_CHEESE, false));
    public static final RegistrySupplier<Item> PIECE_OF_LAVENDER_CHEESE = registerItem("piece_of_lavender_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> LAVENDER_CHEESE_BLOCK = registerBlock("lavender_cheese_block",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_LAVENDER_CHEESE, false));
    public static final RegistrySupplier<Item> PIECE_OF_HERB_CHEESE = registerItem("piece_of_herb_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> HERB_CHEESE_BLOCK = registerBlock("herb_cheese_block",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_HERB_CHEESE, false));
    public static final RegistrySupplier<Item> PIECE_OF_GOAT_CHEESE = registerItem("piece_of_goat_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> GOAT_CHEESE_BLOCK = registerBlock("goat_cheese_block",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_GOAT_CHEESE, false));
    public static final RegistrySupplier<Item> PIECE_OF_WARPED_CHEESE = registerItem("piece_of_warped_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 1), 1.0F).build())));
    public static final RegistrySupplier<Block> WARPED_CHEESE_BLOCK = registerBlock("warped_cheese_block",
            () -> new WarpedCheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.PIECE_OF_WARPED_CHEESE, false));
    public static final RegistrySupplier<Item> CHEESE_TART_SLICE = registerItem("cheese_tart_slice",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> CHEESE_TART = registerBlock("cheese_tart",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.CHEESE_TART_SLICE, true));
    public static final RegistrySupplier<Item> CHEESECAKE_SLICE = registerItem("cheesecake_slice",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Block> CHEESECAKE = registerBlock("cheesecake",
            () -> new CheeseBlock(AbstractBlock.Settings.copy(Blocks.CAKE), ObjectRegistry.CHEESECAKE_SLICE, true));
    public static final RegistrySupplier<Block> FIR = registerBlockWithoutItem("fir",
            () -> new Block(AbstractBlock.Settings.of(Material.LEAVES).breakInstantly()));
    public static final RegistrySupplier<Block> POTTED_ENZIAN = registerBlockWithoutItem("potted_enzian",
            () -> new FlowerPotBlock(ObjectRegistry.ENZIAN.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_FIRE_LILY = registerBlockWithoutItem("potted_fire_lily",
            () -> new FlowerPotBlock(ObjectRegistry.FIRE_LILY.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_ALPINE_POPPY = registerBlockWithoutItem("potted_alpine_poppy",
            () -> new FlowerPotBlock(ObjectRegistry.ALPINE_POPPY.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> W_POTTED_ALPINE_POPPY = registerBlockWithoutItem("w_potted_alpine_poppy", () -> new WoodenFlowerPotBlock(ALPINE_POPPY.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_SAXIFRAGE = registerBlockWithoutItem("potted_saxifrage",
            () -> new FlowerPotBlock(ObjectRegistry.SAXIFRAGE.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_DELPHINIUM = registerBlockWithoutItem("potted_delphinium",
            () -> new FlowerPotBlock(ObjectRegistry.DELPHINIUM.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_ERIOPHORUM = registerBlockWithoutItem("potted_eriophorum",
            () -> new FlowerPotBlock(ObjectRegistry.ERIOPHORUM.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> POTTED_PINE_SAPLING = registerBlockWithoutItem("potted_pine_sapling",
            () -> new FlowerPotBlock(ObjectRegistry.PINE_SAPLING.get(), AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)));
    public static final RegistrySupplier<Block> W_POTTED_PINE_SAPLING = registerBlockWithoutItem("w_potted_pine_sapling", () -> new WoodenFlowerPotBlock(PINE_SAPLING.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_OAK_SAPLING = registerBlockWithoutItem("wooden_potted_oak_sapling", () -> new WoodenFlowerPotBlock(Blocks.OAK_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_SPRUCE_SAPLING = registerBlockWithoutItem("potted_spruce_sapling", () -> new WoodenFlowerPotBlock(Blocks.SPRUCE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BIRCH_SAPLING = registerBlockWithoutItem("potted_birch_sapling", () -> new WoodenFlowerPotBlock(Blocks.BIRCH_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_JUNGLE_SAPLING = registerBlockWithoutItem("potted_jungle_sapling", () -> new WoodenFlowerPotBlock(Blocks.JUNGLE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ACACIA_SAPLING = registerBlockWithoutItem("potted_acacia_sapling", () -> new WoodenFlowerPotBlock(Blocks.ACACIA_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_DARK_OAK_SAPLING = registerBlockWithoutItem("potted_dark_oak_sapling", () -> new WoodenFlowerPotBlock(Blocks.DARK_OAK_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_MANGROVE_PROPAGULE = registerBlockWithoutItem("potted_mangrove_propagule", () -> new WoodenFlowerPotBlock(Blocks.MANGROVE_PROPAGULE, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_DANDELION = registerBlockWithoutItem("potted_dandelion", () -> new WoodenFlowerPotBlock(Blocks.DANDELION, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_POPPY = registerBlockWithoutItem("potted_poppy", () -> new WoodenFlowerPotBlock(Blocks.POPPY, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_BLUE_ORCHID = registerBlockWithoutItem("potted_blue_orchid", () -> new WoodenFlowerPotBlock(Blocks.BLUE_ORCHID, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ALLIUM = registerBlockWithoutItem("potted_allium", () -> new WoodenFlowerPotBlock(Blocks.ALLIUM, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_AZURE_BLUET = registerBlockWithoutItem("potted_azure_bluet", () -> new WoodenFlowerPotBlock(Blocks.AZURE_BLUET, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_RED_TULIP = registerBlockWithoutItem("potted_red_tulip", () -> new WoodenFlowerPotBlock(Blocks.RED_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_ORANGE_TULIP = registerBlockWithoutItem("potted_orange_tulip", () -> new WoodenFlowerPotBlock(Blocks.ORANGE_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WHITE_TULIP = registerBlockWithoutItem("potted_white_tulip", () -> new WoodenFlowerPotBlock(Blocks.WHITE_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_PINK_TULIP = registerBlockWithoutItem("potted_pink_tulip", () -> new WoodenFlowerPotBlock(Blocks.PINK_TULIP, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_OXEYE_DAISY = registerBlockWithoutItem("potted_oxeye_daisy", () -> new WoodenFlowerPotBlock(Blocks.OXEYE_DAISY, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_CORNFLOWER = registerBlockWithoutItem("potted_cornflower", () -> new WoodenFlowerPotBlock(Blocks.CORNFLOWER, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_LILY_OF_THE_VALLEY = registerBlockWithoutItem("potted_lily_of_the_valley", () -> new WoodenFlowerPotBlock(Blocks.LILY_OF_THE_VALLEY, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_WITHER_ROSE = registerBlockWithoutItem("potted_wither_rose", () -> new WoodenFlowerPotBlock(Blocks.WITHER_ROSE, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_AZALEA = registerBlockWithoutItem("potted_azalea", () -> new WoodenFlowerPotBlock(Blocks.AZALEA, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> POTTED_FLOWERING_AZALEA = registerBlockWithoutItem("potted_flowering_azalea", () -> new WoodenFlowerPotBlock(Blocks.FLOWERING_AZALEA, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));

    public static final RegistrySupplier<Block> W_POTTED_DELPHINIUM = registerBlockWithoutItem("w_potted_delphinium", () -> new WoodenFlowerPotBlock(DELPHINIUM.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_SAXIFRAGE = registerBlockWithoutItem("w_potted_saxifrage", () -> new WoodenFlowerPotBlock(SAXIFRAGE.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ENZIAN = registerBlockWithoutItem("w_potted_enzian", () -> new WoodenFlowerPotBlock(ENZIAN.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_FIRE_LILY = registerBlockWithoutItem("w_potted_fire_lily", () -> new WoodenFlowerPotBlock(FIRE_LILY.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Block> W_POTTED_ERIOPHORUM = registerBlockWithoutItem("w_potted_eriophorum", () -> new WoodenFlowerPotBlock(ERIOPHORUM.get(), AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final RegistrySupplier<Item> CREAM_CHEESE = registerItem("cream_cheese",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> BOWL_MOZRELLA = registerItem("bowl_mozzarella",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> CHEESE_SANDWICH = registerItem("cheese_sandwich",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> CHEESE_ROLL = registerItem("cheese_roll",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> CHEESE_STICK = registerItem("cheese_stick",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));

    public static final RegistrySupplier<Item> RAW_BEAR_MEAT = registerItem("raw_bear_meat",
            () -> new CraftingIngredientItem(getSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build())));
    public static final RegistrySupplier<Item> BEAR_STEW = registerItem("bear_stew",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).build())));
    public static final RegistrySupplier<Item> RICOLA = registerItem("ricola",
            () -> new Item(getSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));
    public static final RegistrySupplier<Item> JUG = registerItem("jug", () -> new Item(getSettings()));
    public static final RegistrySupplier<Item> JUG_MILK = registerItem("jug_milk",
            () -> new JugItem(getSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));
    public static final RegistrySupplier<Item> JUG_YARROW_TEA = registerItem("jug_yarrow_tea",
            () -> new JugItem(getSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));
    public static final RegistrySupplier<Item> JUG_JUNIPER_TEA = registerItem("jug_juniper_tea",
            () -> new JugItem(getSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));
    public static final RegistrySupplier<Item> LAB = registerItem("lab",  () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Block> WATERING_CAN = registerBlockWithoutItem("watering_can",
            () -> new WateringCanBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    //public static final RegistrySupplier<Item> WATERING_CAN_ITEM = registerItem("watering_can",
    //        () -> new WateringCanItem(ObjectRegistry.WATERING_CAN.get(), getSettings().maxDamage(25)));
    public static final RegistrySupplier<Item> MOUNTAIN_LAVENDER_SEEDS = registerItem("mountain_lavender_seeds", () -> new AliasedBlockItem(ObjectRegistry.MOUNTAIN_LAVENDER_CROP.get(), getSettings()));
    public static final RegistrySupplier<Item> BAG_OF_LAVENDER = registerItem("bag_of_lavender", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> JUNIPER_SEEDS = registerItem("juniper_seeds", () -> new AliasedBlockItem(ObjectRegistry.JUNIPER_CROP.get(), getSettings()));
    public static final RegistrySupplier<Item> BAG_OF_JUNIPER = registerItem("bag_of_juniper", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> YARROW_SEEDS = registerItem("yarrow_seeds", () -> new AliasedBlockItem(ObjectRegistry.YARROW_CROP.get(), getSettings()));
    public static final RegistrySupplier<Item> BAG_OF_YARROW = registerItem("bag_of_yarrow", () -> new CraftingIngredientItem(getSettings()));

    public static final RegistrySupplier<Item> CORD = registerItem("cord", () -> new CraftingIngredientItem(getSettings()));
    public static final RegistrySupplier<Item> BEAR_FUR = registerItem("bear_fur", () -> new CraftingIngredientItem(getSettings().group(ModItemGroup.MEADOW_TAB)));
    public static final RegistrySupplier<Item> BEAR_FUR_HELMET = registerItem("bear_fur_helmet", () -> new FurArmorItem(MeadowMaterials.BEAR_FUR_ARMOR, EquipmentSlot.HEAD, getSettings().group(ModItemGroup.MEADOW_TAB)));
    public static final RegistrySupplier<Item> BEAR_FUR_CHESTPLATE = registerItem("bear_fur_chestplate", () -> new FurArmorItem(MeadowMaterials.BEAR_FUR_ARMOR, EquipmentSlot.CHEST, getSettings().group(ModItemGroup.MEADOW_TAB)));
    public static final RegistrySupplier<Item> BEAR_FUR_LEGGINGS = registerItem("bear_fur_leggings", () -> new FurArmorItem(MeadowMaterials.BEAR_FUR_ARMOR, EquipmentSlot.LEGS, getSettings().group(ModItemGroup.MEADOW_TAB)));
    public static final RegistrySupplier<Item> BEAR_FUR_BOOTS = registerItem("bear_fur_boots", () -> new FurArmorItem(MeadowMaterials.BEAR_FUR_ARMOR, EquipmentSlot.FEET, getSettings().group(ModItemGroup.MEADOW_TAB)));
    public static final RegistrySupplier<Item> HUNTING_BOW = registerItem("hunting_bow", () -> new HuntingBowItem(BowUtil.HUNTING, getSettings()));
    public static final RegistrySupplier<Item> CHEESE_MASS = registerItem("cow_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> BUFFALO_CHEESE_MASS = registerItem("buffalo_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> GOAT_CHEESE_MASS = registerItem("goat_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> OAT_CHEESE_MASS = registerItem("oat_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> SHEEP_CHEESE_MASS = registerItem("sheep_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> LAVENDER_CHEESE_MASS = registerItem("lavender_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().group(ModItemGroup.MEADOW_TAB).maxCount(1)));

    public static final RegistrySupplier<Item> HERBS_CHEESE_MASS = registerItem("herbs_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WARPED_CHEESE_MASS = registerItem("warped_cheese_mass_bucket", () -> new WarpedMassItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_CHEESE_MASS = registerItem("wooden_cow_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_BUFFALO_CHEESE_MASS = registerItem("wooden_buffalo_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_GOAT_CHEESE_MASS = registerItem("wooden_goat_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_OAT_CHEESE_MASS = registerItem("wooden_oat_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_SHEEP_CHEESE_MASS = registerItem("wooden_sheep_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_LAVENDER_CHEESE_MASS = registerItem("wooden_lavender_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_HERBS_CHEESE_MASS = registerItem("wooden_herbs_cheese_mass_bucket", () -> new CraftingIngredientItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_WARPED_CHEESE_MASS = registerItem("wooden_warped_cheese_mass_bucket", () -> new WarpedMassItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> BUFFALO_MILK = registerItem("buffalo_milk_bucket", () -> new MilkBucketItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> GOAT_MILK = registerItem("goat_milk_bucket", () -> new MilkBucketItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> OAT_MILK = registerItem("oat_milk_bucket", () -> new MilkBucketItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> SHEEP_MILK = registerItem("sheep_milk_bucket", () -> new MilkBucketItem(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_BUCKET = registerItem("wooden_bucket", () -> new WoodenBucket(Fluids.EMPTY, getSettings().maxCount(16)));

    public static final RegistrySupplier<Item> WOODEN_WATER_BUCKET = registerItem("wooden_water_bucket", () -> new WoodenBucket(Fluids.WATER, getSettings().maxCount(1)));
    public static final RegistrySupplier<Item> WOODEN_POWDER_SNOW_BUCKET = registerItem("wooden_powder_snow_bucket", () -> new WoodenPowderSnowBucket(Blocks.POWDER_SNOW, SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW, getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_MILK_BUCKET = registerItem("wooden_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_BUFFALO_MILK = registerItem("wooden_buffalo_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_GOAT_MILK = registerItem("wooden_goat_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_OAT_MILK = registerItem("wooden_oat_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));

    public static final RegistrySupplier<Item> WOODEN_SHEEP_MILK = registerItem("wooden_sheep_milk_bucket", () -> new WoodenMilkBucket(getSettings().maxCount(1)));

    //TODO
    //public static final RegistrySupplier<Item> PINE_SIGN = registerItem("pine_sign",
    //        new SignItem(getSettings().maxCount(16).group(ModItemGroup.MEADOW_TAB), ObjectRegistry.PINE_SIGN, ObjectRegistry.PINE_WALL_SIGN));


    public static final RegistrySupplier<Item> ALBINO_COW_SPAWN_EGG_ITEM = registerItem( "albino_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.ALBINO_COW, 16777215, 16721408, getSettings()));

    public static final RegistrySupplier<Item> WATER_BUFFALO_SPAWN_EGG_ITEM = registerItem("water_buffalo_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.WATER_BUFFALO, 4473924, 8026746, getSettings()));

    public static final RegistrySupplier<Item> COOKIE_COW_SPAWN_EGG_ITEM = registerItem("cookie_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.COOKIE_COW, 16757823, 16777215, getSettings()));

    public static final RegistrySupplier<Item> CREAM_COW_SPAWN_EGG_ITEM = registerItem("cream_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.CREAM_COW, 16767095, 16777215, getSettings()));

    public static final RegistrySupplier<Item> DAIRY_COW_SPAWN_EGG_ITEM = registerItem("dairy_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.DAIRY_COW, 16777215, 0, getSettings()));

    public static final RegistrySupplier<Item> DARK_COW_SPAWN_EGG_ITEM = registerItem("dark_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.DARK_COW, 0, 2302755, getSettings()));

    public static final RegistrySupplier<Item> PINTO_COW_SPAWN_EGG_ITEM = registerItem("pinto_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.PINTO_COW, 16738816, 16777215, getSettings()));

    public static final RegistrySupplier<Item> SUNSET_COW_SPAWN_EGG_ITEM = registerItem("sunset_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.SUNSET_COW, 11868672,0, getSettings()));

    public static final RegistrySupplier<Item> UMBRA_COW_SPAWN_EGG_ITEM = registerItem("umbra_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.UMBRA_COW, 329011,1115451, getSettings()));

    public static final RegistrySupplier<Item> HIGHLAND_CATTLE_SPAWN_EGG_ITEM = registerItem("highland_cattle_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.HIGHLAND_CATTLE, 11102208, 16755200, getSettings()));

    public static final RegistrySupplier<Item> WARPED_COW_SPAWN_EGG_ITEM = registerItem("warped_cow_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.WARPED_COW,  43690, 11141290, getSettings()));

    public static final RegistrySupplier<Item> BROWN_BEAR_SPAWN_EGG_ITEM = registerItem("brown_bear_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.BROWN_BEAR, 5780480, 5586176, getSettings()));

    public static final RegistrySupplier<Item> CHICKEN1_SPAWN_EGG_ITEM = registerItem("chicken_1_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.CHICKEN1, 17066, 11353600, getSettings()));

    public static final RegistrySupplier<Item> CHICKEN2_SPAWN_EGG_ITEM = registerItem("chicken_2_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.CHICKEN2, 36020, 12893184, getSettings()));

    public static final RegistrySupplier<Item> CHICKEN3_SPAWN_EGG_ITEM = registerItem("chicken_3_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.CHICKEN3, 16775019, 12893184, getSettings()));

    public static final RegistrySupplier<Item> FLECKED_SHEEP_SPAWN_EGG_ITEM = registerItem("flecked_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.FLECKED_SHEEP, 11172162, 14079702, getSettings()));

    public static final RegistrySupplier<Item> FUZZY_SHEEP_SPAWN_EGG_ITEM = registerItem("fuzzy_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.FUZZY_SHEEP, 14079702, 6316128, getSettings()));

    public static final RegistrySupplier<Item> HORNED_SHEEP_SPAWN_EGG_ITEM = registerItem("horned_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.HORNED_SHEEP, 14079702,0, getSettings()));

    public static final RegistrySupplier<Item> INKY_SHEEP_SPAWN_EGG_ITEM = registerItem("inky_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.INKY_SHEEP, 6316128,0, getSettings()));

    public static final RegistrySupplier<Item> LONG_NOSED_SHEEP_SPAWN_EGG_ITEM = registerItem("long_nosed_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.LONG_NOSED_SHEEP, 5780480,2302755, getSettings()));

    public static final RegistrySupplier<Item> PATCHED_SHEEP_SPAWN_EGG_ITEM = registerItem("patched_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.PATCHED_SHEEP, 11184810,4473924, getSettings()));

    public static final RegistrySupplier<Item> ROCKY_SHEEP_SPAWN_EGG_ITEM = registerItem("rocky_sheep_spawn_egg",
            () -> new ArchitecturySpawnEggItem(ModEntities.ROCKY_SHEEP, 4473924,0, getSettings()));

    public static void init() {
        Meadow.LOGGER.debug("Registering Blocks and Items for " + Meadow.MOD_ID);
        ITEMS.register();
        BLOCKS.register();
    }

    public static void commonInit() {
        registerFuels();
    }

    private static AbstractBlock.Settings bowlSettings(){
        return AbstractBlock.Settings.of(Material.DECORATION).nonOpaque().strength(0.1f).sounds(BlockSoundGroup.SCAFFOLDING);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, ModItemGroup.MEADOW_TAB);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block, @Nullable ItemGroup tab) {
        return registerBlock(new MeadowIdentifier(name), block, tab);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlock(Identifier name, Supplier<T> block, @Nullable ItemGroup tab) {
        RegistrySupplier<T> toReturn = registerBlockWithoutItem(name, block);
        Item.Settings properties = new Item.Settings();
        if(tab != null) properties.group(tab);
        ObjectRegistry.registerItem(name, () -> new BlockItem(toReturn.get(), properties));
        return toReturn;
    }

    public static <T extends Block> RegistrySupplier<T> registerBlockWithoutItem(String path, Supplier<T> block) {
        return registerBlockWithoutItem(new MeadowIdentifier(path), block);
    }

    public static <T extends Block> RegistrySupplier<T> registerBlockWithoutItem(Identifier path, Supplier<T> block) {
        if (Platform.isForge()) {
            return BLOCKS.register(path.getPath(), block);
        }
        return BLOCKS_REGISTRAR.register(path, block);
    }

    private static void registerFuels() {
        Meadow.LOGGER.info("Registering Fuels for " + Meadow.MOD_ID);
        FuelRegistry.register(300, PINE_LOG.get(), PINE_WOOD.get(), STRIPPED_PINE_LOG.get(), STRIPPED_PINE_WOOD.get(), PINE_BEAM.get(), PINE_PLANKS.get(), PINE_STAIRS.get(), PINE_SLAB.get(), PINE_FENCE.get(), PINE_RAILING.get(),
                FLECKED_WOOL.get(), HIGHLAND_WOOL.get(), PATCHED_WOOL.get(), ROCKY_SHEEP_WOOL.get(), UMBRA_WOOL.get(), INKY_WOOL.get(), WARPED_WOOL.get(),
                ALPINE_OAK_LOG.get(), ALPINE_BIRCH_LOG.get(), PINE_LEAVES.get(), ALPINE_BIRCH_LEAVES_HANGING.get());
    }

    public static void registerArmor() {
        Registry.register(Registry.ITEM, new MeadowIdentifier("bear_fur_helmet"), BEAR_FUR_HELMET.get());
        Registry.register(Registry.ITEM, new MeadowIdentifier("bear_fur_chestplate"), BEAR_FUR_CHESTPLATE.get());
        Registry.register(Registry.ITEM, new MeadowIdentifier("bear_fur_leggings"), BEAR_FUR_LEGGINGS.get());
        Registry.register(Registry.ITEM, new MeadowIdentifier("bear_fur_boots"), BEAR_FUR_BOOTS.get());
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(String path, Supplier<T> itemSupplier) {
        return registerItem(new MeadowIdentifier(path), itemSupplier);
    }

    public static <T extends Item> RegistrySupplier<T> registerItem(Identifier path, Supplier<T> itemSupplier) {
        if (Platform.isForge()) {
            return ITEMS.register(path.getPath(), itemSupplier);
        }
        return ITEMS_REGISTRAR.register(path, itemSupplier);
    }

    private static Item.Settings getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Item.Settings getSettings(Consumer<Item.Settings> consumer) {
        Item.Settings settings = new Item.Settings().group(ModItemGroup.MEADOW_TAB);
        consumer.accept(settings);
        return settings;
    }
}
