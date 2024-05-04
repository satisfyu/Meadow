package net.satisfyu.meadow.client.gui.handler;

import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import de.cristelknight.doapi.client.recipebook.handler.AbstractRecipeBookGUIScreenHandler;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.satisfyu.meadow.client.recipebook.group.CookingCauldronRecipeBookGroup;
import net.satisfyu.meadow.block.entity.CookingCauldronBlockEntity;
import net.satisfyu.meadow.recipes.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.RecipeRegistry;
import net.satisfyu.meadow.registry.ScreenHandlerRegistry;

import java.util.List;
import java.util.stream.Stream;

public class CookingCauldronGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public CookingCauldronGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(7), new SimpleContainerData(2));
    }

    public CookingCauldronGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenHandlerRegistry.COOKING_CAULDRON_SCREEN_HANDLER.get(), syncId, 6, playerInventory, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(Inventory playerInventory, Container inventory) {
        this.addSlot(new FurnaceResultSlot(playerInventory.player, inventory, 0, 124, 26));
        for (int row = 0; row < 2; row++) {
            for (int slot = 0; slot < 3; slot++) {
                this.addSlot(new Slot(inventory, 1 + slot + row + (row * 2), 30 + (slot * 18), 17 + (row * 18)));
            }
        }
    }

    private void buildPlayerContainer(Inventory playerInventory) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean isBeingBurned() {
        return propertyDelegate.get(1) != 0;
    }

    @SuppressWarnings("unused")
    private boolean isItemIngredient(ItemStack stack) {
        return recipeStream().anyMatch(cookingPotRecipe -> cookingPotRecipe.getIngredients().stream().anyMatch(ingredient -> ingredient.test(stack)));
    }

    private Stream<CookingCauldronRecipe> recipeStream() {
        return this.world.getRecipeManager().getAllRecipesFor(RecipeRegistry.COOKING.get()).stream();
    }

    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CookingCauldronBlockEntity.getMaxCookingTime();
        if (progress == 0) {
            return 0;
        }
        return progress * arrowWidth/ totalProgress + 1;
    }


    @Override
    public List<IRecipeBookGroup> getGroups() {
        return CookingCauldronRecipeBookGroup.CAULDRON_GROUPS;
    }

    @Override
    public boolean hasIngredient(Recipe<?> recipe) {
        if (recipe instanceof CookingCauldronRecipe potRecipe) {
            for (Ingredient ingredient : potRecipe.getIngredients()) {
                boolean found = false;
                for (Slot slot : this.slots) {
                    if (ingredient.test(slot.getItem())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int getCraftingSlotCount() {
        return 6;
    }
}