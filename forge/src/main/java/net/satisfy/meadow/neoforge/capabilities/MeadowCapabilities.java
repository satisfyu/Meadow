package net.satisfy.meadow.neoforge.capabilities;

import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.satisfy.meadow.util.MeadowIdentifier;

public class MeadowCapabilities {
    public static final EntityCapability<VarHolder, Void> VAR_HOLDER_CAPABILITY =
            EntityCapability.createVoid(
                    MeadowIdentifier.of("var_holder"),
                    VarHolder.class);

    private MeadowCapabilities() {}

    public static void setupCapabilities(IEventBus modEventBus) {
        modEventBus.addListener(MeadowCapabilities::registerCaps);
    }

    // make sure the caps classes are registered, so they can be found
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                Capabilities.ItemHandler.ENTITY, // capability to register for
                EntityType.SHEEP, // entity type to register for
                (entity, context) -> new ItemStackHandler()
        );
    }
}
