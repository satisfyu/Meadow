package net.satisfy.meadow.neoforge.capabilities;

import net.minecraft.world.entity.Entity;

public class Util {

    public static int getVarFromCap(Entity entity){
        if(entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY) != null){
            return entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY).getVariant();
        } else {
            return -1;
        }
    }
}
