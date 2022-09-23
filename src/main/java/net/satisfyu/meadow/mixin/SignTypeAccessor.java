package net.satisfyu.meadow.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeAccessor {
    @Invoker
    static SignType callRegister(SignType type) {
        throw new UnsupportedOperationException();
    }

    @Invoker("<init>")
    static SignType callCreate(String name) {
        throw new Error("Mixin did not apply!");
    }
}
