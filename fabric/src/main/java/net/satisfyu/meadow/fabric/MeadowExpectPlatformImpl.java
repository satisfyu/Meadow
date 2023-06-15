package net.satisfyu.meadow.fabric;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.satisfyu.meadow.ExampleExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class MeadowExpectPlatformImpl {
    public static void addFlammable(int burnOdd, int igniteOdd, Block... blocks) {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        for(Block b : blocks) registry.add(b, burnOdd, igniteOdd);
    }
}