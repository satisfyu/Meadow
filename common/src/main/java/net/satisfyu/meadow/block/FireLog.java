package net.satisfyu.meadow.block;

import de.cristelknight.doapi.common.block.FacingBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.List;

public class FireLog extends FacingBlock {

    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);

    private static final VoxelShape SHAPE_AXE = Block.box(1, 0, 1, 15, 4, 15);

    private static final VoxelShape SHAPE_SMALL = Block.box(1, 0, 1, 15, 8, 15);
    private static final VoxelShape SHAPE_MID = Block.box(1, 0, 1, 15, 12, 15);
    private static final VoxelShape SHAPE_BIG = Block.box(1, 0, 1, 15, 16, 15);

    public FireLog(Properties setting) {
        super(setting);
        this.registerDefaultState(this.defaultBlockState().setValue(STAGE, 1));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        int stage = state.getValue(STAGE);
        if (stage == 0) return SHAPE_AXE;
        else if (stage == 1) return SHAPE_SMALL;
        else if (stage == 2) return SHAPE_MID;
        else if (stage == 3) return SHAPE_BIG;
        else return SHAPE_AXE;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        int stage = state.getValue(STAGE);
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (stack.isEmpty() && stage > 0) {
                stage--;
                player.addItem(new ItemStack(ObjectRegistry.FIRE_LOG.get()));
                world.setBlockAndUpdate(pos, state.setValue(STAGE, stage));
                world.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
                return InteractionResult.SUCCESS;
            }
        } else {
            if (stack.is(this.asItem())) {
                if (stage < 3 && stage > 0) {
                    stage++;
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                }
            } else if (stack.is(Items.IRON_AXE) && stage == 1 && stack.getDamageValue() == 0) {
                stage = 0;
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }

        if (stage == state.getValue(STAGE)) {
            return InteractionResult.PASS;
        }

        world.setBlockAndUpdate(pos, state.setValue(STAGE, stage));
        world.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
        return InteractionResult.SUCCESS;
    }

    private void dropItemStack(Level world, BlockPos pos) {
        Block.popResource(world, pos, new ItemStack(ObjectRegistry.FIRE_LOG.get(), 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, BlockGetter world, List<Component> tooltip, TooltipFlag tooltipContext) {
        tooltip.add(Component.translatable("block.meadow.canbeplaced.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        tooltip.add(Component.translatable("block.meadow.fuel_item.tooltip").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
    }
}
