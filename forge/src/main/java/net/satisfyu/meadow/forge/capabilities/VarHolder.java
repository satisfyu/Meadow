package net.satisfyu.meadow.forge.capabilities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;
import java.util.stream.Collectors;

public class VarHolder implements INBTSerializable<CompoundTag> {

    private int var;

    public void setVar(int var) {
        this.var = var;
    }

    public int getId() {
        return var;
    }

    @Override
    public CompoundTag serializeNBT() {
        return CompoundTag.builder().putInt("Variant", this.var).build();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        this.var = tag.getInt("Variant");
    }

    public static Iterable<ServerPlayer> getRecipientsForComponentSync(Entity holder) {
        if (!holder.level().isClientSide && holder.level() instanceof ServerLevel level) {
            Deque<ServerPlayer> watchers = new ArrayDeque<>(tracking(level, holder.blockPosition()));
            if (holder instanceof ServerPlayer player && player.connection != null) {
                watchers.addFirst(player);
            }
            return watchers;
        }
        return List.of();
    }

    public static Collection<ServerPlayer> tracking(ServerLevel world, BlockPos pos) {
        Objects.requireNonNull(pos, "BlockPos cannot be null");

        return tracking(world, new ChunkPos(pos));
    }

    public static Collection<ServerPlayer> tracking(ServerLevel world, ChunkPos pos) {
        Objects.requireNonNull(world, "The world cannot be null");
        Objects.requireNonNull(pos, "The chunk pos cannot be null");

        return world.getChunkSource().chunkMap.getPlayers(pos, false);
    }
}
