package net.satisfy.meadow.forge.capabilities;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class MeadowCapabilities {
    public static final Capability<VarHolder> VAR_HOLDER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private MeadowCapabilities() {}

    public static void setupCapabilities(IEventBus modEventBus) {
        modEventBus.addListener(MeadowCapabilities::registerCaps);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addGenericListener(Entity.class, VarAttacher::attach);
    }

    // make sure the caps classes are registered, so they can be found
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(VarHolder.class);
    }
}
