package net.satisfy.meadow.fabric.cca;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

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

    @Override public void readFromNbt(CompoundTag tag) { this.var = tag.getInt("Variant"); }
    @Override public void writeToNbt(CompoundTag tag) { tag.putInt("Variant", this.var); }
}
