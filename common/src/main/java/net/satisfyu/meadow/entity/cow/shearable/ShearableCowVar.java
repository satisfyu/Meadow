package net.satisfyu.meadow.entity.cow.shearable;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public enum ShearableCowVar implements StringRepresentable {
    HIGHLAND(0, "highland_cattle", ObjectRegistry.HIGHLAND_WOOL.get().asItem()),
    UMBRA(1, "umbra_cow", ObjectRegistry.UMBRA_WOOL.get().asItem()),
    WARPED(2, "warped_cow", ObjectRegistry.WARPED_WOOL.get().asItem());
    public static final Codec<ShearableCowVar> CODEC = StringRepresentable.fromEnum(ShearableCowVar::values);
    private static final IntFunction<ShearableCowVar> BY_ID = ByIdMap.continuous(ShearableCowVar::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;
    private final Item wool;

    ShearableCowVar(int id, String name, Item wool) {
        this.id = id;
        this.name = name;
        this.wool = wool;
    }

    public Item getWool() {
        return wool;
    }

    public int getId() {
        return this.id;
    }

    public static ShearableCowVar byId(int i) {
        return BY_ID.apply(i);
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }


    private static final Map<ShearableCowVar, TagKey<Biome>> SPAWNS = Util.make(new HashMap<>(), map -> {
        map.put(ShearableCowVar.HIGHLAND, TagRegistry.IS_MEADOW);
        map.put(ShearableCowVar.UMBRA, TagRegistry.SPAWNS_DARK_COW);
        map.put(ShearableCowVar.WARPED, TagRegistry.SPAWNS_WARPED_COW);
    });
     public static ShearableCowVar getRandomVariant(LevelAccessor levelAccessor, BlockPos blockPos, boolean spawnEgg) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPos);
        RandomSource random = levelAccessor.getRandom();
        List<ShearableCowVar> possibleVars = getShearableCowVariantsInBiome(holder);
        int size = possibleVars.size();
        if(size == 0){
            if(!spawnEgg){
                if(holder.is(BiomeTags.IS_NETHER)) return ShearableCowVar.WARPED;
                List<ShearableCowVar> list = new java.util.ArrayList<>(List.of(ShearableCowVar.values()));
                list.remove(ShearableCowVar.WARPED);
                return Util.getRandom(list, random);
            }
            return Util.getRandom(ShearableCowVar.values(), random);
        }

        return possibleVars.get(levelAccessor.getRandom().nextInt(size));
    }

    private static List<ShearableCowVar> getShearableCowVariantsInBiome(Holder<Biome> biome) {
        return SPAWNS.keySet().stream()
                .filter(ShearableCowVariant -> biome.is(SPAWNS.get(ShearableCowVariant)))
                .collect(Collectors.toList());
    }
}
