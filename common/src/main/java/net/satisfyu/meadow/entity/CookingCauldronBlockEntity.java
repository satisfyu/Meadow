package net.satisfyu.meadow.entity;

import de.cristelknight.doapi.common.world.ImplementedInventory;
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
import net.satisfyu.meadow.client.gui.handler.CookingCauldronGuiHandler;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.BlockEntityRegistry;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.registry.TagRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@SuppressWarnings("deprecation, unused")
public class CookingCauldronBlockEntity extends BlockEntity implements ImplementedInventory, MenuProvider {
    private static final int MAX_CAPACITY = 7;
    private static final int MAX_COOKING_TIME = 200;
    private static final int OUTPUT_SLOT = 0;
    private static final int INGREDIENTS_AREA = 6;
    private static final int[] SLOTS_FOR_REST = {1, 2, 3, 4, 5, 6};
    private static final int[] SLOTS_FOR_DOWN = {0};
    public static int getMaxCookingTime() {
        return MAX_COOKING_TIME;
    }

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(MAX_CAPACITY, ItemStack.EMPTY);
    private int cookingTime;
    private boolean isBeingBurned;
    private final ContainerData delegate = new ContainerData() {
        public int get(int index) {
            if (index == 0) return cookingTime;
            else if (index == 1) return isBeingBurned ? 1 : 0;
            return 0;
        }

        public void set(int index, int value) {
            if (index == 0) cookingTime = value;
            else if (index == 1) isBeingBurned = value != 0;
        }

        public int getCount() {
            return 2;
        }
    };

    public CookingCauldronBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COOKING_CAULDRON.get(), pos, state);
    }

    public int @NotNull [] getSlotsForFace(Direction side) {
        return side == Direction.DOWN ? SLOTS_FOR_DOWN : SLOTS_FOR_REST;
    }

    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, inventory);
        cookingTime = nbt.getInt("CookingTime");
    }

    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, inventory);
        nbt.putInt("CookingTime", cookingTime);
    }

    public boolean isBeingBurned() {
        if (getLevel() == null) throw new NullPointerException("Null world invoked");
        if (getBlockState().getValue(CookingCauldronBlock.HANGING)) return true;
        var optionalList = BuiltInRegistries.BLOCK.getTag(TagRegistry.ALLOWS_COOKING);
        var entryList = optionalList.orElse(null);
        return entryList != null && entryList.contains(getLevel().getBlockState(getBlockPos().below()).getBlock().builtInRegistryHolder());
    }

    private boolean canCraft(CookingCauldronRecipe recipe) {
        if (recipe == null || recipe.getResultItem().isEmpty()) return false;
        ItemStack outputSlotStack = getItem(OUTPUT_SLOT);
        return outputSlotStack.isEmpty() || (ItemStack.isSameItem(outputSlotStack, recipe.getResultItem()) && outputSlotStack.getCount() < outputSlotStack.getMaxStackSize());
    }

    private void craft(CookingCauldronRecipe recipe) {
        if (!canCraft(recipe)) return;

        ItemStack recipeOutput = recipe.assemble();
        ItemStack outputSlotStack = getItem(OUTPUT_SLOT);
        outputSlotStack.grow(recipeOutput.getCount());

        for (Ingredient ingredient : recipe.getIngredients()) {
            for (int j = 1; j <= INGREDIENTS_AREA; j++) {
                ItemStack stack = getItem(j);
                if (ingredient.test(stack)) {
                    stack.shrink(1);
                    ItemStack remainderStack = stack.getItem().hasCraftingRemainingItem() ? new ItemStack(Objects.requireNonNull(stack.getItem().getCraftingRemainingItem())) : ItemStack.EMPTY;
                    if (!remainderStack.isEmpty()) {
                        setItem(j, remainderStack);
                    }
                    break;
                }
            }
        }
    }


    public void tick(Level world, BlockPos pos, BlockState state) {
        if (world.isClientSide()) return;

        isBeingBurned = isBeingBurned();
        if (!isBeingBurned && state.getValue(CookingCauldronBlock.LIT)) {
            world.setBlock(pos, state.setValue(CookingCauldronBlock.LIT, false), Block.UPDATE_ALL);
            return;
        }

        CookingCauldronRecipe recipe = world.getRecipeManager().getRecipeFor(RecipeRegistry.COOKING.get(), this, world).orElse(null);
        if (canCraft(recipe)) {
            cookingTime++;
            if (cookingTime >= MAX_COOKING_TIME) {
                cookingTime = 0;
                craft(recipe);
            }
            world.setBlock(pos, state.setValue(CookingCauldronBlock.COOKING, true).setValue(CookingCauldronBlock.LIT, true), Block.UPDATE_ALL);
        } else {
            cookingTime = 0;
            if (state.getValue(CookingCauldronBlock.COOKING)) {
                world.setBlock(pos, state.setValue(CookingCauldronBlock.COOKING, false).setValue(CookingCauldronBlock.LIT, true), Block.UPDATE_ALL);
            }
        }
    }

    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public boolean stillValid(Player player) {
        return player.distanceToSqr((double) worldPosition.getX() + 0.5, (double) worldPosition.getY() + 0.5, (double) worldPosition.getZ() + 0.5) <= 64.0;
    }

    public @NotNull Component getDisplayName() {
        return Component.nullToEmpty("");
    }

    public @Nullable AbstractContainerMenu createMenu(int syncId, @NotNull Inventory inv, @NotNull Player player) {
        return new CookingCauldronGuiHandler(syncId, inv, this, delegate);
    }
}
