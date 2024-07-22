package net.satisfy.meadow.fabric.cca;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class VarHolder implements IntComponent, AutoSyncedComponent {

    private int var;
    private final Entity provider;

    public VarHolder(Entity provider) { this.provider = provider; }

    public void setVar(int var) {
        this.var = var;
        MyComponents.VAR.sync(this.provider);
    }

    @Override
    public int getId() {
        return var;
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        this.var = tag.getInt("Variant");
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider registryLookup) {
        tag.putInt("Variant", this.var);
    }
}
