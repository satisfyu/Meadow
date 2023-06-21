package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
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
import net.satisfyu.meadow.client.screen.handler.CookingCauldronGuiHandler;
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
    private boolean isCooking;


    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    public CookingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COOKING_CAULDRON_BLOCK_ENTITY.get(), pos, state);
    }

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            if (index == 0) {
                return syncedInt;
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                syncedInt = value;
            }
        }

        @Override
        public int size() {
            return 1;
        }

    };


    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("syncedInt", syncedInt);
        Inventories.writeNbt(nbt, inventory);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        syncedInt = nbt.getInt("syncedInt");
        Inventories.readNbt(nbt, inventory);
    }

    public void tick(World world, BlockPos pos, BlockState state, CookingCauldronBlockEntity be) {
        if (world.isClient) {
            return;
        }
        Optional<CookingCauldronRecipe> recipe = world.getRecipeManager().getFirstMatch(RecipeRegistry.COOKING.get(), this, world);

        boolean done = false;

        isCooking = isLit(state);
        if (syncedInt > MAX_COOKING_TIME) {
            for (ItemStack itemStack : inventory) {
                int index = inventory.indexOf(itemStack);
                if (index >= 6) continue;

                if (itemStack.isIn(MeadowTags.WOODEN_BUCKETS)) {
                    inventory.set(index, new ItemStack(ObjectRegistry.WOODEN_BUCKET.get()));
                } else if (itemStack.isIn(MeadowTags.BUCKETS)) {
                    inventory.set(index, new ItemStack(Items.BUCKET));
                } else {
                    itemStack.decrement(1);
                    inventory.set(index, itemStack);
                }
            }
            inventory.set(0, recipe.get().getOutput());
            syncedInt = 0;
            done = true;
        } else if (isLit(state) && inventory.get(0).isEmpty()) {
            if (recipe.isPresent()) {
                syncedInt++;
            } else {
                syncedInt = 0;
            }
        } else {
            syncedInt = 0;
            if (!inventory.get(0).isEmpty()) {
                done = true;
            }
        }
        world.setBlockState(pos, state.with(VAR, 5).with(HANGING, state.get(HANGING)).with(DONE, done).with(FACING, state.get(FACING)), Block.NOTIFY_ALL);
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
    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }
}
