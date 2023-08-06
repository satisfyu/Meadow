package net.satisfyu.meadow.entity.chicken;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.satisfyu.meadow.registry.TagRegistry;
import net.satisfyu.meadow.world.CommonSpawnUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public enum ChickenVar implements StringRepresentable {
    DEFAULT(0, "chicken"),
    CHICKEN_1(1, "chicken_1"),
    CHICKEN_2(2, "chicken_2"),
    CHICKEN_3(3, "chicken_3");
    public static final Codec<ChickenVar> CODEC = StringRepresentable.fromEnum(ChickenVar::values);
    private static final IntFunction<ChickenVar> BY_ID = ByIdMap.continuous(ChickenVar::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;

    ChickenVar(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static ChickenVar byId(int i) {
        return BY_ID.apply(i);
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }


    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(Chicken.class, EntityDataSerializers.INT);

    private static final Map<ChickenVar, TagKey<Biome>> SPAWNS = Util.make(new HashMap<>(), map -> {
        map.put(ChickenVar.DEFAULT, null);
        map.put(ChickenVar.CHICKEN_1, TagRegistry.IS_MEADOW);
        map.put(ChickenVar.CHICKEN_2, TagRegistry.IS_MEADOW);
        map.put(ChickenVar.CHICKEN_3, TagRegistry.IS_MEADOW);
    });

    public static void setVariant(Chicken Chicken, ChickenVar variant) {
        setTypeVariant(Chicken,variant.getId() & 255 | getTypeVariant(Chicken) & -256);
    }
    public static ChickenVar getVariant(Chicken Chicken) {
        return ChickenVar.byId(getTypeVariant(Chicken) & 255);
    }
    public static void setTypeVariant(Chicken Chicken, int i) {
        Chicken.getEntityData().set(DATA_ID_TYPE_VARIANT, i);
    }
    public static int getTypeVariant(Chicken Chicken) {
        return Chicken.getEntityData().get(DATA_ID_TYPE_VARIANT);
    }
    public static ChickenVar getRandomVariant(LevelAccessor levelAccessor, BlockPos blockPos) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPos);
        List<ChickenVar> possibleVars = getChickenVariantsInBiome(holder);
        int size = possibleVars.size();
        if(size == 0) return ChickenVar.DEFAULT;

        return possibleVars.get(levelAccessor.getRandom().nextInt(size));
    }
    private static List<ChickenVar> getChickenVariantsInBiome(Holder<Biome> biome) {
        return SPAWNS.keySet().stream()
                .filter(ChickenVariant -> {
                    TagKey<Biome> biomeTag = SPAWNS.get(ChickenVariant);
                    if(biomeTag == null) return CommonSpawnUtil.spawnsInBiome(biome, true, EntityType.CHICKEN);

                    return biome.is(biomeTag);
                })
                .collect(Collectors.toList());
    }
}
