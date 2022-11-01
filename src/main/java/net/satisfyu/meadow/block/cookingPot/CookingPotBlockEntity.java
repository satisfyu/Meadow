package net.satisfyu.meadow.block.cookingPot;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.satisfyu.meadow.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class CookingPotBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public CookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModEntities.COOKING_POT, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return CookingPotBlockEntity.this.progress;
                    case 1: return CookingPotBlockEntity.this.maxProgress;
                    default: return 0;
        super(ModEntities.COOKING_POT_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(MAX_CAPACITY, ItemStack.EMPTY);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(MAX_CAPACITY, ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
        nbt.putInt("CookingTime", this.cookingTime);
        nbt.putBoolean("isBeingBurned", this.isBeingBurned);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        this.cookingTime = nbt.getInt("CookingTime");
        this.isBeingBurned = nbt.getBoolean("isBeingBurned");
        Inventories.writeNbt(nbt, this.inventory);
    }

    public boolean isBeingBurned() {
        if (getWorld() == null) throw new NullPointerException("Null world invoked");
        final BlockState belowState = this.getWorld().getBlockState(getPos().down());
        final var optionalList = Registry.BLOCK.getEntryList(Tags.ALLOWS_COOKING_ON_POT);
        final var entryList = optionalList.orElse(null);
        if (entryList == null) {
            return false;
        } else if (!entryList.contains(belowState.getBlock().getRegistryEntry())) {
            return false;
        } else return belowState.get(Properties.LIT);

    }

    private boolean canCraft(CookingPotRecipe recipe) {
        if (recipe == null || recipe.getOutput().isEmpty()) {
            return false;
        } else if (!this.getStack(BOTTLE_INPUT_SLOT).isOf(recipe.getContainer().getItem())) {
            return false;
        } else if (this.getStack(OUTPUT_SLOT).isEmpty()) {
            return true;
        } else {
            final ItemStack recipeOutput = recipe.getOutput();
            final ItemStack outputSlotStack = this.getStack(OUTPUT_SLOT);
            final int outputSlotCount = outputSlotStack.getCount();
            if (!outputSlotStack.isItemEqualIgnoreDamage(recipeOutput)) {
                return false;
            } else if (outputSlotCount < this.getMaxCountPerStack() && outputSlotCount < outputSlotStack.getMaxCount()) {
                return true;
            } else {
                return outputSlotCount < recipeOutput.getMaxCount();
            }
        }
    }

    private void craft(CookingPotRecipe recipe) {
        if (!canCraft(recipe)) {
            return;
        }
        final ItemStack recipeOutput = recipe.getOutput();
        final ItemStack outputSlotStack = this.getStack(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            setStack(OUTPUT_SLOT, recipeOutput.copy());
        } else if (outputSlotStack.isOf(recipeOutput.getItem())) {
            outputSlotStack.increment(recipeOutput.getCount());
        }
        final DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        // each slot can only be used once because in canMake we only checked if decrement by 1 still retains the recipe
        // otherwise recipes can break when an ingredient is used multiple times
        boolean[] slotUsed = new boolean[INGREDIENTS_AREA];
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            // Looks for the best slot to take it from
            final ItemStack bestSlot = this.getStack(i);
            if (ingredient.test(bestSlot) && !slotUsed[i]) {
                slotUsed[i] = true;
                bestSlot.decrement(1);
            } else {
                // check all slots in search of the ingredient
                for (int j = 0; j < INGREDIENTS_AREA; j++) {
                    ItemStack stack = this.getStack(j);
                    if (ingredient.test(stack) && !slotUsed[j]) {
                        slotUsed[j] = true;
                        stack.decrement(1);
                    }
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CookingPotBlockEntity.this.progress = value; break;
                    case 1: CookingPotBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
        return inventory;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }
        if (totalCookingTime == 0) {
            this.totalCookingTime = MAX_COOKING_TIME;
        }
        this.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Cooking Pot");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CookingPotScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("cooking_pot.progress", progress);
        return new CookingPotScreenHandler(syncId, inv, this, delegate);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBoolean(isBeingBurned);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("cooking_pot.progress");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, CookingPotBlockEntity entity) {
        if(world.isClient()) {
            return;
        }

        if(hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(CookingPotBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if(hasRecipe(entity)) {
            entity.removeStack(1, 1);

            entity.setStack(2, new ItemStack(ModItems.LAB,
                    entity.getStack(2).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(CookingPotBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        boolean hasRawBeefInFirstSlot = entity.getStack(1).getItem() == ModItems.LAB;

        return hasRawBeefInFirstSlot && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, ModItems.LAB);
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }
}
