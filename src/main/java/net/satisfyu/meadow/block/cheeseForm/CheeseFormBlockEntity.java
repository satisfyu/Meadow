package net.satisfyu.meadow.block.cheeseForm;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.entity.ModEntities;

import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock.VAR;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreen.getTimeToCook;

public class CheeseFormBlockEntity extends BlockEntity {

    private int syncedInt;

    public CheeseFormBlockEntity(BlockPos pos, BlockState state) {
        super(ModEntities.CHEESE_FORM_BLOCK_ENTITY, pos, state);
    }


    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("syncedInt", syncedInt);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        syncedInt = nbt.getInt("syncedInt");
    }

    public void tick(World world, BlockPos pos, BlockState state, CheeseFormBlockEntity be) {
        if(!world.isClient){
            if(!state.get(CheeseFormBlock.DONE) && state.get(VAR) > 0){
                syncedInt++;
            }
            if(syncedInt >= getTimeToCook()){
                world.setBlockState(pos, state.with(CheeseFormBlock.DONE, true));
                syncedInt = 0;
            }
        }
    }
}
