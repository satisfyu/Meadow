package net.satisfyu.meadow.terrablender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.satisfyu.meadow.world.MeadowSurfaceRules;
import terrablender.api.*;

import java.util.List;
import java.util.function.Consumer;

import static net.satisfyu.meadow.Meadow.MOD_ID;


public class MeadowRegion extends Region {

    public MeadowRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }


    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        addModifiedVanillaOverworldBiomes(mapper, builder -> {


            List<MultiNoiseUtil.NoiseHypercube> meadowPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.NEUTRAL)
                    .humidity(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.WET)
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_1, ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.PEAK_NORMAL, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.UNDERGROUND)
                    .build();


            List<MultiNoiseUtil.NoiseHypercube> meadowForestPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F), MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F))
                    .humidity(MultiNoiseUtil.ParameterRange.of(-1.0F, -0.36F), MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F), MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F), MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F))
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_1, ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.PEAK_NORMAL, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .build();

            meadowForestPoints.forEach(point -> builder.replaceBiome(point, MeadowSurfaceRules.MEADOW_FOREST_KEY));

            List<MultiNoiseUtil.NoiseHypercube> meadowClearingPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(MultiNoiseUtil.ParameterRange.of(-0.45F,-0.15F), MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F))
                    .humidity(MultiNoiseUtil.ParameterRange.of(-1.0F, -0.35F), MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F), MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F), MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F))
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_1, ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .build();

            meadowClearingPoints.forEach(point -> builder.replaceBiome(point, MeadowSurfaceRules.MEADOW_CLEARING_KEY));
        });
    }

    public static void loadTerrablender(){
        Regions.register(new MeadowRegion(new Identifier(MOD_ID, "overworld"), 5));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, MeadowSurfaceRules.makeRules());
    }

}
