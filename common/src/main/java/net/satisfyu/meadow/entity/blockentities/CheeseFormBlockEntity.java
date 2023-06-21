package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.util.ImplementedInventory;
import net.satisfyu.meadow.util.MeadowTags;
import org.jetbrains.annotations.Nullable;

public class CheeseFormBlockEntity extends BlockEntity implements BlockEntityTicker<CheeseFormBlockEntity>, NamedScreenHandlerFactory, ImplementedInventory {

    private DefaultedList<ItemStack> inventory;
    public static final int CAPACITY = 3;
    public static final int COOKING_TIME_IN_TICKS = 1800; // 90s or 3 minutes
    private static final int OUTPUT_SLOT = 0;
    private int fermentationTime = 0;
    private int totalFermentationTime;
    protected float experience;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> CheeseFormBlockEntity.this.fermentationTime;
                case 1 -> CheeseFormBlockEntity.this.totalFermentationTime;
                default -> 0;
            };
        }


        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> CheeseFormBlockEntity.this.fermentationTime = value;
                case 1 -> CheeseFormBlockEntity.this.totalFermentationTime = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public CheeseFormBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CHEESE_FORM_BLOCK_ENTITY.get(), pos, state);
        this.inventory = DefaultedList.ofSize(CAPACITY, ItemStack.EMPTY);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
        this.fermentationTime = nbt.getShort("fermentationTime");
        this.experience = nbt.getFloat("experience");

    }


    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        nbt.putFloat("experience", this.experience);
        nbt.putShort("fermentationTime", (short) this.fermentationTime);
    }


    @Override
    public void tick(World world, BlockPos pos, BlockState state, CheeseFormBlockEntity blockEntity) {
        if (world.isClient) return;
        boolean dirty = false;
        final var recipeType = world.getRecipeManager()
                .getFirstMatch(RecipeRegistry.CHEESE.get(), blockEntity, world)
                .orElse(null);
        if (canCraft(recipeType)) {
            this.fermentationTime++;

            if (this.fermentationTime == this.totalFermentationTime) {
                this.fermentationTime = 0;
                craft(recipeType);
                dirty = true;
            }
        } else {
            this.fermentationTime = 0;
        }
        if (dirty) {
            markDirty();
        }

    }

    private boolean canCraft(CheeseFormRecipe recipe) {
        if (recipe == null || recipe.getOutput().isEmpty()) {
            return false;
        } else if (areInputsEmpty()) {
            return false;
        }
        ItemStack itemStack = this.getStack(OUTPUT_SLOT);
        return itemStack.isEmpty() || itemStack == recipe.getOutput(); //TODO geht nicht
    }


    private boolean areInputsEmpty() {
        int emptyStacks = 0;
        for (int i = 1; i <= 2; i++) {
            if (this.getStack(i).isEmpty()) emptyStacks++;
        }
        return emptyStacks == 2;
    }

    private void craft(CheeseFormRecipe recipe) {
        if (!canCraft(recipe)) {
            return;
        }
        final ItemStack recipeOutput = recipe.getOutput();
        final ItemStack outputSlotStack = this.getStack(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            ItemStack output = recipeOutput.copy();
            setStack(OUTPUT_SLOT, output);
        }//TODO bucket
        for (Ingredient entry : recipe.getIngredients()) {
            ItemStack slot1Stack = this.getStack(1);
            if (entry.test(slot1Stack)) {
                if (slot1Stack.isIn(MeadowTags.MILK)) {
                    ItemStack bucket = slot1Stack.getItem() == ObjectRegistry.WOODEN_MILK_BUCKET.get() ? ObjectRegistry.WOODEN_BUCKET.get().getDefaultStack() : Items.BUCKET.getDefaultStack();
                    this.setStack(1, bucket);
                } else {
                    removeStack(1, 1);
                }
            }
            ItemStack slot2Stack = this.getStack(2);
            if (entry.test(this.getStack(2))) {
                if (slot2Stack.isIn(MeadowTags.MILK)) {
                    ItemStack bucket = slot2Stack.getItem() == ObjectRegistry.WOODEN_MILK_BUCKET.get() ? ObjectRegistry.WOODEN_BUCKET.get().getDefaultStack() : Items.BUCKET.getDefaultStack();
                    this.setStack(2, bucket);
                } else {
                    removeStack(2, 1);
                }
            }
        }
    }

    @Override
    public int size() {
        return CAPACITY;
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        final ItemStack stackInSlot = this.inventory.get(slot);
        boolean dirty = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(stackInSlot) && ItemStack.areNbtEqual(stack, stackInSlot);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }
        if (slot == 1 || slot == 2) {
            if (!dirty) {
                this.totalFermentationTime = CheeseFormBlockEntity.COOKING_TIME_IN_TICKS;
                this.fermentationTime = 0;
                markDirty();
            }
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return player.squaredDistanceTo((double) this.pos.getX() + 0.5, (double) this.pos.getY() + 0.5, (double) this.pos.getZ() + 0.5) <= 64.0;
        }
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }


    @Override
    public Text getDisplayName() {
        return Text.translatable(this.getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CheeseFormGuiHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getInventory() {
        return this.inventory;
    }
}
