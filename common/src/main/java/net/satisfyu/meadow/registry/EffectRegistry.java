package net.satisfyu.meadow.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.effects.CozyHomeEffect;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Supplier;


public class EffectRegistry {
    public static final DeferredRegister<MobEffect> STATUS_EFFECTS = DeferredRegister.create(Meadow.MOD_ID, Registries.MOB_EFFECT);
    public static final Registrar<MobEffect> STATUS_EFFECTS_REGISTRAR = STATUS_EFFECTS.getRegistrar();

    public static final RegistrySupplier<MobEffect> COZY_HOME = registerEffect("cozy_home", CozyHomeEffect::new);

    public static void init() {
        Meadow.LOGGER.debug("Mob effects");
        STATUS_EFFECTS.register();
    }

    private static RegistrySupplier<MobEffect> registerEffect(String name, Supplier<MobEffect> effect) {
        if (Platform.isForge()) {
            return STATUS_EFFECTS.register(name, effect);
        }
        return STATUS_EFFECTS_REGISTRAR.register(new MeadowIdentifier(name), effect);
    }
}
