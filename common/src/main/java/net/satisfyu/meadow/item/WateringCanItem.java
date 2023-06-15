package net.satisfyu.meadow.item;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WateringCanItem extends BlockItem {
    public WateringCanItem(Block block, Settings settings) {
        super(block, settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        if(playerEntity == null || playerEntity.isSneaking()) return super.useOnBlock(context);
        ItemStack stack = context.getStack();

        World world = context.getWorld();
        BlockHitResult hitResult = WateringCanItem.raycast(world, playerEntity, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            if (!world.canPlayerModifyAt(playerEntity, blockPos)) {
                return ActionResult.PASS;
            }
            if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                world.playSound(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                world.emitGameEvent(playerEntity, GameEvent.FLUID_PICKUP, blockPos);
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                stack.setDamage(0);
                return ActionResult.SUCCESS;
            }
        }


        if(stack.getDamage() >= stack.getMaxDamage() && !playerEntity.getAbilities().creativeMode) return ActionResult.PASS;
        BlockPos blockPos = context.getBlockPos();
        BlockPos blockPos2 = blockPos.offset(context.getSide());
        if (WateringCanItem.useOnFertilizable(stack, world, blockPos, playerEntity)) {
            if (!world.isClient) {
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 0);
            }
            return ActionResult.success(world.isClient);
        }
        BlockState blockState = world.getBlockState(blockPos);
        boolean bl = blockState.isSideSolidFullSquare(world, blockPos, context.getSide());
        if (bl && WateringCanItem.useOnGround(stack, world, blockPos2, context.getSide(), playerEntity)) {
            if (!world.isClient) {
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos2, 0);
            }
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    public static boolean useOnFertilizable(ItemStack stack, World world, BlockPos pos, PlayerEntity playerEntity) {
        Fertilizable fertilizable;
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof Fertilizable && (fertilizable = (Fertilizable) blockState.getBlock()).isFertilizable(world, pos, blockState, world.isClient)) {
            if (world instanceof ServerWorld) {
                if (fertilizable.canGrow(world, world.random, pos, blockState)) {
                    fertilizable.grow((ServerWorld)world, world.random, pos, blockState);
                }
                damage(stack, playerEntity);
            }
            return true;
        }
        return false;
    }


    public static boolean useOnGround(ItemStack stack, World world, BlockPos blockPos, @Nullable Direction facing, PlayerEntity playerEntity) {
        if (!world.getBlockState(blockPos).isOf(Blocks.WATER) || world.getFluidState(blockPos).getLevel() != 8) {
            return false;
        }
        if (!(world instanceof ServerWorld)) {
            return true;
        }
        Random random = world.getRandom();
        block0: for (int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos;
            BlockState blockState = Blocks.SEAGRASS.getDefaultState();
            for (int j = 0; j < i / 16; ++j) {
                if (world.getBlockState(blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1)).isFullCube(world, blockPos2)) continue block0;
            }
            RegistryEntry<Biome> registryEntry = world.getBiome(blockPos2);
            if (registryEntry.isIn(BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL)) {
                if (i == 0 && facing != null && facing.getAxis().isHorizontal()) {
                    blockState = Registry.BLOCK.getEntryList(BlockTags.WALL_CORALS).flatMap(blocks -> blocks.getRandom(world.random)).map(blockEntry -> blockEntry.value().getDefaultState()).orElse(blockState);
                    if (blockState.contains(DeadCoralWallFanBlock.FACING)) {
                        blockState = blockState.with(DeadCoralWallFanBlock.FACING, facing);
                    }
                } else if (random.nextInt(4) == 0) {
                    blockState = Registry.BLOCK.getEntryList(BlockTags.UNDERWATER_BONEMEALS).flatMap(blocks -> blocks.getRandom(world.random)).map(blockEntry -> blockEntry.value().getDefaultState()).orElse(blockState);
                }
            }
            if (blockState.isIn(BlockTags.WALL_CORALS, state -> state.contains(DeadCoralWallFanBlock.FACING))) {
                for (int k = 0; !blockState.canPlaceAt(world, blockPos2) && k < 4; ++k) {
                    blockState = blockState.with(DeadCoralWallFanBlock.FACING, Direction.Type.HORIZONTAL.random(random));
                }
            }
            if (!blockState.canPlaceAt(world, blockPos2)) continue;
            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.isOf(Blocks.WATER) && world.getFluidState(blockPos2).getLevel() == 8) {
                world.setBlockState(blockPos2, blockState, Block.NOTIFY_ALL);
                continue;
            }
            if (!blockState2.isOf(Blocks.SEAGRASS) || random.nextInt(10) != 0) continue;
            ((Fertilizable) Blocks.SEAGRASS).grow((ServerWorld)world, random, blockPos2, blockState2);
        }
        damage(stack, playerEntity);
        return true;
    }

    public static void damage(ItemStack stack, PlayerEntity entity){
        if(entity.getAbilities().creativeMode) return;
        int damage = stack.getDamage();
        if(damage < 25){
            stack.setDamage(damage + 1);
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.meadow.canline1.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
        tooltip.add(Text.translatable("item.meadow.canline2.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
        tooltip.add(Text.translatable("item.meadow.canbeplaced.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));

    }
}
