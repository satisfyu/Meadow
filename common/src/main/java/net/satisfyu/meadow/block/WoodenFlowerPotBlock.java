package net.satisfyu.meadow.block;

import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.registry.ObjectRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class WoodenFlowerPotBlock extends Block {
    private static final Map<Block, Block> WOODEN_CONTENT_TO_POTTED = Maps.newHashMap();
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);
    private final Block content;

    public WoodenFlowerPotBlock(Block content, Properties settings) {
        super(settings);
        this.content = content;
        WOODEN_CONTENT_TO_POTTED.put(content, this);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        boolean bl2;
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        BlockState blockState = (item instanceof BlockItem ? WOODEN_CONTENT_TO_POTTED.getOrDefault(((BlockItem) item).getBlock(), Blocks.AIR) : Blocks.AIR).defaultBlockState();
        boolean bl = blockState.is(Blocks.AIR);
        if (bl != (bl2 = this.isEmpty())) {
            if (bl2) {
                world.setBlock(pos, blockState, Block.UPDATE_ALL);
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            } else {
                ItemStack itemStack2 = new ItemStack(this.content);
                if (itemStack.isEmpty()) {
                    player.setItemInHand(hand, itemStack2);
                } else if (!player.addItem(itemStack2)) {
                    player.drop(itemStack2, false);
                }
                world.setBlock(pos, ObjectRegistry.WOODEN_FLOWER_POT.get().defaultBlockState(), Block.UPDATE_ALL);
            }
            world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        if (this.isEmpty()) {
            return super.getCloneItemStack(world, pos, state);
        }
        return new ItemStack(this.content);
    }

    private boolean isEmpty() {
        return this.content == Blocks.AIR;
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canSurvive(world, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType type) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.meadow.canbeplaced.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }

    public ItemLike getContent() {
        return this.content;
    }

}