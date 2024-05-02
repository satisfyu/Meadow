package net.satisfyu.meadow.forge.mixin;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;
import net.satisfyu.meadow.forge.networking.MeadowNetworkForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Inject(method = "handleAddEntity(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;postAddEntitySoundInstance(Lnet/minecraft/world/entity/Entity;)V"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            require = 0
    )
    private void meadowSyncVar(ClientboundAddEntityPacket packet, CallbackInfo ci, EntityType<?> entityType, Entity entity, int i) {
        if (entity instanceof Sheep || entity instanceof Chicken || entity instanceof Cow) {
            if (entity.level().isClientSide()) {
                FriendlyByteBuf buf = GeneralUtil.create();
                buf.writeUUID(entity.getUUID());
                NetworkManager.sendToServer(MeadowNetworkForge.VAR_REQUEST_S2C, buf);
            }
        }
    }
}
