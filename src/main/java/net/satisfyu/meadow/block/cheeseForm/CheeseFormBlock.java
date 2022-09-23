package net.satisfyu.meadow.block.cheeseForm;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.ModItems;
import net.satisfyu.meadow.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class CheeseFormBlock extends BlockWithEntity {

    public static final IntProperty VAR = IntProperty.of("var", 0, 7);

    public static final BooleanProperty DONE = BooleanProperty.of("done");

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 5, 13);

    public CheeseFormBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(DONE, false).with(VAR, 0));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }



    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i;
        ItemStack stack = player.getStackInHand(hand);
        if(state.get(VAR) == 0 && (i = getVar(stack.getItem())) < 8){
            world.setBlockState(pos, state.with(VAR, i));
            if(!player.getAbilities().creativeMode) stack.decrement(1);
            return ActionResult.success(world.isClient);
        }
        else if(state.get(DONE) && player.getAbilities().allowModifyWorld && player.getStackInHand(hand).isEmpty() && player.canConsume(false)){
            world.setBlockState(pos, state.with(VAR, 0).with(DONE, false));
            player.getHungerManager().add(6, 0.6F);
            world.playSound(null, pos, ModSounds.SLURPING_BOWL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModEntities.CHEESE_FORM_BLOCK_ENTITY, (world1, pos, state1, be) -> be.tick(world1, pos, state1, be));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CheeseFormBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public static int getVar(Item item){
        if (ModItems.CHEESE_MASS.equals(item) || ModItems.WOODEN_CHEESE_MASS.equals(item)) {
            return 2;
        } else if (ModItems.BUFFALO_CHEESE_MASS.equals(item) || ModItems.WOODEN_BUFFALO_CHEESE_MASS.equals(item)) {
            return 1;
        } else if (ModItems.GOAT_CHEESE_MASS.equals(item) || ModItems.WOODEN_GOAT_CHEESE_MASS.equals(item)) {
            return 3;
        } else if (ModItems.OAT_CHEESE_MASS.equals(item) || ModItems.WOODEN_OAT_CHEESE_MASS.equals(item)) {
            return 5;
        } else if (ModItems.SHEEP_CHEESE_MASS.equals(item) || ModItems.WOODEN_SHEEP_CHEESE_MASS.equals(item)) {
            return 6;
        } else if (ModItems.LAVENDER_CHEESE_MASS.equals(item) || ModItems.WOODEN_LAVENDER_CHEESE_MASS.equals(item)) {
            return 4;
        } else if (ModItems.HERBS_CHEESE_MASS.equals(item) || ModItems.WOODEN_HERBS_CHEESE_MASS.equals(item)) {
            return 7;
        }
        else{
            return 100;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VAR, DONE);
    }
}
