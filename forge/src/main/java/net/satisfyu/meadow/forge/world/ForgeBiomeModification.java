package net.satisfyu.meadow.forge.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.satisfyu.meadow.Meadow;

public class ForgeBiomeModification {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Meadow.MOD_ID);

    public static final RegistryObject<Codec<ForgeEntitySpawn>> ADD_BEAR_BIOMES = BIOME_MODIFIER_SERIALIZERS.register("add_bear", () -> RecordCodecBuilder.create((builder) -> builder.group(Biome.REGISTRY_ENTRY_LIST_CODEC.fieldOf("biomes").forGetter(ForgeEntitySpawn::biomes))
            .apply(builder, ForgeEntitySpawn::new)));
}
