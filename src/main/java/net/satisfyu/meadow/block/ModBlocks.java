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
import net.satisfyu.meadow.block.custom.multipleModelBlocks.Block2;
import net.satisfyu.meadow.block.custom.multipleModelBlocks.Flower2;
import net.satisfyu.meadow.block.custom.multipleModelBlocks.Flower3;
import net.satisfyu.meadow.block.custom.multipleModelBlocks.Flower4;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBehavior;
import net.satisfyu.meadow.block.woodenCauldren.WoodenCauldronBlock;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock;
import net.satisfyu.meadow.block.custom.*;
import net.satisfyu.meadow.block.woodCutter.WoodcutterBlock;
import net.satisfyu.meadow.item.ModItemGroup;

public class ModBlocks {
    public static final Block OAT_CROP = registerBlockWithoutItem("oat_crop",
            new OatCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block YARROW_CROP = registerBlockWithoutItem("yarrow_crop",
            new YarrowCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block ALPINE_GRASS = registerBlock("alpine_grass",
            new FernBlock(FabricBlockSettings.copyOf(Blocks.GRASS)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_GRASS_TALL = registerBlock("alpine_grass_tall",
            new TallPlantBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_FLOWER_1 = registerBlock("alpine_flower1",
            new Flower2(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_FLOWER_2 = registerBlock("alpine_flower2",
            new Flower4(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_FLOWER_3 = registerBlock("alpine_flower3",
            new Flower3(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_FLOWER_4 = registerBlock("alpine_flower4",
            new Flower3(StatusEffect.byRawId(6), 1, FabricBlockSettings.copyOf(Blocks.DANDELION)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_SALT_ORE = registerBlock("alpine_salt_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE = registerBlock("limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)), ModItemGroup.ALPINE_SALT);

    public static final Block COBBLED_LIMESTONE = registerBlock("cobbled_limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block MOSSY_COBBLED_LIMESTONE = registerBlock("mossy_cobbled_limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_COBBLESTONE)), ModItemGroup.ALPINE_SALT);

    public static final Block CRACKED_LIMESTONE_BRICKS = registerBlock("cracked_limestone_bricks",
            new Block2(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block LIMESTONE_BRICKS = registerBlock("limestone_bricks",
            new Block2(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

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

    public static final Block FLECKED_BED = registerBlock("flecked_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block HIGHLAND_BED = registerBlock("highland_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block PATCHED_BED = registerBlock("patched_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block UMBRA_BED = registerBlock("umbra_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block WOOLY_BED = registerBlock("wooly_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);


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

    public static final Block FURNACE_COBBLESTONE = registerBlock("furnace_cobblestone",
            new SmokerBlock(FabricBlockSettings.copyOf(Blocks.SMOKER)), ModItemGroup.ALPINE_SALT);

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
    
    public static final Block SHELF = registerBlock("shelf",
            new Block(FabricBlockSettings.copyOf(Blocks.BOOKSHELF)), ModItemGroup.ALPINE_SALT);

    public static final Block AXE_WOODSTACK = registerBlock("axe_woodstack",
            new WoodStackBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), false), ModItemGroup.ALPINE_SALT);

    public static final Block BIG_WOODSTACK = registerBlock("big_woodstack",
            new WoodStackBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD), true), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_0 = registerBlock("window_shutter_0",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_1 = registerBlock("window_shutter_1",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_2 = registerBlock("window_shutter_2",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block WINDOW_SHUTTER_3 = registerBlock("window_shutter_3",
            new WindowShutterBlock(AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()), ModItemGroup.ALPINE_SALT);

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