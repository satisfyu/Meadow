package net.satisfy.meadow.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.Entity;
import net.satisfy.meadow.fabric.cca.MyComponents;

public class MeadowExpectPlatformImpl {
    public static boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    public static int getTypeVariant(Entity entity) {
        return entity.getComponent(MyComponents.VAR).getId();
    }

    public static void setTypeVariant(Entity entity, int var) {
        entity.getComponent(MyComponents.VAR).setVar(var);
    }
}