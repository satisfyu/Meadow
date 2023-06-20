package net.satisfyu.meadow.forge.world;

import com.mojang.serialization.Codec;

import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.satisfyu.meadow.forge.client.ForgeSpawnConfig;
import net.satisfyu.meadow.registry.EntityRegistry;

public record ForgeEntitySpawn(RegistryEntryList<Biome> biomes) implements BiomeModifier {
    @Override
    public void modify(RegistryEntry<Biome> biome, Phase phase, Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();

            SpawnSettings.SpawnEntry spawner = new SpawnSettings.SpawnEntry(EntityRegistry.BROWN_BEAR.get(),
                    ForgeSpawnConfig.bearSpawnWeight.get(),
                    ForgeSpawnConfig.bearPackSizeMin.get(),
                    ForgeSpawnConfig.bearPackSizeMax.get());
            EntityType<?> type = spawner.type;
            spawns.getSpawner(type.getSpawnGroup());
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return ForgeBiomeModification.ADD_BEAR_BIOMES.get();
    }
}