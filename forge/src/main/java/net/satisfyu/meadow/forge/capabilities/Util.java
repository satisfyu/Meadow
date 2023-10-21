package net.satisfyu.meadow.forge.capabilities;

import net.minecraft.world.entity.Entity;

import java.util.Optional;

public class Util {

    public static int getVarFromCap(Entity entity){
        if(entity == null){
            throw new RuntimeException("Entity is null");
        }

        Optional<VarHolder> holder = entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY).resolve();
        if(holder.isEmpty()){
            throw new RuntimeException("Optional empty");
        }
        return holder.get().getId();
    }
}
