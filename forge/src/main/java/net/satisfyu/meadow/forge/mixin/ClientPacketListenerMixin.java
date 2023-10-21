package net.satisfyu.meadow.forge.mixin;

import dev.architectury.networking.NetworkManager;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;
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
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;postAddEntitySoundInstance(Lnet/minecraft/world/entity/Entity;)V"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            require = 0
    )
    private void meadowSyncVar(ClientboundAddEntityPacket clientboundAddEntityPacket, CallbackInfo ci, Entity entity) {
        Class<? extends Entity> eC = entity.getClass();
        if ((eC == Sheep.class || eC == Chicken.class || eC == Cow.class) && entity.level().isClientSide()) {
            FriendlyByteBuf buf = GeneralUtil.create();
            buf.writeUUID(entity.getUUID());
            NetworkManager.sendToServer(MeadowNetworkForge.VAR_REQUEST_S2C, buf);
        }
    }
}