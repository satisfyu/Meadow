package net.satisfyu.meadow.fabric;

import net.fabricmc.api.ModInitializer;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.fabric.villager.FabricVillager;
import net.satisfyu.meadow.fabric.world.FabricEntitySpawn;
import net.satisfyu.meadow.fabric.world.FeatureModification;
import net.satisfyu.meadow.fabric.world.MeadowBiomeModification;
import net.satisfyu.meadow.registry.CompostableRegistry;


public class MeadowFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Meadow.init();
        CompostableRegistry.registerCompostable();
        FeatureModification.init();
        Meadow.commonSetup();
        FabricVillager.init();
        MeadowBiomeModification.init();
        FabricEntitySpawn.registerEntitySpawn();
    }
}

