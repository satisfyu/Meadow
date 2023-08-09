package net.satisfyu.meadow.entity.blockentities;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfyu.meadow.block.CookingCauldronBlock;
import net.satisfyu.meadow.client.screen.handler.CookingCauldronGuiHandler;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import net.satisfyu.meadow.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

public class CookingCauldronBlockEntity extends BlockEntity implements ImplementedInventory, MenuProvider {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
    private static final int MAX_CAPACITY = 7;
    public static final int MAX_COOKING_TIME = 600; // Time in ticks (30s)
    private int cookingTime;
    public static final int OUTPUT_SLOT = 0;
    private static final int INGREDIENTS_AREA = 6;
    private static final int[] SLOTS_FOR_REST = new int[]{1, 2, 3, 4, 5, 6};
    private static final int[] SLOTS_FOR_DOWN = new int[]{0};
    private boolean isBeingBurned;

    private final ContainerData delegate;

    public CookingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COOKING_CAULDRON.get(), pos, state);
        this.delegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> cookingTime;
                    case 1 -> isBeingBurned ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> cookingTime = value;
                    case 1 -> isBeingBurned = value != 0;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if(side.equals(Direction.DOWN)){
            return SLOTS_FOR_DOWN;
        }
        return SLOTS_FOR_REST;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, this.inventory);
        this.cookingTime = nbt.getInt("CookingTime");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.inventory);
        nbt.putInt("CookingTime", this.cookingTime);
    }

    public boolean isBeingBurned() {
        if (getLevel() == null)
            throw new NullPointerException("Null world invoked");
        if(this.getBlockState().getValue(CookingCauldronBlock.HANGING)) return true;
        final BlockState belowState = this.getLevel().getBlockState(getBlockPos().below());
        final var optionalList = BuiltInRegistries.BLOCK.getTag(TagRegistry.ALLOWS_COOKING);
        final var entryList = optionalList.orElse(null);
        if (entryList == null) {
            return false;
        } else return entryList.contains(belowState.getBlock().builtInRegistryHolder());
    }

    private boolean canCraft(CookingCauldronRecipe recipe) {
        if (recipe == null || recipe.getResultItem().isEmpty()) {
            return false;
        } else if (this.getItem(OUTPUT_SLOT).isEmpty()) {
            return true;
        } else {
            final ItemStack recipeOutput = recipe.getResultItem();
            final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
            final int outputSlotCount = outputSlotStack.getCount();

            if (!ItemStack.isSameItem(outputSlotStack, recipeOutput)) { //no damage same?
                return false;
            } else if (outputSlotCount < this.getMaxStackSize() && outputSlotCount < outputSlotStack.getMaxStackSize()) {
                return true;
            } else {
                return outputSlotCount < recipeOutput.getMaxStackSize();
            }
        }
    }

    private void craft(CookingCauldronRecipe recipe) {
        if (!canCraft(recipe)) {
            return;
        }
        final ItemStack recipeOutput = recipe.assemble();
        final ItemStack outputSlotStack = this.getItem(OUTPUT_SLOT);
        if (outputSlotStack.isEmpty()) {
            setItem(OUTPUT_SLOT, recipeOutput);
        } else if (outputSlotStack.is(recipeOutput.getItem())) {
            outputSlotStack.grow(recipeOutput.getCount());
        }
        final NonNullList<Ingredient> ingredients = recipe.getIngredients();
        boolean[] slotUsed = new boolean[INGREDIENTS_AREA];
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            ItemStack bestSlot = getItem(i + 1);
            if (ingredient.test(bestSlot) && !slotUsed[i]) {
                slotUsed[i] = true;
                ItemStack remainderStack = getRemainderItem(bestSlot);
                bestSlot.shrink(1);
                if (!remainderStack.isEmpty()) {
                    setItem(i + 1, remainderStack);
                }
            } else {
                for (int j = 1; j <= INGREDIENTS_AREA; j++) {
                    ItemStack stack = getItem(j);
                    if (ingredient.test(stack) && !slotUsed[j]) {
                        slotUsed[j] = true;
                        ItemStack remainderStack = getRemainderItem(stack);
                        stack.shrink(1);
                        if (!remainderStack.isEmpty()) {
                            setItem(j, remainderStack);
                        }
                    }
                }
            }
        }
    }

    private ItemStack getRemainderItem(ItemStack stack) {
        if (stack.getItem().hasCraftingRemainingItem()) {
            return new ItemStack(stack.getItem().getCraftingRemainingItem());
        }
        return ItemStack.EMPTY;
    }



    public void tick(Level world, BlockPos pos, BlockState state, CookingCauldronBlockEntity blockEntity) {
        if (world.isClientSide()) {
            return;
        }
        this.isBeingBurned = isBeingBurned();
        if (!this.isBeingBurned) {
            if (state.getValue(CookingCauldronBlock.LIT))
                world.setBlock(pos, state.setValue(CookingCauldronBlock.LIT, false), Block.UPDATE_ALL);
            return;
        }
        CookingCauldronRecipe recipe = world.getRecipeManager().getRecipeFor(RecipeRegistry.COOKING.get(), this, world).orElse(null);

        boolean canCraft = canCraft(recipe);
        if (canCraft) {
            this.cookingTime++;
            if (this.cookingTime >= MAX_COOKING_TIME) {
                this.cookingTime = 0;
                craft(recipe);
            }
        } else if (!canCraft(recipe)) {
            this.cookingTime = 0;
        }
        if (canCraft) {
            world.setBlock(pos, state.setValue(CookingCauldronBlock.COOKING, true).setValue(CookingCauldronBlock.LIT, true), Block.UPDATE_ALL);
        } else if (state.getValue(CookingCauldronBlock.COOKING)) {
            world.setBlock(pos, state.setValue(CookingCauldronBlock.COOKING, false).setValue(CookingCauldronBlock.LIT, true), Block.UPDATE_ALL);
        } else if (state.getValue(CookingCauldronBlock.LIT) != isBeingBurned) {
            world.setBlock(pos, state.setValue(CookingCauldronBlock.LIT, isBeingBurned), Block.UPDATE_ALL);
        }
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
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
        return new CookingCauldronGuiHandler(syncId, inv, this, this.delegate);
    }
}