package net.satisfyu.meadow.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.fabric.client.MeadowFabricSpawnConfig;
import net.satisfyu.meadow.fabric.villager.FabricVillager;
import net.satisfyu.meadow.fabric.world.FabricEntitySpawn;
import net.satisfyu.meadow.fabric.world.FeatureModification;
import net.satisfyu.meadow.fabric.world.MeadowBiomeModification;
import net.satisfyu.meadow.util.MeadowIdentifier;

import static net.satisfyu.meadow.Meadow.MOD_ID;


public class MeadowFabric implements ModInitializer {

    public static ConfigHolder<MeadowFabricSpawnConfig> config;

    @Override
    public void onInitialize() {
        config = AutoConfig.register(MeadowFabricSpawnConfig.class, Toml4jConfigSerializer::new);


        Meadow.init();
        FeatureModification.init();
        Meadow.commonSetup();
        FabricVillager.init();
        MeadowBiomeModification.init();
        FabricEntitySpawn.registerEntitySpawn();

        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container -> {
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("bushy_leaves"), container, ResourcePackActivationType.NORMAL);
            ResourceManagerHelper.registerBuiltinResourcePack(new MeadowIdentifier("optifine_support"), container, ResourcePackActivationType.NORMAL);
        });
    }
}

