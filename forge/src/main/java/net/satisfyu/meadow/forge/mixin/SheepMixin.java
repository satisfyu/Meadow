package net.satisfyu.meadow.forge.mixin;

import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.satisfyu.meadow.entity.sheep.SheepVar;
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
        if(itemLike.asItem().equals(Items.WHITE_WOOL)){
            return SheepVar.getVariant(getSheep()).getWool();
        }
        return itemLike;
    }


    @Unique
    private Sheep getSheep() {
        return (Sheep) (Object)this;
    }
}