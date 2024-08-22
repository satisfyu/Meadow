package net.satisfy.meadow.neoforge;

import net.minecraft.world.entity.Entity;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.satisfy.meadow.neoforge.capabilities.MeadowCapabilities;
import net.satisfy.meadow.neoforge.capabilities.VarHolder;

import javax.annotation.Nullable;
import java.util.Optional;

public class MeadowExpectPlatformImpl {
    public static boolean isModLoaded(String modid) {
        ModList modList = ModList.get();
        if(modList != null){
            return modList.isLoaded(modid);
        }
        return isModPreLoaded(modid);
    }

    public static boolean isModPreLoaded(String modid) {
        return getPreLoadedModInfo(modid) != null;
    }

    public static @Nullable ModInfo getPreLoadedModInfo(String modId){
        for(ModInfo info : LoadingModList.get().getMods()){
            if(info.getModId().equals(modId)) {
                return info;
            }
        }
        return null;
    }


    public static int getTypeVariant(Entity entity) {
        Optional<VarHolder> optional = Optional.ofNullable(entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY));
        return optional.map(VarHolder::getVariant).orElse(0);
    }

    public static void setTypeVariant(Entity entity, int var) {
        Optional<VarHolder> optional = Optional.ofNullable(entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY));
        optional.ifPresent(varHolder -> varHolder.setVar(var));
    }
}