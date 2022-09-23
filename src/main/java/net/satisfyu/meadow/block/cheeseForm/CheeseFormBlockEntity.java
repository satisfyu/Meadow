package net.satisfyu.meadow.block.cheeseForm;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ImplementedInventory;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronRecipes;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreenHandler;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.custom.WoodenMilkBucket;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.state.property.Properties.LIT;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock.*;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronRecipes.getVar;
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
