package net.satisfyu.meadow.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.registry.EntityRegistry;
import net.satisfyu.meadow.terrablender.MeadowRegion;

@Mod(Meadow.MOD_ID)
public class MeadowForge {
    public MeadowForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Meadow.MOD_ID, modEventBus);

        Meadow.init();

        modEventBus.addListener(this::commonSetup);
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork( () -> {
            MeadowRegion.loadTerrablender();
        });
        Meadow.commonSetup();
    }

    @SubscribeEvent
    public static void spawnEvent(SpawnPlacementRegisterEvent event){
        event.register(EntityRegistry.BROWN_BEAR.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.DAIRY_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.COOKIE_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.FUZZY_SHEEP.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.HIGHLAND_CATTLE.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.MEADOW_CHICKEN.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.FLECKED_SHEEP.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.SUNSET_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.ALBINO_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.CREAM_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.PINTO_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.WATER_BUFFALO.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.HORNED_SHEEP.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.ROCKY_SHEEP.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.DARK_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.UMBRA_COW.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );

        event.register(EntityRegistry.INKY_SHEEP.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
        event.register(EntityRegistry.PATCHED_SHEEP.get(),
                SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                AnimalEntity::isValidNaturalSpawn,
                SpawnPlacementRegisterEvent.Operation.AND
                );
    }
}
