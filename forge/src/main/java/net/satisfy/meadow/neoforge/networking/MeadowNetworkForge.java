package net.satisfy.meadow.neoforge.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.satisfy.meadow.neoforge.capabilities.MeadowCapabilities;
import net.satisfy.meadow.neoforge.capabilities.Util;
import net.satisfy.meadow.neoforge.capabilities.VarHolder;

import java.util.Optional;
import java.util.UUID;

public class MeadowNetworkForge {
    public static void registerC2SPackets() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, VarRequestC2SPacket.PACKET_ID, VarRequestC2SPacket.PACKET_CODEC, (payload, context) -> {
            UUID entityId = payload.entityId();
            ServerLevel lvl = (ServerLevel) context.getPlayer().level();
            Entity entity = lvl.getEntity(entityId);

            context.queue(() -> {
                if (entity != null) {
                    int var = Util.getVarFromCap(entity);
                    VarS2CPacket packet = new VarS2CPacket(entity.getUUID(), var);
                    NetworkManager.sendToPlayer((ServerPlayer) context.getPlayer(), packet);
                }
            });
        });
    }

    public static void registerS2CPackets() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, VarS2CPacket.PACKET_ID, VarS2CPacket.PACKET_CODEC, (payload, context) -> {
            UUID entityId = payload.entityId();
            int var = payload.var();

            Level lvl = Minecraft.getInstance().level;
            if (lvl != null) {
                Entity entity = lvl.getPlayerByUUID(entityId);
                if (entity != null) {
                    Optional<VarHolder> holder = Optional.ofNullable(entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY));
                    holder.ifPresent(h -> h.setVar(var));
                }
            }
        });
    }
}
