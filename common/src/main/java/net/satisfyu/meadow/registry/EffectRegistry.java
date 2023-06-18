package net.satisfyu.meadow.registry;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.effects.CozyHomeEffect;
import net.satisfyu.meadow.util.MeadowIdentifier;

import java.util.function.Supplier;


public class EffectRegistry {
    public static final DeferredRegister<StatusEffect> STATUS_EFFECTS = DeferredRegister.create(Meadow.MOD_ID, Registry.MOB_EFFECT_KEY);
    public static final Registrar<StatusEffect> STATUS_EFFECTS_REGISTRAR = STATUS_EFFECTS.getRegistrar();

    public static final RegistrySupplier<StatusEffect> COZY_HOME = registerEffect("cozy_home", CozyHomeEffect::new);

    public static void init() {
        Meadow.LOGGER.debug("Mob effects");
        STATUS_EFFECTS.register();
    }

    private static RegistrySupplier<StatusEffect> registerEffect(String name, Supplier<StatusEffect> effect) {
        if (Platform.isForge()) {
            return STATUS_EFFECTS.register(name, effect);
        }
        return STATUS_EFFECTS_REGISTRAR.register(new MeadowIdentifier(name), effect);
    }
}
