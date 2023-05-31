package net.satisfyu.meadow.entity.custom.cow.shearable.highland_cattle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.entity.custom.cow.shearable.ShearableCowEntity;
import net.satisfyu.meadow.registry.ObjectRegistry;


public class HighlandCattleEntity extends ShearableCowEntity {
    public HighlandCattleEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world, ObjectRegistry.HIGHLAND_WOOL.get());
    }

    @Override
    public CowEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return ModEntities.HIGHLAND_CATTLE.get().create(serverWorld);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        boolean bl;
        if (((bl = itemStack.isOf(Items.BUCKET)) || itemStack.isOf(ObjectRegistry.WOODEN_BUCKET.get())) && !this.isBaby()) {
            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, bl ? ObjectRegistry.BUFFALO_MILK.get().getDefaultStack() : ObjectRegistry.WOODEN_BUFFALO_MILK.get().getDefaultStack());
            player.setStackInHand(hand, itemStack2);
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }
}


