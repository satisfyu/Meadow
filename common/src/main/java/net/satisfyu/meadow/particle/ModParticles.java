package net.satisfyu.meadow.particle;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;

public class ModParticles {
    //TODO
    //public static final DefaultParticleType CHEESE_SPLASH = new MeadowDefaultParticle(true);

    private static void registerParticle(String id, DefaultParticleType particle) {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Meadow.MOD_ID, id), particle);
    }

    public static void registerParticles(){
        Meadow.LOGGER.debug("Registering Mod Particles for " + Meadow.MOD_ID);

        //registerParticle("cheese_splash", CHEESE_SPLASH);
    }

}
