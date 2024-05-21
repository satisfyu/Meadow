package net.satisfy.meadow.registry;

import de.cristelknight.doapi.common.util.datafixer.DataFixers;
import de.cristelknight.doapi.common.util.datafixer.StringPairs;
import net.satisfy.meadow.Meadow;

public class DataFixerRegistry {

    public static void init(){
        StringPairs p = DataFixers.getOrCreate(Meadow.MOD_ID);
        p.add("window_1", "heart_patterned_window");
        p.add("window", "heart_patterned_window");
        p.add("window_2", "sun_patterned_window");
        p.add("window_3", "pine_window");
        p.add("flower_pot_big", "wooden_flower_pot_big");
        p.add("flower_box", "wooden_flower_box");
        p.add("wooden_flower_pot", "wooden_flower_pot_small");
        p.add("cooking_pot", "cooking_cauldron");
    }
}
