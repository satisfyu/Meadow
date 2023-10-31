package net.satisfyu.meadow.forge;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.forge.capabilities.MeadowCapabilities;
import net.satisfyu.meadow.forge.capabilities.VarHolder;
import net.satisfyu.meadow.forge.networking.MeadowNetworkForge;
import net.satisfyu.meadow.util.GeneralUtil;

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
        LazyOptional<VarHolder> optional = entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY);
        if(optional.isPresent()){
            Optional<VarHolder> holder = optional.resolve();
            if(holder.isPresent()) return holder.get().getId();
        }
        return 0;
    }

    public static void setTypeVariant(Entity entity, int var) {
        LazyOptional<VarHolder> optional = entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY);

        if(optional.isPresent()){
            Optional<VarHolder> holder = optional.resolve();
            holder.ifPresent(varHolder -> varHolder.setVar(var));
        }
    }
}