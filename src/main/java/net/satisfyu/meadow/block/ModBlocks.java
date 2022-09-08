package net.satisfyu.meadow.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.custom.*;
import net.satisfyu.meadow.block.woodCutter.WoodcutterBlock;
import net.satisfyu.meadow.item.ModItemGroup;

public class ModBlocks {
    public static final Block ALPINE_SALT_ORE = registerBlock("alpine_salt_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool(),
                    UniformIntProvider.create(3, 7)), ModItemGroup.ALPINE_SALT);

    public static final Block OAT_CROP = registerBlockWithoutItem("oat_crop",
            new OatCropBlock(FabricBlockSettings.copy(Blocks.WHEAT)));

    public static final Block ALPINE_GRASS = registerBlock("alpine_grass",
            new FernBlock(FabricBlockSettings.copyOf(Blocks.GRASS)), ModItemGroup.ALPINE_SALT);

    public static final Block ALPINE_GRASS_TALL = registerBlock("alpine_grass_tall",
            new TallPlantBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS)), ModItemGroup.ALPINE_SALT);

    public static final Block FLECKED_BED = registerBlock("flecked_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block PATCHED_BED = registerBlock("patched_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block UMBRA_BED = registerBlock("umbra_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);
    public static final Block WOOLY_BED = registerBlock("wooly_bed", new MeadowBedBlock(FabricBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOD).strength(0.2F).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block CHISELD_LIMESTONE = registerBlock("chiseled_limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.CHISELED_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block COBBLED_LIMESTONE = registerBlock("cobbled_limestone",
            new Block(FabricBlockSettings.copyOf(Blocks.CHISELED_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block CRACKED_LIMESTONE = registerBlock("cracked_limestone_bricks",
            new Block2(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS)), ModItemGroup.ALPINE_SALT);

    public static final Block FLECKED_WOOL = registerBlock("flecked_wool",
            new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT);

    public static final Block CAMERA = registerBlock("camera",
            new CameraBlock(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL)), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_CORNFLAKES = registerBlock("bowl_cornflakes",
            new BowlBlock(settings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_EMPTY = registerBlockWithoutItem("bowl_empty",
            new BowlBlock(settings()));

    public static final Block BOWL_HONEY = registerBlock("bowl_honey",
            new BowlBlock(settings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_MILK = registerBlock("bowl_milk",
            new BowlBlock(settings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_MOZERELLA = registerBlock("bowl_mozzarella",
            new BowlBlock(settings()), ModItemGroup.ALPINE_SALT);

    public static final Block BOWL_SWEETBERRIES = registerBlock("bowl_sweetberries",
            new BowlBlock(settings()), ModItemGroup.ALPINE_SALT);

    public static final Block CAN = registerBlock("can",
            new CanBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(0.8f).nonOpaque()), ModItemGroup.ALPINE_SALT);

    public static final Block CARPET = registerBlock("carpet",
            new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET)), ModItemGroup.ALPINE_SALT);

    public static final Block CHEESE_FORM = registerBlock("cheese_form",
            new Block(settings()), ModItemGroup.ALPINE_SALT);

    public static final Block WOODCUTTER = registerBlock("woodcutter",
            new WoodcutterBlock(FabricBlockSettings.of(Material.WOOD).requiresTool().strength(3.5F)), ModItemGroup.ALPINE_SALT);


    private static FabricBlockSettings settings(){
        return FabricBlockSettings.of(Material.DECORATION).nonOpaque().strength(0.1f).sounds(BlockSoundGroup.SCAFFOLDING);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {

        return Registry.register(Registry.BLOCK, new Identifier(Meadow.MOD_ID, name), block);
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