package net.satisfyu.meadow.entity.cow;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.satisfyu.meadow.MeadowExpectPlatform;
import net.satisfyu.meadow.registry.TagRegistry;
import net.satisfyu.meadow.world.CommonSpawnUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public enum CowVar implements StringRepresentable {
    DEFAULT(0, "cow"),
    ALBINO(1, "albino"),
    COOKIE(2, "cookie"),
    CREAM(3, "cream"),
    DAIRY(4, "dairy"),
    DARK(5, "dark"),
    PINTO(6, "pinto"),
    SUNSET(7, "sunset");
    public static final Codec<CowVar> CODEC = StringRepresentable.fromEnum(CowVar::values);
    private static final IntFunction<CowVar> BY_ID = ByIdMap.continuous(CowVar::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;

    CowVar(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static CowVar byId(int i) {
        return BY_ID.apply(i);
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }

    private static final Map<CowVar, TagKey<Biome>> SPAWNS = Util.make(new HashMap<>(), map -> {
        map.put(CowVar.DEFAULT, null);
        map.put(CowVar.COOKIE, TagRegistry.IS_MEADOW);
        map.put(CowVar.DAIRY, TagRegistry.IS_MEADOW);
        map.put(CowVar.CREAM, null);
        map.put(CowVar.PINTO, null);
        map.put(CowVar.DARK, TagRegistry.SPAWNS_DARK_COW);
        map.put(CowVar.ALBINO, TagRegistry.SPAWNS_BEAR);
        map.put(CowVar.SUNSET, TagRegistry.SPAWNS_SUNSET_COW);
    });

    public static void setVariant(Cow cow, CowVar variant) {
        setTypeVariant(cow,variant.getId() & 255 | getTypeVariant(cow) & -256);
    }
    public static CowVar getVariant(Cow cow) {
        return CowVar.byId(getTypeVariant(cow) & 255);
    }
    public static void setTypeVariant(Cow cow, int i) {
        MeadowExpectPlatform.setTypeVariant(cow, i);
    }
    public static int getTypeVariant(Cow cow) {
        return MeadowExpectPlatform.getTypeVariant(cow);
    }
    public static CowVar getRandomVariant(LevelAccessor levelAccessor, BlockPos blockPos, boolean spawnEgg) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPos);
        List<CowVar> possibleVars = getCowVariantsInBiome(holder);
        int size = possibleVars.size();
        RandomSource random = levelAccessor.getRandom();
        if(size == 0 || spawnEgg) return Util.getRandom(CowVar.values(), random);

        return possibleVars.get(random.nextInt(size));
    }
    private static List<CowVar> getCowVariantsInBiome(Holder<Biome> biome) {
        return SPAWNS.keySet().stream()
                .filter(cowVariant -> {
                    TagKey<Biome> biomeTag = SPAWNS.get(cowVariant);
                    if(biomeTag == null) return CommonSpawnUtil.spawnsInBiome(biome, true, EntityType.COW);

                    return biome.is(biomeTag);
                })
                .collect(Collectors.toList());
    }
}
