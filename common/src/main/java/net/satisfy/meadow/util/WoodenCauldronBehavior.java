package net.satisfy.meadow.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.satisfy.meadow.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

@SuppressWarnings("unused")
public interface WoodenCauldronBehavior extends CauldronInteraction {
    Map<Item, CauldronInteraction> EMPTY = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> WATER = CauldronInteraction.newInteractionMap();
    Map<Item, CauldronInteraction> POWDER_SNOW = CauldronInteraction.newInteractionMap();

    CauldronInteraction FILL_WATER = (state, world, pos, player, hand, stack) -> WoodenCauldronBehavior.fillCauldron(world, pos, player, hand, stack, ObjectRegistry.WOODEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 3), SoundEvents.BUCKET_EMPTY, Items.BUCKET);
    CauldronInteraction FILL_POWDER_SNOW = (state, world, pos, player, hand, stack) -> WoodenCauldronBehavior.fillCauldron(world, pos, player, hand, stack, ObjectRegistry.WOODEN_POWDER_SNOW_CAULDRON.get().defaultBlockState().setValue(LEVEL, 3), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, Items.BUCKET);
    CauldronInteraction FILL_WITH_WATER_W = (state, world, pos, player, hand, stack) -> WoodenCauldronBehavior.fillCauldron(world, pos, player, hand, stack, ObjectRegistry.WOODEN_WATER_CAULDRON.get().defaultBlockState().setValue(LEVEL, 3), SoundEvents.BUCKET_EMPTY, ObjectRegistry.WOODEN_BUCKET.get());

    @NotNull InteractionResult interact(BlockState var1, Level var2, BlockPos var3, Player var4, InteractionHand var5, ItemStack var6);

    static void bootStrap() {
        registerCauldronBehavior();

        WoodenCauldronBehavior.addDefaultInteractions(EMPTY);
        EMPTY.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            if (PotionUtils.getPotion(stack) != Potions.WATER) {
                return InteractionResult.PASS;
            }
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.ITEM_USED.get(item));
                world.setBlockAndUpdate(pos, ObjectRegistry.WOODEN_WATER_CAULDRON.get().defaultBlockState());
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }
            return InteractionResult.sidedSuccess(world.isClientSide);
        });
        WoodenCauldronBehavior.addDefaultInteractions(WATER);
        WATER.put(Items.BUCKET, (state2, world, pos, player, hand, stack) -> WoodenCauldronBehavior.fillBucket(state2, world, pos, player, hand, stack, new ItemStack(Items.WATER_BUCKET), state -> state.getValue(LEVEL) == 3, SoundEvents.BUCKET_FILL));
        WATER.put(ObjectRegistry.WOODEN_BUCKET.get(), (state2, world, pos, player, hand, stack) -> WoodenCauldronBehavior.fillBucket(state2, world, pos, player, hand, stack, new ItemStack(ObjectRegistry.WOODEN_WATER_BUCKET.get()), state -> state.getValue(LEVEL) == 3, SoundEvents.BUCKET_FILL));
        WATER.put(Items.GLASS_BOTTLE, (state, world, pos, player, hand, stack) -> {
            if (!world.isClientSide) {
                Item item = stack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)));
                player.awardStat(Stats.ITEM_USED.get(item));
                LayeredCauldronBlock.lowerFillLevel(state, world, pos);
                world.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);
                world.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
            }
            return InteractionResult.sidedSuccess(world.isClientSide);
        });
        WATER.put(Items.POTION, (state, world, pos, player, hand, stack) -> {
            if (state.getValue(LEVEL) == 3 || PotionUtils.getPotion(stack) != Potions.WATER) {
                return InteractionResult.PASS;
            }
            if (!world.isClientSide) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                world.setBlockAndUpdate(pos, state.cycle(LEVEL));
                world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
                world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
            }
            return InteractionResult.sidedSuccess(world.isClientSide);
        });
        WATER.put(Items.LEATHER_BOOTS, DYED_ITEM);
        WATER.put(Items.LEATHER_LEGGINGS, DYED_ITEM);
        WATER.put(Items.LEATHER_CHESTPLATE, DYED_ITEM);
        WATER.put(Items.LEATHER_HELMET, DYED_ITEM);
        WATER.put(Items.LEATHER_HORSE_ARMOR, DYED_ITEM);
        WATER.put(Items.WHITE_BANNER, BANNER);
        WATER.put(Items.GRAY_BANNER, BANNER);
        WATER.put(Items.BLACK_BANNER, BANNER);
        WATER.put(Items.BLUE_BANNER, BANNER);
        WATER.put(Items.BROWN_BANNER, BANNER);
        WATER.put(Items.CYAN_BANNER, BANNER);
        WATER.put(Items.GREEN_BANNER, BANNER);
        WATER.put(Items.LIGHT_BLUE_BANNER, BANNER);
        WATER.put(Items.LIGHT_GRAY_BANNER, BANNER);
        WATER.put(Items.LIME_BANNER, BANNER);
        WATER.put(Items.MAGENTA_BANNER, BANNER);
        WATER.put(Items.ORANGE_BANNER, BANNER);
        WATER.put(Items.PINK_BANNER, BANNER);
        WATER.put(Items.PURPLE_BANNER, BANNER);
        WATER.put(Items.RED_BANNER, BANNER);
        WATER.put(Items.YELLOW_BANNER, BANNER);
        WATER.put(Items.WHITE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.GRAY_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.BLACK_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.BLUE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.BROWN_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.CYAN_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.GREEN_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.LIGHT_BLUE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.LIGHT_GRAY_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.LIME_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.MAGENTA_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.ORANGE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.PINK_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.PURPLE_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.RED_SHULKER_BOX, SHULKER_BOX);
        WATER.put(Items.YELLOW_SHULKER_BOX, SHULKER_BOX);
        POWDER_SNOW.put(Items.BUCKET, (state2, world, pos, player, hand, stack) -> WoodenCauldronBehavior.fillBucket(state2, world, pos, player, hand, stack, new ItemStack(Items.POWDER_SNOW_BUCKET), state -> state.getValue(LEVEL) == 3, SoundEvents.BUCKET_FILL_POWDER_SNOW));
        WoodenCauldronBehavior.addDefaultInteractions(POWDER_SNOW);
    }

    static void addDefaultInteractions(Map<Item, CauldronInteraction> behavior) {
        behavior.put(Items.WATER_BUCKET, FILL_WATER);
        behavior.put(ObjectRegistry.WOODEN_WATER_BUCKET.get(), FILL_WITH_WATER_W);
        behavior.put(Items.POWDER_SNOW_BUCKET, FILL_POWDER_SNOW);
    }

    static InteractionResult fillBucket(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, ItemStack output, Predicate<BlockState> predicate, SoundEvent soundEvent) {
        if (!predicate.test(state)) {
            return InteractionResult.PASS;
        }
        if (!world.isClientSide) {
            Item item = stack.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, output));
            player.awardStat(Stats.ITEM_USED.get(item));
            world.setBlockAndUpdate(pos, ObjectRegistry.WOODEN_CAULDRON.get().defaultBlockState());
            world.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    static InteractionResult fillCauldron(Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState state, SoundEvent soundEvent, Item returnItem) {
        if (!world.isClientSide) {
            Item item = stack.getItem();
            player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(returnItem)));
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            world.setBlockAndUpdate(pos, state);
            world.playSound(null, pos, soundEvent, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.gameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    static void registerCauldronBehavior() {
        CauldronInteraction.WATER.put(ObjectRegistry.WOODEN_BUCKET.get(), (state2, world, pos, player, hand, stack) -> CauldronInteraction.fillBucket(state2, world, pos, player, hand, stack, new ItemStack(ObjectRegistry.WOODEN_WATER_BUCKET.get()), state -> state.getValue(LEVEL) == 3, SoundEvents.BUCKET_FILL));
        registerBucketBehaviorForNormalCauldron(CauldronInteraction.EMPTY);
    }

    static void registerBucketBehaviorForNormalCauldron(Map<Item, CauldronInteraction> behavior) {
        CauldronInteraction fillWithWater = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LEVEL, 3), SoundEvents.BUCKET_EMPTY, ObjectRegistry.WOODEN_BUCKET.get());
        CauldronInteraction fillWithPowderSnow = (state, world, pos, player, hand, stack) -> fillCauldron(world, pos, player, hand, stack, Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(LEVEL, 3), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, ObjectRegistry.WOODEN_BUCKET.get());
        behavior.put(ObjectRegistry.WOODEN_WATER_BUCKET.get(), fillWithWater);
    }

}
