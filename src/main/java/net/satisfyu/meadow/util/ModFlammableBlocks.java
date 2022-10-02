package net.satisfyu.meadow.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.satisfyu.meadow.block.ModBlocks;

public class ModFlammableBlocks {

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

        registry.add(ModBlocks.PINE_LOG, 5, 5);
        registry.add(ModBlocks.PINE_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_PINE_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_PINE_WOOD, 5, 5);

        registry.add(ModBlocks.PINE_PLANKS, 5, 20);
        registry.add(ModBlocks.PINE_LEAVES, 30, 60);
    }
}

