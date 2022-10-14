package net.satisfyu.meadow.world.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.world.feature.custom.CobbledLimestoneRock;

public class ModFeatures {


    public static void registerFeatures(){
        Meadow.LOGGER.debug("Registering the ModFeatures for " + Meadow.MOD_ID);

        register("cobbled_limestone_rock", new CobbledLimestoneRock());
    }

    private static void register(String id, Feature<?> t){
        Registry.register(Registry.FEATURE, new Identifier(Meadow.MOD_ID, id), t);
    }
}
