package net.satisfyu.meadow.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.util.MeadowIdentifier;


public class MeadowEffects {



    public static final StatusEffect COZY_HOME = registerEffekt("cozy_home", new CozyHomeEffect());

    public static void init(){
        Meadow.LOGGER.debug("Mob effects");
    }

    private static StatusEffect registerEffekt(String name, StatusEffect effect){
        return Registry.register(Registry.STATUS_EFFECT, new MeadowIdentifier(name), effect);
    }
}
