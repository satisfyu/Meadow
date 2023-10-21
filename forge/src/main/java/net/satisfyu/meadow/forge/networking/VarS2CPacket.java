package net.satisfyu.meadow.forge.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.forge.capabilities.MeadowCapabilities;
import net.satisfyu.meadow.forge.capabilities.VarHolder;

import java.util.Objects;
import java.util.Optional;

public class VarS2CPacket implements NetworkManager.NetworkReceiver {
    @Override
    public void receive(FriendlyByteBuf buffer, NetworkManager.PacketContext context) {
        try {
            int entityId = buffer.readInt();
            int var = buffer.readInt();
            Meadow.LOGGER.warn("Read: " + entityId + " and var: " + var);

            Level lvl = Objects.requireNonNull(Minecraft.getInstance().level);
            Entity entity = lvl.getEntity(entityId);
            if(entity == null){
                Meadow.LOGGER.error("Entity is null");
                return;
            }

            Optional<VarHolder> holder = entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY).resolve();
            if(holder.isEmpty()){
                Meadow.LOGGER.error("Optional empty");
                return;
            }
            holder.get().setVar(var);

        } catch (Exception e) {
            Meadow.LOGGER.error("Error while reading entity var from network", e);
            throw e;
        }
    }
}