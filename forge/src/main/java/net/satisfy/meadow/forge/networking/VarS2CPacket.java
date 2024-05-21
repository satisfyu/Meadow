package net.satisfy.meadow.forge.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.satisfy.meadow.forge.capabilities.MeadowCapabilities;
import net.satisfy.meadow.forge.capabilities.VarHolder;

import java.util.Optional;

public class VarS2CPacket implements NetworkManager.NetworkReceiver {
    @Override
    public void receive(FriendlyByteBuf buffer, NetworkManager.PacketContext context) {
        int entityId = buffer.readInt();
        int var = buffer.readInt();

        Level lvl = Minecraft.getInstance().level;
        if (lvl != null) {
            Entity entity = lvl.getEntity(entityId);
            if (entity != null) {
                Optional<VarHolder> holder = entity.getCapability(MeadowCapabilities.VAR_HOLDER_CAPABILITY).resolve();
                holder.ifPresent(h -> h.setVar(var));
            }
        }
    }
}

