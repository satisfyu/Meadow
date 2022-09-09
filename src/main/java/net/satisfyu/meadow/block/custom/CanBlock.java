package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.Meadow;

public class CanBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 16, 13);

    public static final IntProperty FLUID = IntProperty.of("fluid", 0, 2);

    public CanBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getDefaultState().with(FLUID, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ItemStack itemStack = player.getStackInHand(hand);
            Item item = itemStack.getItem();
            player.sendMessage(Text.literal("interact"));
            if(state.get(FLUID) == 0 && (item.equals(Items.MILK_BUCKET) || item.equals(Items.WATER_BUCKET))){
                player.sendMessage(Text.literal("yeah"));
                player.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, player, new ItemStack(Items.BUCKET)));
                //player.incrementStat(Meadow.FILL_CAN);
                player.incrementStat(Stats.USED.getOrCreateStat(item));
                world.setBlockState(pos, state.with(FLUID, item.equals(Items.MILK_BUCKET) ? 1 : 2));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLUID);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
