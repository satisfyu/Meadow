package net.satisfyu.meadow.entity.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.block.CheeseFormBlock;
import net.satisfyu.meadow.client.screen.handler.CheeseFormGuiHandler;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.ObjectRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import net.satisfyu.meadow.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

public class CheeseFormBlockEntity extends BlockEntity implements BlockEntityTicker<CheeseFormBlockEntity>, MenuProvider, ImplementedInventory {

    private NonNullList<ItemStack> inventory;
    public static final int CAPACITY = 3;
    public static final int COOKING_TIME_IN_TICKS = 1800; // 90s or 3 minutes
    private static final int OUTPUT_SLOT = 0;
    private int fermentationTime = 0;
    protected float experience;

    private static final int[] SLOTS_FOR_SIDE = new int[]{2};
    private static final int[] SLOTS_FOR_UP = new int[]{1};
    private static final int[] SLOTS_FOR_DOWN = new int[]{0};

    private final ContainerData propertyDelegate = new ContainerData() {

        @Override
        public int get(int index) {
            if (index == 0) {
                return fermentationTime;
            }
            return 0;
        }


        @Override
        public void set(int index, int value) {
            if (index == 0) {
                fermentationTime = value;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public CheeseFormBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CHEESE_FORM_BLOCK_ENTITY.get(), pos, state);
        this.inventory = NonNullList.withSize(CAPACITY, ItemStack.EMPTY);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if(side.equals(Direction.UP)){
            return SLOTS_FOR_UP;
        } else if (side.equals(Direction.DOWN)){
            return SLOTS_FOR_DOWN;
        } else return SLOTS_FOR_SIDE;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.fermentationTime = nbt.getShort("fermentationTime");
        this.experience = nbt.getFloat("experience");

    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putFloat("experience", this.experience);
        nbt.putShort("fermentationTime", (short) this.fermentationTime);
    }


    @Override
    public void tick(Level world, BlockPos pos, BlockState state, CheeseFormBlockEntity blockEntity) {
        if (world.isClientSide) return;
        RegistryAccess manager = world.registryAccess();
        final var recipeType = world.getRecipeManager()
                .getRecipeFor(RecipeRegistry.CHEESE.get(), blockEntity, world)
                .orElse(null);
        boolean working = canCraft(recipeType, manager);
        if (working) {
            this.fermentationTime++;

            if (this.fermentationTime >= COOKING_TIME_IN_TICKS) {
                this.fermentationTime = 0;
                craft(recipeType, manager);
                setChanged();
            }
        } else {
            this.fermentationTime = 0;
        }
        boolean done = !inventory.get(OUTPUT_SLOT).isEmpty();
        if (state.getValue(CheeseFormBlock.WORKING) != working || state.getValue(CheeseFormBlock.DONE) != done) {
            world.setBlockAndUpdate(pos, state.setValue(CheeseFormBlock.WORKING, working).setValue(CheeseFormBlock.DONE, done));
        }
    }

    private boolean canCraft(RecipeHolder<CheeseFormRecipe> recipe, RegistryAccess manager) {
        if (recipe == null || recipe.value().getResultItem(manager).isEmpty()) {
            return false;
        } else if (areInputsEmpty()) {
            return false;
        }
        ItemStack itemStack = this.getItem(OUTPUT_SLOT);
        return itemStack.isEmpty() || itemStack == recipe.value().getResultItem(manager);
    }


    private boolean areInputsEmpty() {
        int emptyStacks = 0;
        for (int i = 1; i <= 2; i++) {
            if (this.getItem(i).isEmpty()) emptyStacks++;
        }
        return emptyStacks == 2;
    }

    private void craft(RecipeHolder<CheeseFormRecipe> recipeHolder, RegistryAccess manager) {
        if (!canCraft(recipeHolder, manager)) {
            return;
        }
        CheeseFormRecipe recipe = recipeHolder.value();
        final ItemStack recipeOutput = recipe.getResultItem(manager);
        final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            ItemStack output = recipeOutput.copy();
            setItem(OUTPUT_SLOT, output);
        }//TODO bucket
        for (Ingredient entry : recipe.getIngredients()) {
            ItemStack slot1Stack = this.getItem(1);
            if (entry.test(slot1Stack)) {
                if (slot1Stack.is(TagRegistry.MILK)) {
                    ItemStack bucket = slot1Stack.getItem() == ObjectRegistry.WOODEN_MILK_BUCKET.get() ? ObjectRegistry.WOODEN_BUCKET.get().getDefaultInstance() : Items.BUCKET.getDefaultInstance();
                    this.setItem(1, bucket);
                } else {
                    removeItem(1, 1);
                }
            }
            ItemStack slot2Stack = this.getItem(2);
            if (entry.test(this.getItem(2))) {
                if (slot2Stack.is(TagRegistry.MILK)) {
                    ItemStack bucket = slot2Stack.getItem() == ObjectRegistry.WOODEN_MILK_BUCKET.get() ? ObjectRegistry.WOODEN_BUCKET.get().getDefaultInstance() : Items.BUCKET.getDefaultInstance();
                    this.setItem(2, bucket);
                } else {
                    removeItem(2, 1);
                }
            }
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        final ItemStack stackInSlot = this.inventory.get(slot);
        boolean dirty = !stack.isEmpty() && ItemStack.isSameItem(stack, stackInSlot) && ItemStack.matches(stack, stackInSlot);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        if (slot == 1 || slot == 2) {
            if (!dirty) {
                this.fermentationTime = 0;
                setChanged();
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double) this.worldPosition.getX() + 0.5, (double) this.worldPosition.getY() + 0.5, (double) this.worldPosition.getZ() + 0.5) <= 64.0;
        }
    }


    @Override
    public Component getDisplayName() {
        return Component.nullToEmpty("");
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player player) {
        return new CheeseFormGuiHandler(syncId, inv, this, this.propertyDelegate);
    }
}
