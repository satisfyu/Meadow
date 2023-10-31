package net.satisfyu.meadow.forge.mixin;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.forge.networking.MeadowNetworkForge;
import net.satisfyu.meadow.util.GeneralUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Inject(method = "handleAddEntity(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;getId()I"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            require = 0
    )
    private void bumblezone$syncHorseUUID1(
            ClientboundAddEntityPacket clientboundAddEntityPacket,
            CallbackInfo ci,
            EntityType<?> entitytype,
            Entity entity)
    {
        if (entity instanceof Sheep && entity.level().isClientSide()) {
            FriendlyByteBuf buf = GeneralUtil.create();
            buf.writeUUID(entity.getUUID());
            NetworkManager.sendToServer(MeadowNetworkForge.VAR_REQUEST_S2C, buf);
            Meadow.LOGGER.warn("Sending Request to server");
        }
    }
}