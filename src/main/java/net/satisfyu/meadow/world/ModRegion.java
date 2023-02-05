package net.satisfyu.meadow.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.List;
import java.util.function.Consumer;

import static net.satisfyu.meadow.Meadow.MOD_ID;
import static net.satisfyu.meadow.world.ModSurfaceRules.makeRules;

import static terrablender.api.ParameterUtils.*;
import static terrablender.api.ParameterUtils.Continentalness.*;
import static terrablender.api.ParameterUtils.Erosion.*;
import static terrablender.api.ParameterUtils.Humidity.*;
import static terrablender.api.ParameterUtils.Weirdness.*;


public class ModRegion extends Region {


    public ModRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        addModifiedVanillaOverworldBiomes(mapper, builder -> {

            List<MultiNoiseUtil.NoiseHypercube> meadowPoints = new ParameterUtils.ParameterPointListBuilder()

                    .temperature(Temperature.COOL, Temperature.NEUTRAL)
                    .humidity(ARID, DRY, NEUTRAL, WET)
                    .continentalness(FAR_INLAND)
                    .erosion(EROSION_1, EROSION_2, EROSION_3)
                    .weirdness(HIGH_SLICE_NORMAL_ASCENDING, PEAK_NORMAL, HIGH_SLICE_NORMAL_DESCENDING, MID_SLICE_NORMAL_DESCENDING, MID_SLICE_VARIANT_ASCENDING, HIGH_SLICE_VARIANT_ASCENDING, PEAK_VARIANT, HIGH_SLICE_VARIANT_DESCENDING, MID_SLICE_VARIANT_DESCENDING)

                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.UNDERGROUND)
                    .build();



            List<MultiNoiseUtil.NoiseHypercube> meadowForestPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(MultiNoiseUtil.ParameterRange.of(-0.45F, -0.15F), MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F))
                    .humidity(MultiNoiseUtil.ParameterRange.of(-1.0F, -0.36F), MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F), MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F), MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F))
                    .continentalness(FAR_INLAND)
                    .erosion(EROSION_1, EROSION_2, EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(HIGH_SLICE_NORMAL_ASCENDING, PEAK_NORMAL, HIGH_SLICE_NORMAL_DESCENDING, MID_SLICE_NORMAL_DESCENDING, MID_SLICE_VARIANT_ASCENDING, HIGH_SLICE_VARIANT_ASCENDING, PEAK_VARIANT, HIGH_SLICE_VARIANT_DESCENDING, MID_SLICE_VARIANT_DESCENDING)
                    .build();

            meadowForestPoints.forEach(point -> builder.replaceBiome(point, ModSurfaceRules.MEADOW_FOREST_KEY));

            List<MultiNoiseUtil.NoiseHypercube> meadowClearingPoints = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(MultiNoiseUtil.ParameterRange.of(-0.45F,-0.15F), MultiNoiseUtil.ParameterRange.of(-0.15F, 0.2F))
                    .humidity(MultiNoiseUtil.ParameterRange.of(-1.0F, -0.35F), MultiNoiseUtil.ParameterRange.of(-0.35F, -0.1F), MultiNoiseUtil.ParameterRange.of(-0.1F, 0.1F), MultiNoiseUtil.ParameterRange.of(0.1F, 0.3F))
                    .continentalness(FAR_INLAND)
                    .erosion(EROSION_1, EROSION_2, EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(HIGH_SLICE_NORMAL_ASCENDING, HIGH_SLICE_NORMAL_DESCENDING, MID_SLICE_NORMAL_DESCENDING, MID_SLICE_VARIANT_ASCENDING, HIGH_SLICE_VARIANT_ASCENDING, HIGH_SLICE_VARIANT_DESCENDING, MID_SLICE_VARIANT_DESCENDING)
                    .build();

            meadowClearingPoints.forEach(point -> builder.replaceBiome(point, ModSurfaceRules.MEADOW_CLEARING_KEY));
        });
    }

    public static void loadTerrablender(){
        Regions.register(new ModRegion(new Identifier(MOD_ID, "overworld"), 2));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, makeRules());
    }




}
