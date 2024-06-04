package net.satisfy.meadow.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.fabric.client.FurArmorHatRenderer;
import net.satisfy.meadow.fabric.villager.FabricVillager;
import net.satisfy.meadow.fabric.world.FabricEntitySpawn;
import net.satisfy.meadow.fabric.world.FeatureModification;
import net.satisfy.meadow.fabric.world.MeadowBiomeModification;
import net.satisfy.meadow.registry.CompostableRegistry;
import net.satisfy.meadow.registry.ObjectRegistry;


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

        ArmorRenderer.register(new FurArmorHatRenderer(), ObjectRegistry.FUR_HELMET.get());
    }
}

