package net.satisfyu.meadow.mixin.entity;

import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RabbitEntityRenderer.class)
public abstract class RabbitMixin{

    private static final Identifier MEADOW = new Identifier(Meadow.MOD_ID, "textures/entity/rabbit/meadow_rabbit.png");

    private static final Identifier MEADOW2 = new Identifier(Meadow.MOD_ID, "textures/entity/rabbit/meadow_rabbit2.png");

    @Inject(
            method = "getTexture(Lnet/minecraft/entity/passive/RabbitEntity;)Lnet/minecraft/util/Identifier;",
            at = @At(value = "HEAD"),
            cancellable = true)

    private void getTexture(RabbitEntity rabbitEntity, CallbackInfoReturnable<Identifier> cir) {
        String string = Formatting.strip(rabbitEntity.getName().getString());
        if (!(rabbitEntity.hasCustomName() && "Toast".equals(string))) {
            switch (rabbitEntity.getRabbitType()) {
                case 6 -> cir.setReturnValue(MEADOW);
                case 7 -> cir.setReturnValue(MEADOW2);
            }
        }
    }
}
