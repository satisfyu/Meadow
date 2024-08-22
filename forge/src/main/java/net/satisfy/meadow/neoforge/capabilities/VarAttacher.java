package net.satisfy.meadow.neoforge.capabilities;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;


public final class VarAttacher {
    private static class VarCapabilityProvider implements INBTSerializable<CompoundTag> {
        private final VarHolder backend = new VarHolder();

        @Override
        public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
            return this.backend.serializeNBT(provider);
        }

        @Override
        public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag tag) {
            this.backend.deserializeNBT(provider, tag);
        }
    }
}
