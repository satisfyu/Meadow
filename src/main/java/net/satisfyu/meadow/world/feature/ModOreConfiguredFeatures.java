package net.satisfyu.meadow.world.feature;

import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.util.Tags;

import java.util.List;

import static net.minecraft.world.gen.feature.OreConfiguredFeatures.BASE_STONE_OVERWORLD;

public class ModOreConfiguredFeatures {
    public static final RuleTest LIMESTONE_ORE_REPLACEABLES = new TagMatchRuleTest(Tags.LIMESTONE_ORE_REPLACEABLES);

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LIMESTONE =
            ConfiguredFeatures.register("ore_limestone", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD,
                    ModBlocks.LIMESTONE.getDefaultState(), 64));



    public static final List<OreFeatureConfig.Target> SALT_ORE = List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.ALPINE_SALT_ORE.getDefaultState()));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SALT = ConfiguredFeatures.register("alpine_ore_salt", Feature.ORE, new OreFeatureConfig(SALT_ORE, 9));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SALT_SMALL = ConfiguredFeatures.register("alpine_ore_salt_small", Feature.ORE, new OreFeatureConfig(SALT_ORE, 4));

    /*
    public static final List<OreFeatureConfig.Target> IRON_ORE = List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.ALPINE_IRON_ORE.getDefaultState()));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_IRON = ConfiguredFeatures.register("alpine_ore_iron", Feature.ORE, new OreFeatureConfig(IRON_ORE, 9));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SALT_IRON = ConfiguredFeatures.register("alpine_ore_salt_small", Feature.ORE, new OreFeatureConfig(IRON_ORE, 4));


    public static final List<OreFeatureConfig.Target> COAL_ORE = List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.ALPINE_COAL_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COAL = ConfiguredFeatures.register("alpine_ore_coal", Feature.ORE, new OreFeatureConfig(COAL_ORE, 17));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COAL_BURIED = ConfiguredFeatures.register("alpine_ore_coal_buried", Feature.ORE, new OreFeatureConfig(COAL_ORE, 17, 0.5f));


    public static final List<OreFeatureConfig.Target> LAPIS_ORE = List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.Lapi));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LAPIS = ConfiguredFeatures.register("alpine_ore_lapis", Feature.ORE, new OreFeatureConfig(LAPIS_ORE, 7));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_LAPIS_BURIED = ConfiguredFeatures.register("alpine_ore_lapis_buried", Feature.ORE, new OreFeatureConfig(LAPIS_ORE, 7, 1.0f));

    public static final List<OreFeatureConfig.Target> COPPER_ORE = List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.COPPER_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COPPER_SMALL = ConfiguredFeatures.register("alpine_ore_copper_small", Feature.ORE, new OreFeatureConfig(COPPER_ORE, 10));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COPPER_LARGE = ConfiguredFeatures.register("alpine_ore_copper_large", Feature.ORE, new OreFeatureConfig(COPPER_ORE, 20));

    public static final List<OreFeatureConfig.Target> DIAMOND_ORE = List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.DIAMOND_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_SMALL = ConfiguredFeatures.register("ore_diamond_small", Feature.ORE, new OreFeatureConfig(DIAMOND_ORES, 4, 0.5f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_LARGE = ConfiguredFeatures.register("ore_diamond_large", Feature.ORE, new OreFeatureConfig(DIAMOND_ORES, 12, 0.7f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_DIAMOND_BURIED = ConfiguredFeatures.register("ore_diamond_buried", Feature.ORE, new OreFeatureConfig(DIAMOND_ORES, 8, 1.0f));

     */

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_EMERALD = ConfiguredFeatures.register("alpine_ore_emerald", Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(LIMESTONE_ORE_REPLACEABLES, ModBlocks.ALPINE_EMERALD_ORE.getDefaultState())), 3));


}
