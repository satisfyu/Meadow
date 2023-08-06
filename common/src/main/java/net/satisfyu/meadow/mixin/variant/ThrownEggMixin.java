package net.satisfyu.meadow.mixin.variant;

import net.minecraft.Util;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.satisfyu.meadow.entity.chicken.ChickenVar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ThrownEgg.class)
public class ThrownEggMixin {
    @ModifyVariable(
            method = "onHit",
            at = @At("STORE")
    )
    private Chicken mixin(Chicken chicken) {
        ChickenVar var = Util.getRandom(ChickenVar.values(), chicken.getRandom());
        ChickenVar.setVariant(chicken, var);
        return chicken;
    }

}