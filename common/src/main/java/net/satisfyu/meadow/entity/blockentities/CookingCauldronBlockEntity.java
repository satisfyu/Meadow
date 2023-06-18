package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.client.gui.handler.CookingCauldronGuiHandler;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.util.ImplementedInventory;
import net.satisfyu.meadow.util.MeadowTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.state.property.Properties.LIT;
import static net.satisfyu.meadow.block.CookingCauldronBlock.*;

public class CookingCauldronBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    public static final Text TITLE = Text.translatable("container.cooking_cauldron");
    public static final int MAX_COOKING_TIME = 6000; // Time in ticks (300s)
    private int syncedInt;
    private int isCooking;


    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(7, ItemStack.EMPTY);

    public CookingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COOKING_CAULDRON_BLOCK_ENTITY.get(), pos, state);
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
        Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        syncedInt = nbt.getInt("syncedInt");
        Inventories.readNbt(nbt, items);
    }

    public void tick(World world, BlockPos pos, BlockState state, CookingCauldronBlockEntity be) {
        if (!world.isClient) {
            int var = 0;
            Optional<CookingCauldronRecipe> recipe = world.getRecipeManager().getFirstMatch(RecipeRegistry.COOKING.get(), this, world);

            boolean done = false;

            isCooking = isLit(state) ? 1 : 0;
            if (syncedInt > MAX_COOKING_TIME) {
                boolean isWood = false;
                for (ItemStack itemStack : items) {
                    int index = items.indexOf(itemStack);
                    if (index > 2) continue;

                    if (itemStack.isIn(MeadowTags.WOODEN_BUCKETS)) {
                        isWood = true;
                        items.set(index, new ItemStack(ObjectRegistry.WOODEN_BUCKET.get()));
                    } else if (itemStack.isIn(MeadowTags.BUCKETS)) {
                        items.set(index, new ItemStack(Items.BUCKET));
                    } else {
                        itemStack.decrement(1);
                        items.set(index, itemStack);
                    }
                }
                items.set(3, isWood ? recipe.get().craftIfWoodBucket(this) : recipe.get().craft(this));
                syncedInt = 0;
                done = true;
            } else if (isLit(state) && items.get(3).isEmpty()) {
                if (recipe.isPresent()) {
                    syncedInt++;
                } else {
                    syncedInt = 0;
                }
            } else {
                syncedInt = 0;
                if (!items.get(3).isEmpty()) {
                    done = true;
                }
                if (!done) {
                    recipe = Optional.empty();
                }
            }

            if (recipe.isPresent()) {
                var = getVar(recipe.get().getOutput().getItem());
            } else if (!items.get(3).isEmpty()) {
                var = getVar(items.get(3).getItem());
            }
            world.setBlockState(pos, state.with(VAR, var).with(HANGING, state.get(HANGING)).with(DONE, done).with(FACING, state.get(FACING)), Block.NOTIFY_ALL);
        }
    }

    public static int getVar(Item outputItem) {
        if (outputItem.equals(ObjectRegistry.OAT_CHEESE_MASS) || outputItem.equals(ObjectRegistry.WOODEN_HERBS_CHEESE_MASS))
            return 5;

        else return 5;
    }

    //   /data modify block -216 68 -461 syncedInt set value 47750
    //   /kill @e[type=minecraft:item]

    public boolean isLit(BlockState state) {
        if (state.get(HANGING)) return true;
        BlockState downState = world.getBlockState(pos.down());
        if (downState.contains(LIT)) {
            return downState.get(LIT);
        } else return downState.isOf(ObjectRegistry.STOVE_LID.get());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CookingCauldronGuiHandler(syncId, inv, this, propertyDelegate);
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
