package net.satisfy.meadow.entity.var;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.satisfy.meadow.MeadowExpectPlatform;
import net.satisfy.meadow.registry.ObjectRegistry;
import net.satisfy.meadow.registry.TagRegistry;
import net.satisfy.meadow.world.CommonSpawnUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public enum SheepVar implements StringRepresentable {
    DEFAULT(0, "sheep", Items.WHITE_WOOL),
    FLECKED(1, "flecked", ObjectRegistry.FLECKED_WOOL.get().asItem()),
    FUZZY(2, "fuzzy", ObjectRegistry.ROCKY_WOOL.get().asItem()),
    INKY(3, "inky", ObjectRegistry.INKY_WOOL.get().asItem()),
    LONG_NOSED(4, "long_nosed", ObjectRegistry.HIGHLAND_WOOL.get().asItem()),
    PATCHED(5, "patched", ObjectRegistry.PATCHED_WOOL.get().asItem()),
    ROCKY(6, "rocky", ObjectRegistry.ROCKY_WOOL.get().asItem()),
    HORNED(7, "horned", Items.WHITE_WOOL);

    public static final Codec<SheepVar> CODEC = StringRepresentable.fromEnum(SheepVar::values);
    private static final IntFunction<SheepVar> BY_ID = ByIdMap.continuous(SheepVar::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;
    private final Item wool;

    SheepVar(int id, String name, Item wool) {
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

    public static SheepVar byId(int i) {
        return BY_ID.apply(i);
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }

    private static final Map<SheepVar, TagKey<Biome>> SPAWNS = Util.make(new HashMap<>(), map -> {
        map.put(SheepVar.DEFAULT, null);
        map.put(SheepVar.FLECKED, TagRegistry.SPAWNS_SUNSET_COW);
        map.put(SheepVar.FUZZY, TagRegistry.IS_MEADOW);
        map.put(SheepVar.INKY, null);
        map.put(SheepVar.LONG_NOSED, TagRegistry.SPAWNS_ROCKY_SHEEP);
        map.put(SheepVar.PATCHED, null);
        map.put(SheepVar.ROCKY, TagRegistry.SPAWNS_ROCKY_SHEEP);
        map.put(SheepVar.HORNED, TagRegistry.SPAWNS_ROCKY_SHEEP);
    });

    public static void setVariant(Sheep sheep, SheepVar variant) {
        setTypeVariant(sheep,variant.getId() & 255 | getTypeVariant(sheep) & -256);
    }
    public static SheepVar getVariant(Sheep sheep) {
        return SheepVar.byId(getTypeVariant(sheep) & 255);
    }
    public static void setTypeVariant(Sheep sheep, int i) {
        MeadowExpectPlatform.setTypeVariant(sheep, i);
    }
    public static int getTypeVariant(Sheep sheep) {
        return MeadowExpectPlatform.getTypeVariant(sheep);
    }
    public static SheepVar getRandomVariant(LevelAccessor levelAccessor, BlockPos blockPos, boolean spawnEgg) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPos);
        List<SheepVar> possibleVars = getSheepVariantsInBiome(holder);
        int size = possibleVars.size();
        RandomSource random = levelAccessor.getRandom();
        if(size == 0 || spawnEgg) return Util.getRandom(SheepVar.values(), random);
        return possibleVars.get(random.nextInt(size));
    }
    private static List<SheepVar> getSheepVariantsInBiome(Holder<Biome> biome) {
        return SPAWNS.keySet().stream()
                .filter(sheepVar -> {
                    TagKey<Biome> biomeTag = SPAWNS.get(sheepVar);
                    if(biomeTag == null) return CommonSpawnUtil.spawnsInBiome(biome, true, EntityType.SHEEP);

                    return biome.is(biomeTag);
                })
                .collect(Collectors.toList());
    }
}