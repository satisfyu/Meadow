package net.satisfyu.meadow.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.forge.networking.MeadowNetworkForge;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.terrablender.MeadowRegion;

@Mod(Meadow.MOD_ID)
public class MeadowForge {
    public MeadowForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Meadow.MOD_ID, modEventBus);

        Meadow.init();
        MeadowNetworkForge.registerC2SPackets();

        modEventBus.addListener(this::commonSetup);
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(MeadowRegion::loadTerrablender);
        Meadow.commonSetup();
    }

    @SubscribeEvent
    public static void spawnEvent(SpawnPlacementRegisterEvent event){
        event.register(EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND
        );

        event.register(EntityRegistry.BROWN_BEAR.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.WATER_BUFFALO.get(),
                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND
                );
    }
}
