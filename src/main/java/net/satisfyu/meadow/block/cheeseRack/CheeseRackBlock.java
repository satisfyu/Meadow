package net.satisfyu.meadow.block.cheeseRack;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.block.custom.FacingBlock;
import net.satisfyu.meadow.util.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CheeseRackBlock extends FacingBlock implements BlockEntityProvider {

    public static final IntProperty STAGE = IntProperty.of("stage", 0, 2);

    public CheeseRackBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(STAGE, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        CheeseRackBlockEntity be = (CheeseRackBlockEntity) world.getBlockEntity(pos);
        if(be == null || player.isSneaking()) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);
        List<Item> list = be.getItems();

        if(stack.isEmpty()){
            Optional<Item> stackOutOfList = list.stream().findFirst();
            if(stackOutOfList.isPresent()){
                Item item = stackOutOfList.get();
                player.giveItemStack(new ItemStack(item));
                list.remove(item);
                be.setItems(list);
                world.setBlockState(pos, state.with(STAGE, list.size()));
                return ActionResult.SUCCESS;
            }
        }
        else if (stack.isIn(Tags.CHEESE_BLOCKS) && list.size() < 2) {
            list.add(stack.getItem());
            stack.decrement(1);
            be.setItems(list);
            world.setBlockState(pos, state.with(STAGE, list.size()));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.IGNORE;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CheeseRackBlockEntity be) {
                for(Item stack : be.getItems()){
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(stack));
                }
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseRackBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STAGE);
    }
}

