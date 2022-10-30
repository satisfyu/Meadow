package net.satisfyu.meadow.block.cookingCauldron;

import net.minecraft.block.Block;
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
import net.satisfyu.meadow.block.ImplementedInventory;
import net.satisfyu.meadow.entity.ModEntities;
import net.satisfyu.meadow.item.custom.WoodenMilkBucket;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.state.property.Properties.LIT;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlock.*;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronRecipes.getVar;
import static net.satisfyu.meadow.block.cookingCauldron.CookingCauldronScreen.getTimeToCook;

public class CookingCauldronBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    public static final Text TITLE = Text.translatable("container.cooking_cauldron");
    private int syncedInt;

    private int isCooking;

    private Optional<CookingCauldronRecipes> recipe = Optional.empty();


    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public CookingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(ModEntities.COOKING_CAULDRON_BLOCK_ENTITY, pos, state);
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0 -> {
                    return syncedInt;
                }
                case 1 -> {
                    return isCooking;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> syncedInt = value;
                case 1 -> isCooking = value;
            }
        }
        @Override
        public int size() {
            return 2;
        }

    };


    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("syncedInt", syncedInt);
        String s;
        if(recipe.isPresent()){
            s = recipe.get().toString();
        }
        else {
            s = "empty";
        }
        nbt.putString("recipe", s);
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        syncedInt = nbt.getInt("syncedInt");
        String s = nbt.getString("recipe");
        if(s.equals("empty")){
            recipe = Optional.empty();
        }
        else {
            recipe = Optional.of(CookingCauldronRecipes.valueOf(s));
        }
        Inventories.readNbt(nbt, items);
    }

    public void tick(World world, BlockPos pos, BlockState state, CookingCauldronBlockEntity be) {
        if(!world.isClient){
            int var = 0;

            boolean done = false;

            isCooking = isLit(state) ? 1 : 0;
            if(syncedInt >= getTimeToCook()){
                boolean isWood = false;
                for(ItemStack itemStack : items){
                    int index = items.indexOf(itemStack);
                    if(index > 2) continue;
                    if(itemStack.getItem() instanceof MilkBucketItem){
                        isWood = itemStack.getItem() instanceof WoodenMilkBucket;
                        items.set(index, ItemStack.EMPTY);
                    }
                    else {
                        itemStack.decrement(1);
                        items.set(index, itemStack);
                    }
                }
                items.set(3, new ItemStack(isWood ? recipe.get().getOutputW() : recipe.get().getOutput()));
                syncedInt = 0;
                done = true;
            }
            else if(isLit(state) && items.get(3).isEmpty()){
                recipe = CookingCauldronRecipes.getMatchingRecipe(items.get(0).getItem(), items.get(1).getItem(), items.get(2).getItem());

                if(recipe.isPresent()){
                    syncedInt++;
                }
                else {
                    syncedInt = 0;
                }
            }
            else {
                syncedInt = 0;
                if(!items.get(3).isEmpty()){
                    done = true;
                }
                if(!done){
                    recipe = Optional.empty();
                }
            }

            if(recipe.isPresent()){
                var = getVar(recipe.get());
            }
            if(state.get(VAR) == var && state.get(DONE) == done){
                return;
            }
            world.setBlockState(pos, state.with(VAR, var).with(HANGING, state.get(HANGING)).with(DONE, done).with(FACING, state.get(FACING)), Block.NOTIFY_ALL);
        }
    }

    //   /data modify block -216 68 -461 syncedInt set value 47750
    //   /kill @e[type=minecraft:item]

    public boolean isLit(BlockState state){
        if(state.get(HANGING)) return true;
        BlockState downState = world.getBlockState(pos.down());
        if(downState.contains(LIT)){
            return downState.get(LIT);
        }
        return false;
    }




    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CookingCauldronScreenHandler(syncId, inv, propertyDelegate, this);
    }

    @Override
    public Text getDisplayName() {
        return TITLE;
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
}
