package net.satisfy.meadow.neoforge.networking;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record VarRequestC2SPacket(UUID entityId) implements CustomPacketPayload {
    public static final ResourceLocation PACKET_RESOURCE_LOCATION = MeadowIdentifier.of("var_request");
    public static final CustomPacketPayload.Type<VarRequestC2SPacket> PACKET_ID = new CustomPacketPayload.Type<>(PACKET_RESOURCE_LOCATION);

    public static final StreamCodec<RegistryFriendlyByteBuf, VarRequestC2SPacket> PACKET_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, VarRequestC2SPacket::entityId,
            VarRequestC2SPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return PACKET_ID;
    }
}
