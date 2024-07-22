package net.satisfy.meadow.forge.mixin;

import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.satisfy.meadow.entity.var.SheepVar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Sheep.class)
public class SheepMixin {
    @ModifyArg(
            method = "onSheared",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;)V"))
    public ItemLike getDrop(ItemLike itemLike) {
        if (itemLike.asItem().equals(Items.WHITE_WOOL)) {
            return meadow$safeGetWoolVariant();
        }
        return itemLike;
    }

    @Unique
    private ItemLike meadow$safeGetWoolVariant() {
        SheepVar variant = SheepVar.getVariant(meadow$getSheep());
        if (variant != null) {
            ItemLike wool = variant.getWool();
            if (wool != null) {
                return wool;
            }
        }
        return Items.WHITE_WOOL;
    }

    @Unique
    private Sheep meadow$getSheep() {
        return (Sheep) (Object)this;
    }
}
