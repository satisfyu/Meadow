package net.satisfy.meadow.forge.capabilities;

import net.minecraft.world.entity.Entity;

import java.util.Optional;
import java.util.function.Function;

public class Util {

    public static int getVarFromCap(Entity entity){
        return Optional.ofNullable(entity)
                .map(e -> e.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY).resolve())
                .flatMap(Function.identity())
                .map(VarHolder::getVariant)
                .orElse(-1);
    }
}
