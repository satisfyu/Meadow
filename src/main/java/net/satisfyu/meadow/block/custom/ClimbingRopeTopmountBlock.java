package net.satisfyu.meadow.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.satisfyu.meadow.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ClimbingRopeTopmountBlock extends Block {
    protected static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(7, 0, 7, 9, 16, 9), Block.createCuboidShape(6, 8, 6, 10, 12, 10));

    public ClimbingRopeTopmountBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient){
            for(int i = 1; i <= 10; i++){
                BlockPos blockPos = pos.offset(Direction.DOWN, i);
                if(world.canSetBlock(blockPos) && world.getBlockState(blockPos).isAir()){
                    world.setBlockState(blockPos, ModBlocks.CLIMBING_ROPE.getDefaultState());
                }
            }
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(!world.isClient()){
            if (Block.sideCoversSmallSquare(world, pos.up(), Direction.DOWN)) {
                return state;
            }
            world.breakBlock(pos, true);
            return world.getBlockState(pos);
        }
        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.up(), Direction.DOWN) && world.isAir(pos.down());
    }
}
