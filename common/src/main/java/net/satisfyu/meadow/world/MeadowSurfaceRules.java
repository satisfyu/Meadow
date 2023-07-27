package net.satisfyu.meadow.world;

import net.satisfyu.meadow.registry.ObjectRegistry;

import static net.satisfyu.meadow.Meadow.MOD_ID;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class MeadowSurfaceRules {

    private static final ResourceKey<NormalNoise.NoiseParameters> SURFACE = register("surface");
    private static final ResourceKey<NormalNoise.NoiseParameters> DIRT = register("dirt");
    private static final ResourceKey<NormalNoise.NoiseParameters> CRACKS = register("cracks");

    private static final SurfaceRules.RuleSource LIMESTONE = makeStateRule(ObjectRegistry.LIMESTONE.get());
    private static final SurfaceRules.RuleSource COBBLED_LIMESTONE = makeStateRule(ObjectRegistry.COBBLED_LIMESTONE.get());
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource DIRT_BLOCK = makeStateRule(Blocks.DIRT);

    public static final ResourceKey<Biome> MEADOW_CLEARING_KEY = createBiomeKey("meadow_clearing");
    public static final ResourceKey<Biome> MEADOW_FOREST_KEY = createBiomeKey("meadow_forest");

    private static ResourceKey<Biome> createBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(MOD_ID, name));
    }

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource podzol = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PODZOL), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT_BLOCK));

        SurfaceRules.RuleSource clearing = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(CRACKS, -0.15, 0.15), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, Double.parseDouble("-1e+308"), 0.1), COARSE_DIRT), COBBLED_LIMESTONE)),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(DIRT, 0.3, Double.parseDouble("1e+308")), COARSE_DIRT)),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0.2, Double.parseDouble("1.7976931348623157e+308")), LIMESTONE),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), COARSE_DIRT));


        SurfaceRules.RuleSource forest = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(CRACKS, -0.15, 0.15), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, Double.parseDouble("-1e+308"), 0.1), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, COARSE_DIRT)), podzol)),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(DIRT, 0.3, Double.parseDouble("1e+308")), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, COARSE_DIRT))),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0.2, Double.parseDouble("1.7976931348623157e+308")), podzol),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, COARSE_DIRT)));

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(MEADOW_CLEARING_KEY), SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.steep(), LIMESTONE), clearing)))),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(MEADOW_FOREST_KEY), SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.steep(), LIMESTONE), forest))));
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static ResourceKey<NormalNoise.NoiseParameters> register(String name) {
        return ResourceKey.create(Registries.NOISE, new ResourceLocation(MOD_ID, name));
    }
}