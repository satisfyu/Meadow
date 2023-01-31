package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.item.ModItems;

import java.util.List;

public class CanBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 15, 13);

    public static final IntProperty FLUID = IntProperty.of("fluid", 0, 2);

    public CanBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(FLUID, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (!(item instanceof BucketItem || item instanceof MilkBucketItem)) {
            return ActionResult.PASS;
        }

        if (!world.isClient) {

            boolean water = false;
            boolean wood = false;
            if(state.get(FLUID) == 0 && (item.equals(Items.MILK_BUCKET) || (water = item.equals(Items.WATER_BUCKET)) || (wood = item.equals(ModItems.WOODEN_MILK_BUCKET)) || (water = wood = item.equals(ModItems.WOODEN_WATER_BUCKET)))){
                player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, new ItemStack(wood ? ModItems.WOODEN_BUCKET : Items.BUCKET)));
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.with(FLUID, water ? 2 : 1));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                return ActionResult.SUCCESS;
            }
            else if((state.get(FLUID) == 1 || state.get(FLUID) == 2) && (item.equals(Items.BUCKET) || item.equals(ModItems.WOODEN_BUCKET))){
                //player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, new ItemStack(state.get(FLUID) == 2 ? Items.WATER_BUCKET : Items.MILK_BUCKET)));

                boolean bl = item.equals(ModItems.WOODEN_BUCKET);

                player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, new ItemStack(state.get(FLUID) == 2 ? bl ? ModItems.WOODEN_WATER_BUCKET : Items.WATER_BUCKET : bl ? ModItems.WOODEN_MILK_BUCKET : Items.MILK_BUCKET)));

                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.with(FLUID, 0));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.success(world.isClient);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLUID, FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }


    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.meadow.canbeplaced.tooltip").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}
