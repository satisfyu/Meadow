package net.satisfyu.meadow.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.satisfyu.meadow.Meadow;

public class ModParticles {

    public static final DefaultParticleType SPLASH = FabricParticleTypes.simple();

    public static final DefaultParticleType CHEESE_SPLASH = FabricParticleTypes.simple();

    private static void registerParticle(String id, DefaultParticleType particle) {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Meadow.MOD_ID, id), particle);
    }

    public static void registerParticles(){
        Meadow.LOGGER.debug("Registering Mod Particles for " + Meadow.MOD_ID);

        registerParticle("splash", SPLASH);
        registerParticle("cheese_splash", CHEESE_SPLASH);
    }

}
