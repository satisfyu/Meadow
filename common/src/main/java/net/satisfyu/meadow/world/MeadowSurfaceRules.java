package net.satisfyu.meadow.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.satisfyu.meadow.registry.ObjectRegistry;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class MeadowSurfaceRules {

    private static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> SURFACE = register("surface");
    private static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> DIRT = register("dirt");
    private static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> CRACKS = register("cracks");
    
    private static final MaterialRules.MaterialRule LIMESTONE = makeStateRule(ObjectRegistry.LIMESTONE.get());
    private static final MaterialRules.MaterialRule COBBLED_LIMESTONE = makeStateRule(ObjectRegistry.COBBLED_LIMESTONE.get());
    private static final MaterialRules.MaterialRule PODZOL = makeStateRule(Blocks.PODZOL);
    private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final MaterialRules.MaterialRule DIRT_BLOCK = makeStateRule(Blocks.DIRT);
    
    public static final RegistryKey<Biome> MEADOW_CLEARING_KEY = createBiomeKey("meadow_clearing");
    public static final RegistryKey<Biome> MEADOW_FOREST_KEY = createBiomeKey("meadow_forest");

    private static RegistryKey<Biome> createBiomeKey(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, name));
    }

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule podzol = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, PODZOL), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, DIRT_BLOCK));

        MaterialRules.MaterialRule clearing = MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(CRACKS, -0.15, 0.15), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, Double.parseDouble("-1e+308"), 0.1), COARSE_DIRT), COBBLED_LIMESTONE)),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), MaterialRules.condition(MaterialRules.noiseThreshold(DIRT, 0.3, Double.parseDouble("1e+308")), COARSE_DIRT)),
        MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0.2, Double.parseDouble("1.7976931348623157e+308")), LIMESTONE),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), COARSE_DIRT));


        MaterialRules.MaterialRule forest = MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(CRACKS, -0.15, 0.15), MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, Double.parseDouble("-1e+308"), 0.1), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, COARSE_DIRT)), podzol)),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), MaterialRules.condition(MaterialRules.noiseThreshold(DIRT, 0.3, Double.parseDouble("1e+308")), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, COARSE_DIRT))),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0.2, Double.parseDouble("1.7976931348623157e+308")), podzol),
                MaterialRules.condition(MaterialRules.noiseThreshold(SURFACE, 0, Double.parseDouble("1.7976931348623157e+308")), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, COARSE_DIRT)));

        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(MEADOW_CLEARING_KEY), MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(MaterialRules.steepSlope(), LIMESTONE), clearing)))),
                MaterialRules.condition(MaterialRules.biome(MEADOW_FOREST_KEY), MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.sequence(MaterialRules.condition(MaterialRules.steepSlope(), LIMESTONE), forest))));
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
    
    private static RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> register(String name) {
        return RegistryKey.of(Registry.NOISE_KEY, new Identifier(MOD_ID, name));
    }
}