package net.satisfyu.meadow.fabric;

import net.satisfyu.meadow.Meadow;
import net.fabricmc.api.ModInitializer;
import net.satisfyu.meadow.fabric.villager.FabricVillager;

public class MeadowFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Meadow.init();
        Meadow.commonSetup();

        FabricVillager.init();
    }
}
