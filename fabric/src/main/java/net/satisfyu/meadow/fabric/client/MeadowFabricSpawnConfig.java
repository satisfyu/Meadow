package net.satisfyu.meadow.fabric.client;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.satisfyu.meadow.Meadow;

@Config(name = Meadow.MOD_ID)
    public class MeadowFabricSpawnConfig implements ConfigData {
        @ConfigEntry.Gui.CollapsibleObject
        public Spawning spawning = new Spawning();

        @ConfigEntry.Gui.CollapsibleObject
        public Meadow meadow = new Meadow();

        public static class Spawning {
            @ConfigEntry.BoundedDiscrete(min = 0, max = Integer.MAX_VALUE)
            public int meadowSpawnWeight = 15;

            @ConfigEntry.BoundedDiscrete(min = 0, max = Integer.MAX_VALUE)
            public int meadowPackSizeMin = 6;

            @ConfigEntry.BoundedDiscrete(min = 0, max = Integer.MAX_VALUE)
            public int meadowPackSizeMax = 10;

            @ConfigEntry.BoundedDiscrete(min = 0, max = Integer.MAX_VALUE)
            public int bearSpawnWeight = 5;

            @ConfigEntry.BoundedDiscrete(min = 0, max = Integer.MAX_VALUE)
            public int bearPackSizeMin = 2;

            @ConfigEntry.BoundedDiscrete(min = 0, max = Integer.MAX_VALUE)
            public int bearPackSizeMax = 3;

        }
    }

