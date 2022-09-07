package net.satisfyu.meadow.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.custom.MeadowBedBlock;
import net.satisfyu.meadow.block.custom.OatCropBlock;
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


    private static Block registerBlockWithoutItem(String name, Block block) {

        return Registry.register(Registry.BLOCK, new Identifier(Meadow.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Meadow.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(Meadow.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Meadow.LOGGER.debug("Registering ModBlocks for " + Meadow.MOD_ID);
    }
}