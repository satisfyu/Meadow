package net.satisfyu.meadow.entity.cow;

import com.mojang.serialization.Codec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public enum CowVar implements StringRepresentable {
    ALBINO(0, "albino"),
    COOKIE(1, "cookie"),
    CREAM(2, "cream"),
    DAIRY(3, "dairy"),
    DARK(4, "dark"),
    PINTO(5, "pinto"),
    SUNSET(6, "sunset");
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
}
