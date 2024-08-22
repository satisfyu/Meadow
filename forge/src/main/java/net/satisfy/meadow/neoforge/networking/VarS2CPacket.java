package net.satisfy.meadow.neoforge.networking;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record VarS2CPacket(UUID entityId, int var) implements CustomPacketPayload {
    public static final ResourceLocation PACKET_RESOURCE_LOCATION = MeadowIdentifier.of("var_s2c");
    public static final CustomPacketPayload.Type<VarS2CPacket> PACKET_ID = new CustomPacketPayload.Type<>(PACKET_RESOURCE_LOCATION);

    public static final StreamCodec<RegistryFriendlyByteBuf, VarS2CPacket> PACKET_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, VarS2CPacket::entityId,
            ByteBufCodecs.INT, VarS2CPacket::var,
            VarS2CPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return PACKET_ID;
    }
}

