package net.satisfyu.meadow.util;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Blocks;
import net.satisfyu.meadow.block.ModBlocks;

    public class ModStrippableBlocks {

        public static void registerStrippables() {
            StrippableBlockRegistry.register(ModBlocks.PINE_LOG, ModBlocks.STRIPPED_PINE_LOG);
            StrippableBlockRegistry.register(ModBlocks.PINE_WOOD, ModBlocks.STRIPPED_PINE_WOOD);
            StrippableBlockRegistry.register(ModBlocks.ALPINE_BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG);
            StrippableBlockRegistry.register(ModBlocks.ALPINE_OAK_LOG, Blocks.STRIPPED_OAK_LOG);
        }
    }