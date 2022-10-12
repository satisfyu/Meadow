package net.satisfyu.meadow.mixin.biome;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomeCreator.class)
public interface OverworldDefaultFeaturesMixin {

    @Invoker
    static void callAddBasicFeatures(GenerationSettings.Builder generationSettings) {
    }
}