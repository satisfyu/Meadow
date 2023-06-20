package net.satisfyu.meadow.forge.client;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ForgeSpawnConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;

    public static final IntValue bearSpawnWeight;
    public static final IntValue bearPackSizeMin;
    public static final IntValue bearPackSizeMax;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Spawning configuration").push("spawning");

        bearSpawnWeight = builder
                .defineInRange("meadowSpawnWeight", 5, 0, Integer.MAX_VALUE);

        bearPackSizeMin = builder
                .defineInRange("meadowPackSizeMin", 2, 0, Integer.MAX_VALUE);

        bearPackSizeMax = builder
                .defineInRange("meadowPackSizeMax", 3, 0, Integer.MAX_VALUE);

        builder.pop();

        CONFIG_SPEC = builder.build();
    }
}
