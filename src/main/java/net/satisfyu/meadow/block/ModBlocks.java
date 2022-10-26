package net.satisfyu.meadow.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.cheeseForm.CheeseFormBlock;
import net.satisfyu.meadow.block.custom.FacingBlock;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBehavior;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBlock;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock;
import net.satisfyu.meadow.block.custom.*;
import net.satisfyu.meadow.block.woodCutter.WoodcutterBlock;
import net.satisfyu.meadow.item.ModItemGroup;
import net.satisfyu.meadow.item.ModItems;
import net.satisfyu.meadow.world.feature.custom.tree.PineSaplingGenerator;

public class ModBlocks {
    public static final Block OAT_CROP = registerBlockWithoutItem("oat_crop",
            new OatCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block YARROW_CROP = registerBlockWithoutItem("yarrow_crop",
            new YarrowCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block MOUNTAIN_LAVENDER_CROP = registerBlockWithoutItem("mountain_lavender_crop",
            new MountainLavenderCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block JUNIPER_CROP = registerBlockWithoutItem("juniper_crop",
            new JuniperCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block ALPINE_GRASS = registerBlock("alpine_grass",
            new FernBlock(FabricBlockSettings.copyOf(Blocks.GRASS)), ModItemGroup.ALPINE_SALT);

    public static final Block WILD_MOUNTAIN_LAVENDER = registerBlockWithoutItem("wild_mountain_lavender",
            new FernBlock(FabricBlockSettings.copyOf(Blocks.GRASS)));

    public static final Block WILD_JUNIPER = registerBlockWithoutItem("wild_juniper",
            new FernBlock(FabricBlockSettings.copyOf(Blocks.GRASS)));

    public static final Block WILD_YARROW = registerBlockWithoutItem("wild_yarrow",
            new FernBlock(FabricBlockSettings.copyOf(Blocks.GRASS)));



    public static final Block ALPINE_GRASS_TALL = registerBlock("alpine_grass_tall",
            new TallPlantBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS)), ModItemGroup.ALPINE_SALT);

    public static final Block DELPHINIUM = registerBlock("delphinium",
            new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block POTTED_DELPHINIUM = registerBlockWithoutItem("potted_delphinium",
            new FlowerPotBlock(ModBlocks.DELPHINIUM, FabricBlockSettings.copyOf(Blocks.DANDELION)));

    public static final Block ALPINE_POPPY = registerBlock("alpine_poppy",
            new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block POTTED_ALPINE_POPPY = registerBlockWithoutItem("potted_alpine_poppy",
            new FlowerPotBlock(ModBlocks.ALPINE_POPPY, FabricBlockSettings.copyOf(Blocks.DANDELION)));
    public static final Block SAXIFRAGE = registerBlock("saxifrage",
            new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block POTTED_SAXIFRAGE = registerBlockWithoutItem("potted_saxifrage",
            new FlowerPotBlock(ModBlocks.SAXIFRAGE, FabricBlockSettings.copyOf(Blocks.DANDELION)));
    public static final Block ENZIAN = registerBlock("enzian",
            new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block POTTED_ENZIAN = registerBlockWithoutItem("potted_enzian",
            new FlowerPotBlock(ModBlocks.ENZIAN, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY)));

    public static final Block FIRE_LILY = registerBlock("fire_lily",
            new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block POTTED_FIRE_LILY = registerBlockWithoutItem("potted_fire_lily",
            new FlowerPotBlock(ModBlocks.ENZIAN, FabricBlockSettings.copyOf(Blocks.DANDELION)));

    public static final Block ERIOPHORUM = registerBlock("eriophorum",
            new FlowerBlock(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block POTTED_ERIOPHORUM = registerBlockWithoutItem("potted_eriophorum",
            new FlowerPotBlock(ModBlocks.ERIOPHORUM, FabricBlockSettings.copyOf(Blocks.DANDELION)));
    public static final Block ERIOPHORUM_TALL = registerBlock("eriophorum_tall",
            new TallPlantBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH)), ModItemGroup.ALPINE_SALT);

    public static final Block SMALL_FIR = registerBlock("small_fir",
            new TallPlantBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_SALT_ORE = registerBlock("alpine_salt_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_COAL_ORE = registerBlock("alpine_coal_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_LAPIS_ORE = registerBlock("alpine_lapis_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_GOLD_ORE = registerBlock("alpine_gold_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_EMERALD_ORE = registerBlock("alpine_emerald_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_IRON_ORE = registerBlock("alpine_iron_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE = registerBlock("limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)), ModItemGroup.ALPINE_SALT);

    public static final Block COBBLED_LIMESTONE = registerBlock("cobbled_limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_COBBLED_LIMESTONE = registerBlock("mossy_cobbled_limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_COBBLESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block CRACKED_LIMESTONE_BRICKS = registerBlock("cracked_limestone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_LIMESTONE_BRICKS = registerBlock("mossy_limestone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block CHISELD_LIMESTONE_BRICKS = registerBlock("chiseled_limestone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.CHISELED_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block FLECKED_WOOL = registerBurningBlock("flecked_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT, 30, 60);

    public static final Block HIGHLAND_WOOL = registerBurningBlock("highland_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT, 30, 60);

    public static final Block PATCHED_WOOL = registerBurningBlock("patched_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT, 30, 60);

    public static final Block ROCKY_SHEEP_WOOL = registerBurningBlock("rocky_sheep_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT, 30, 60);

    public static final Block UMBRA_WOOL = registerBurningBlock("umbra_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT, 30, 60);

    public static final Block INKY_WOOL = registerBurningBlock("inky_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT, 30, 60);

    public static final Block FLECKED_CARPET = registerBlock("flecked_carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block HIGHLAND_CARPET = registerBlock("highland_carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block PATCHED_CARPET = registerBlock("patched_carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block UMBRA_CARPET = registerBlock("umbra_carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block ROCKY_SHEEP_CARPET = registerBlock("rocky_sheep_carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block INKY_CARPET = registerBlock("inky_carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block FLECKED_BED = registerBlock("flecked_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block HIGHLAND_BED = registerBlock("highland_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block PATCHED_BED = registerBlock("patched_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block UMBRA_BED = registerBlock("umbra_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block WOOLY_BED = registerBlock("wooly_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block INKY_BED = registerBlock("inky_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block CAMERA = registerBlock("camera",
            new CameraBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_CORNFLAKES = registerBlock("bowl_cornflakes",
            new BowlBlock(bowlSettings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_EMPTY = registerBlockWithoutItem("bowl_empty",
            new BowlBlock(bowlSettings()));

    public static final Block BOWL_HONEY = registerBlock("bowl_honey",
            new BowlBlock(bowlSettings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_MILK = registerBlock("bowl_milk",
            new BowlBlock(bowlSettings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_MOZERELLA = registerBlock("bowl_mozzarella",
            new BowlBlock(bowlSettings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_SWEETBERRIES = registerBlock("bowl_sweetberries",
            new BowlBlock(bowlSettings()), ModItemGroup.ALPINE_SALT);

    public static final Block CAN = registerBlock("can",
            new CanBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(0.8f).nonOpaque().sounds(BlockSoundGroup.METAL)), ModItemGroup.ALPINE_SALT);

    public static final Block DOORMAT = registerBlock("doormat",
            new DoormatBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block HAYBLOCK_RUG = registerBlock("hayblock_rug",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block OATBLOCK_RUG = registerBlock("oatblock_rug",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block CHEESE_FORM = registerBlock("cheese_form",
            new CheeseFormBlock(bowlSettings()), ModItemGroup.ALPINE_SALT);

    public static final Block WOODCUTTER = registerBlock("woodcutter",
            new WoodcutterBlock(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block WOODEN_CAULDRON = registerBlock("wooden_cauldron",
            new WoodenCauldronBlock(FabricBlockSettings.of(Material.WOOD, MapColor.SPRUCE_BROWN).requiresTool().strength(2.0f).nonOpaque().sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block WOODEN_WATER_CAULDRON = registerBlockWithoutItem("wooden_water_cauldron",
            new LeveledCauldronBlock(FabricBlockSettings.copyOf(ModBlocks.WOODEN_CAULDRON), LeveledCauldronBlock.RAIN_PREDICATE, WoodenCauldronBehavior.WATER_CAULDRON_BEHAVIOR));

    public static final Block WOODEN_POWDER_SNOW_CAULDRON = registerBlockWithoutItem("wooden_powder_snow_cauldron",
            new PowderSnowCauldronBlock(FabricBlockSettings.copyOf(ModBlocks.WOODEN_CAULDRON), LeveledCauldronBlock.SNOW_PREDICATE, WoodenCauldronBehavior.POWDER_SNOW_CAULDRON_BEHAVIOR));

    public static final Block PINE_LOG = registerBurningBlock("pine_log",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), ModItemGroup.ALPINE_SALT,  5, 5);

    public static final Block PINE_WOOD = registerBurningBlock("pine_wood",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), ModItemGroup.ALPINE_SALT,  5, 5);

    public static final Block STRIPPED_PINE_WOOD = registerBurningBlock("stripped_pine_wood",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), ModItemGroup.ALPINE_SALT,  5, 5);

    public static final Block STRIPPED_PINE_LOG = registerBurningBlock("stripped_pine_log",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), ModItemGroup.ALPINE_SALT,  5, 5);

    public static final Block PINE_PLANKS = registerBurningBlock("pine_planks",
            new Block(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_ORANGE).sounds(BlockSoundGroup.WOOD).strength(2.0f, 3.0f)), ModItemGroup.ALPINE_SALT, 5, 20);

    public static final Block PINE_LEAVES = registerBurningBlock("pine_leaves",
            new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().blockVision((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)), ModItemGroup.ALPINE_SALT,  30, 60);

    public static final Block ALPINE_BIRCH_LEAVES_HANGING = registerBurningBlock("alpine_birch_leaves_hanging",
            new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().blockVision((state, world, pos) -> false)
                    .suffocates((state, world, pos) -> false)), ModItemGroup.ALPINE_SALT,  30, 60);

    public static final Block PINE_SAPLING = registerBlock("pine_sapling",
            new SaplingBlock(new PineSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_FENCE = registerBurningBlock("pine_fence",
            new FenceBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT, 5, 20);

    public static final Block CLIMBING_ROPE = registerBlockWithoutItem("climbing_rope",
            new ClimbingRopeBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).noCollision()));

    public static final Block CLIMBING_ROPE_TOPMOUNT = registerBlock("climbing_rope_topmount",
            new ClimbingRopeTopmountBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).noCollision()), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_DOOR = registerBlock("pine_door",
            new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_SIGN = registerBlockWithoutItem("pine_sign",
            new SignBlock(FabricBlockSettings.of(Material.WOOD).noCollision().strength(1.0f).sounds(BlockSoundGroup.WOOD), Meadow.PINE));

    public static final Block PINE_WALL_SIGN = registerBlockWithoutItem("pine_wall_sign",
            new WallSignBlock(FabricBlockSettings.of(Material.WOOD).noCollision().strength(1.0f).sounds(BlockSoundGroup.WOOD).dropsLike(ModBlocks.PINE_SIGN), Meadow.PINE));

    public static final Block OIL_LANTERN = registerBlock("oil_lantern",
            new OilLantern(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN).luminance(state -> state.get(OilLantern.LUMINANCE)).nonOpaque().ticksRandomly()), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.getDefaultMapColor()).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block OAT_BALL = registerBlock("oat_ball",
            new HayBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT);

    public static final Block OAT_BAG = registerBlock("oat_bag",
            new HayBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_BUTTON = registerBlock("pine_button",
            new WoodenButtonBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_BUTTON)), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_TRAPDOOR)), ModItemGroup.ALPINE_SALT);

    public static final Block PINE_SLAB = registerBurningBlock("pine_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_SLAB)), ModItemGroup.ALPINE_SALT, 5, 20);

    public static final Block PINE_STAIRS = registerBurningBlock("pine_stairs",
            new StairsBlock(PINE_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.ACACIA_STAIRS)), ModItemGroup.ALPINE_SALT, 5, 20);

    public static final Block PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.WOOD, PINE_PLANKS.getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block SHELF = registerBlock("shelf",
            new FacingBlock(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);
    public static final Block FURNACE_COBBLESTONE = registerBlock("furnace_cobblestone",
            new CobblestoneFurnaceBlock(FabricBlockSettings.copyOf(Blocks.SMOKER)), ModItemGroup.ALPINE_SALT);

    public static final Block STOVE = registerBlock("stove_tiles",
            new StoveBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD), true), ModItemGroup.ALPINE_SALT);

    public static final Block STOVE_WOOD = registerBlock("stove_tiles_wood",
            new StoveBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD), true), ModItemGroup.ALPINE_SALT);

    public static final Block STOVE_WOOD_ON = registerBlock("stove_tiles_wood_on",
            new CobblestoneFurnaceBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block STOVE_LID = registerBlock("stove_tiles_lid",
            new StoveBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD), true), ModItemGroup.ALPINE_SALT);

    public static final Block STOVE_BENCH = registerBlock("stove_tiles_bench",
            new StoveBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_WOOD), false), ModItemGroup.ALPINE_SALT);
    public static final Block COOKING_CAULDRON = registerBlock("cooking_cauldron",
            new CookingCauldronBlock(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW = registerBlock("window",
            new WindowBlock(FabricBlockSettings.of(Material.GLASS).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block FRAME = registerBlock("frame",
            new FrameBlock(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block TABLE = registerBlock("table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block CHAIR = registerBlock("chair",
            new ChairBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block BENCH = registerBlock("bench",
            new BenchBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)), ModItemGroup.ALPINE_SALT);

    public static final Block AXE_WOODSTACK = registerBlock("axe_woodstack",
            new WoodStackBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), 0), ModItemGroup.ALPINE_SALT);

    public static final Block MID_WOODSTACK = registerBlock("mid_woodstack",
            new WoodStackBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), 2), ModItemGroup.ALPINE_SALT);

    public static final Block SMALL_WOODSTACK = registerBlock("small_woodstack",
            new WoodStackBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), 1), ModItemGroup.ALPINE_SALT);

    public static final Block BIG_WOODSTACK = registerBlock("big_woodstack",
            new WoodStackBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), 2), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_0 = registerBlock("window_shutter_0",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_1 = registerBlock("window_shutter_1",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_2 = registerBlock("window_shutter_2",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_3 = registerBlock("window_shutter_3",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_BRICK_WALL = registerBlock("limestone_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(LIMESTONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block COBBLED_LIMESTONE_WALL = registerBlock("cobbled_limestone_wall",
            new WallBlock(FabricBlockSettings.copyOf(COBBLED_LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_COBBLED_LIMESTONE_WALL = registerBlock("mossy_cobbled_limestone_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_LIMESTONE_BRICK_WALL = registerBlock("mossy_limestone_brick_wall",
            new WallBlock(FabricBlockSettings.copyOf(MOSSY_LIMESTONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_WALL = registerBlock("limestone_wall",
            new WallBlock(FabricBlockSettings.copyOf(LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_STAIRS = registerBlock("limestone_stairs",
            new StairsBlock(LIMESTONE.getDefaultState(), FabricBlockSettings.copyOf(LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_BRICK_STAIRS = registerBlock("limestone_brick_stairs",
            new StairsBlock(LIMESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(LIMESTONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_LIMESTONE_BRICK_STAIRS = registerBlock("mossy_limestone_brick_stairs",
            new StairsBlock(MOSSY_LIMESTONE_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_LIMESTONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block COBBLED_LIMESTONE_STAIRS = registerBlock("cobbled_limestone_stairs",
            new StairsBlock(COBBLED_LIMESTONE.getDefaultState(), FabricBlockSettings.copyOf(COBBLED_LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_COBBLED_LIMESTONE_STAIRS = registerBlock("mossy_cobbled_limestone_stairs",
            new StairsBlock(MOSSY_COBBLED_LIMESTONE.getDefaultState(), FabricBlockSettings.copyOf(MOSSY_COBBLED_LIMESTONE)), ModItemGroup.ALPINE_SALT);


    public static final Block LIMESTONE_SLAB = registerBlock("limestone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_BRICK_SLAB = registerBlock("limestone_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(LIMESTONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_LIMESTONE_BRICK_SLAB = registerBlock("mossy_limestone_brick_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_LIMESTONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block COBBLED_LIMESTONE_SLAB = registerBlock("cobbled_limestone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(COBBLED_LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_COBBLED_LIMESTONE_SLAB = registerBlock("mossy_cobbled_limestone_slab",
            new SlabBlock(FabricBlockSettings.copyOf(MOSSY_COBBLED_LIMESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_OAK_LOG = registerBurningBlock("alpine_oak_log",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), ModItemGroup.ALPINE_SALT,  5, 5);

    public static final Block ALPINE_BIRCH_LOG = registerBurningBlock("alpine_birch_log",
            new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).strength(2.0f)), ModItemGroup.ALPINE_SALT,  5, 5);

    public static final Block SHEEP_CHEESE_BLOCK = registerBlock("sheep_cheese_block",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.PIECE_OF_SHEEP_CHEESE, false), ModItemGroup.ALPINE_SALT);

    public static final Block OAT_CHEESE_BLOCK = registerBlock("oat_cheese_block",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.PIECE_OF_OAT_CHEESE, false), ModItemGroup.ALPINE_SALT);

    public static final Block LAVENDER_CHEESE_BLOCK = registerBlock("lavender_cheese_block",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.PIECE_OF_LAVENDER_CHEESE, false), ModItemGroup.ALPINE_SALT);

    public static final Block HERB_CHEESE_BLOCK = registerBlock("herb_cheese_block",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.PIECE_OF_HERB_CHEESE, false), ModItemGroup.ALPINE_SALT);

    public static final Block GOAT_CHEESE_BLOCK = registerBlock("goat_cheese_block",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.PIECE_OF_GOAT_CHEESE, false), ModItemGroup.ALPINE_SALT);

    public static final Block CHEESE_TART = registerBlock("cheese_tart",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.CHEESE_TART_SLICE, true), ModItemGroup.ALPINE_SALT);

    public static final Block CHEESECAKE = registerBlock("cheesecake",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE), ModItems.CHEESECAKE_SLICE, true), ModItemGroup.ALPINE_SALT);



    public static final Block POTTED_PINE_SAPLING = registerBlockWithoutItem("potted_pine_sapling",
            new FlowerPotBlock(ModBlocks.PINE_SAPLING, FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque()));




    private static FabricBlockSettings bowlSettings(){
        return FabricBlockSettings.of(Material.DECORATION).nonOpaque().strength(0.1f).sounds(BlockSoundGroup.SCAFFOLDING);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Meadow.MOD_ID, name), block);
    }

    private static Block registerBurningBlock(String name, Block block, ItemGroup tab, int burn, int spread){
        Block block1 = registerBlock(name, block, tab);
        FlammableBlockRegistry.getDefaultInstance().add(block1, burn, spread);
        return block1;
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Meadow.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.ITEM, new Identifier(Meadow.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Meadow.LOGGER.debug("Registering ModBlocks for " + Meadow.MOD_ID);
    }
}