package net.satisfy.meadow.neoforge;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.satisfy.meadow.Meadow;
import net.satisfy.meadow.neoforge.networking.MeadowNetworkForge;
import net.satisfy.meadow.neoforge.registry.MeadowForgeVillagers;
import net.satisfy.meadow.registry.CompostableRegistry;
import net.satisfy.meadow.registry.EntityRegistry;

@Mod(Meadow.MOD_ID)
public class MeadowForge {
    public MeadowForge(IEventBus modBus) {
        Meadow.init();
        MeadowNetworkForge.registerC2SPackets();
        MeadowForgeVillagers.register(modBus);
        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CompostableRegistry::registerCompostable);
        Meadow.commonSetup();
    }

    @SubscribeEvent
    public static void spawnEvent(SpawnPlacementRegisterEvent event){
        event.register(EntityRegistry.SHEARABLE_MEADOW_VAR_COW.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND
        );
        event.register(EntityRegistry.WATER_BUFFALO.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND
        );
    }
}
