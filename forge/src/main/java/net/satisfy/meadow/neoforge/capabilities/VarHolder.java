package net.satisfy.meadow.neoforge.capabilities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.*;

@SuppressWarnings("unused")
public class VarHolder implements INBTSerializable<CompoundTag> {
    private volatile int var;

    public void setVar(int var) {
        this.var = var;
    }

    public int getVariant() {
        return var;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Variant", this.var);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag tag) {
        if (tag.contains("Variant")) {
            this.var = tag.getInt("Variant");
        }
    }

    public static Iterable<ServerPlayer> getRecipientsForComponentSync(Entity holder) {
        if (!holder.level().isClientSide && holder.level() instanceof ServerLevel level) {
            Deque<ServerPlayer> watchers = new ArrayDeque<>(tracking(level, holder.blockPosition()));
            if (holder instanceof ServerPlayer player) {
                watchers.addFirst(player);
            }
            return watchers;
        }
        return Collections.emptyList();
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
