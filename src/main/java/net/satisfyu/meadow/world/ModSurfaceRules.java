package net.satisfyu.meadow.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.satisfyu.meadow.block.ModBlocks;

import static net.satisfyu.meadow.Meadow.MOD_ID;
import static net.satisfyu.meadow.world.ModRegion.MEADOW_CLEARING_KEY;
import static net.satisfyu.meadow.world.ModRegion.MEADOW_FOREST_KEY;

public class ModSurfaceRules {

    private static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> SURFACE = register("shield/surface");
    private static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> DIRT = register("taiga/dirt");
    private static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> CRACKS = register("taiga/cracks");



    private static final MaterialRules.MaterialRule LIMESTONE = makeStateRule(ModBlocks.LIMESTONE);
    private static final MaterialRules.MaterialRule COBBLED_LIMESTONE = makeStateRule(ModBlocks.COBBLED_LIMESTONE);
    private static final MaterialRules.MaterialRule PODZOL = makeStateRule(Blocks.PODZOL);
    private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);

    protected static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule clearing = MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(CRACKS, -0.15, 0.15), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, Double.parseDouble("-1e+308"), 0.1), COARSE_DIRT), COBBLED_LIMESTONE)),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), MaterialRules.condition(MaterialRules.noiseThreshold(DIRT, 0.3, Double.parseDouble("1e+308")), COARSE_DIRT)),
        MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0.2, Double.parseDouble("1.7976931348623157e+308")), LIMESTONE),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), COARSE_DIRT));

        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(MEADOW_CLEARING_KEY), MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(MaterialRules.steepSlope(), LIMESTONE), clearing))))
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }

                //MaterialRules.condition(MaterialRules.biome(MEADOW_CLEARING_KEY), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, clearing)),
                //MaterialRules.MaterialRule clearing = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, LIMESTONE), MaterialRules.condition(isAtOrAboveWaterLevel, COARSE_DIRT), MaterialRules.condition(isAtOrAboveWaterLevel, COBBLED_LIMESTONE));

    private static RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> register(String name) {
        return RegistryKey.of(Registry.NOISE_KEY, new Identifier(MOD_ID, name));
    }
}
