package net.satisfyu.meadow.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.fabric.villager.FabricVillager;
import net.satisfyu.meadow.fabric.world.FabricEntitySpawn;
import net.satisfyu.meadow.fabric.world.FeatureModification;
import net.satisfyu.meadow.fabric.world.MeadowBiomeModification;
import net.satisfyu.meadow.util.MeadowIdentifier;



public class MeadowFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Meadow.init();
        FeatureModification.init();
        Meadow.commonSetup();
        FabricVillager.init();
        MeadowBiomeModification.init();
        FabricEntitySpawn.registerEntitySpawn();

        FabricLoader.getInstance().getModContainer(Meadow.MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("bushy_leaves"), container, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("green_stove"), container, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("optifine_support"), container, ResourcePackActivationType.NORMAL);
        });
    }
}

