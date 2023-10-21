package net.satisfyu.meadow.forge.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.forge.capabilities.MeadowCapabilities;
import net.satisfyu.meadow.forge.capabilities.Util;
import net.satisfyu.meadow.forge.capabilities.VarHolder;
import net.satisfyu.meadow.util.GeneralUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class VarRequestC2SPacket implements NetworkManager.NetworkReceiver {
    @Override
    public void receive(FriendlyByteBuf buffer, NetworkManager.PacketContext context) {
        try {
            UUID entityId = buffer.readUUID();

            ServerLevel lvl = (ServerLevel) Objects.requireNonNull(context.getPlayer().level());
            Entity entity = lvl.getEntity(entityId);

            //Sending back the packet
            if(entity != null){
                int var = Util.getVarFromCap(entity);

                FriendlyByteBuf buf = GeneralUtil.create();
                buf.writeInt(entity.getId());
                buf.writeInt(var);
                NetworkManager.sendToPlayer((ServerPlayer) context.getPlayer(), MeadowNetworkForge.VAR_S2C, buf);
                Meadow.LOGGER.warn("Wrote: " + entity.getId() + " and var: " + var + " to client");
            }



        } catch (Exception e) {
            Meadow.LOGGER.error("Error while reading entity id from network", e);
            throw e;
        }
    }
}