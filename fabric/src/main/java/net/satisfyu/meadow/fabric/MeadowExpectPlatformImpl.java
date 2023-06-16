package net.satisfyu.meadow.fabric;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;

public class MeadowExpectPlatformImpl {
    public static void addFlammable(int burnOdd, int igniteOdd, Block... blocks) {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        for(Block b : blocks) registry.add(b, burnOdd, igniteOdd);
    }
}