package net.satisfyu.meadow.entity.cow.shearable;

import com.mojang.serialization.Codec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public enum ShearableCowVar implements StringRepresentable {
    HIGHLAND(0, "highland_cattle"),
    UMBRA(1, "umbra_cow"),
    WARPED(2, "warped_cow");
    public static final Codec<ShearableCowVar> CODEC = StringRepresentable.fromEnum(ShearableCowVar::values);
    private static final IntFunction<ShearableCowVar> BY_ID = ByIdMap.continuous(ShearableCowVar::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;

    ShearableCowVar(int id, String name) {
        this.id = id;
        this.name = name;
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
}
