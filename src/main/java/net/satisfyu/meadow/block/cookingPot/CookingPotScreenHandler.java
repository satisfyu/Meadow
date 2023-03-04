package net.satisfyu.meadow.block.cookingPot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.satisfyu.meadow.recipes.ModRecipes;
import net.satisfyu.meadow.recipes.pot.CookingPotRecipe;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;
import net.satisfyu.meadow.screenHandler.RecipeScreenHandler;
import net.satisfyu.meadow.util.GeneralUtil;

import java.util.stream.Stream;

public class CookingPotScreenHandler extends RecipeScreenHandler {
    private final World world;

    public CookingPotScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(8), new ArrayPropertyDelegate(2));
    }

    public CookingPotScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.COOKING_POT_SCREEN_HANDLER, syncId, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
        this.world = playerInventory.player.getWorld();
    }

    private void buildBlockEntityContainer(PlayerInventory playerInventory, Inventory inventory) {
        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                this.addSlot(new Slot(inventory, slot + row + (row * 2), 30 + (slot * 18), 17 + (row * 18)));
            }
        }
        this.addSlot(new Slot(inventory, 6,95, 50));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 7, 124, 26));
    }

    private void buildPlayerContainer(PlayerInventory playerInventory) {
        int m;
        int l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public boolean isBeingBurned() {
        return propertyDelegate.get(1) != 0;
    }


    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack stack;
        final Slot slot = this.getSlot(index);
        if (slot != null && slot.hasStack()) {
            final ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();
            if (GeneralUtil.isIndexInRange(index, 0, 7)) {
                if (!this.insertItem(stackInSlot, 8, 43, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(stackInSlot, stack);

            } else if (isItemIngredient(stackInSlot)) {
                if (!this.insertItem(stackInSlot, 0, 5, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (isItemContainer(stack)) {
                if (!this.insertItem(stackInSlot, 6, 7, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (stackInSlot.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            if (stackInSlot.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, stackInSlot);
        }
        return ItemStack.EMPTY;
    }


    private boolean isItemIngredient(ItemStack stack) {
        return recipeStream().anyMatch(cookingPotRecipe -> cookingPotRecipe.getIngredients().stream().anyMatch(ingredient -> ingredient.test(stack)));
    }

    private Stream<CookingPotRecipe> recipeStream() {
        return this.world.getRecipeManager().listAllOfType(ModRecipes.POT_COOKING).stream();
    }

    private boolean isItemContainer(ItemStack stack) {
        return recipeStream().anyMatch(cookingPotRecipe -> cookingPotRecipe.getContainer().isOf(stack.getItem()));
    }
    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CookingPotBlockEntity.MAX_COOKING_TIME;
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth/ totalProgress + 1;
    }


}
