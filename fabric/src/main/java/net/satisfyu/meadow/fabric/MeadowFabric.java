package net.satisfyu.meadow.fabric;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.satisfyu.meadow.Meadow;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.satisfyu.meadow.fabric.villager.FabricVillager;
import net.satisfyu.meadow.util.MeadowIdentifier;

import static net.satisfyu.meadow.Meadow.MOD_ID;

public class MeadowFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Meadow.init();
        Meadow.commonSetup();
        FabricVillager.init();
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("bushy_leaves"), container, ResourcePackActivationType.NORMAL);
        });
        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("optifine_support"), container, ResourcePackActivationType.NORMAL);
        });
    }
}
