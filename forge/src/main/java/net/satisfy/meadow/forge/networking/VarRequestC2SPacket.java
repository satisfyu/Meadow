package net.satisfy.meadow.forge.networking;

import de.cristelknight.doapi.common.util.GeneralUtil;
import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.satisfy.meadow.forge.capabilities.Util;

import java.util.UUID;

public class VarRequestC2SPacket implements NetworkManager.NetworkReceiver {

    @Override
    public void receive(FriendlyByteBuf buffer, NetworkManager.PacketContext context) {
        UUID entityId = buffer.readUUID();
        ServerLevel lvl = (ServerLevel) context.getPlayer().level();
        Entity entity = lvl.getEntity(entityId);

        if (entity != null) {
            int var = Util.getVarFromCap(entity);
            FriendlyByteBuf buf = GeneralUtil.create();
            buf.writeInt(entity.getId());
            buf.writeInt(var);
            NetworkManager.sendToPlayer((ServerPlayer) context.getPlayer(), MeadowNetworkForge.VAR_S2C, buf);
        }
    }
}
