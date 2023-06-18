package net.satisfyu.meadow.client.gui.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.meadow.client.recipebook.IRecipeBookGroup;
import net.satisfyu.meadow.client.recipebook.custom.CheeseFormRecipeBookGroup;
import net.satisfyu.meadow.client.recipebook.custom.CookingCauldronRecipeBookGroup;
import net.satisfyu.meadow.entity.blockentities.CookingCauldronBlockEntity;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.registry.ScreenHandlerRegistry;
import net.satisfyu.meadow.client.gui.handler.screenHandler.RecipeScreenHandler;

import java.util.List;

public class CookingCauldronGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public CookingCauldronGuiHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory, new SimpleInventory(7), new ArrayPropertyDelegate(2));
    }

    public CookingCauldronGuiHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlerRegistry.COOKING_CAULDRON_SCREEN_HANDLER.get(), syncId, 6, playerInventory, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(PlayerInventory playerInventory, Inventory inventory) {
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 0, 124, 26));

        this.addSlot(new Slot(inventory, 1, 30, 17));
        this.addSlot(new Slot(inventory, 2, 48, 17));
        this.addSlot(new Slot(inventory, 3, 66, 17));
        this.addSlot(new Slot(inventory, 4, 30, 35));
        this.addSlot(new Slot(inventory, 5, 48, 35));
        this.addSlot(new Slot(inventory, 6, 66, 35));

    }

    private void buildPlayerContainer(PlayerInventory playerInventory) {
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

    public boolean getIsCooking(){
        return propertyDelegate.get(1) != 0;
    }

    public int getScaledProgress(int arrowWidth) {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CookingCauldronBlockEntity.MAX_COOKING_TIME;
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
        if (recipe instanceof CheeseFormRecipe cheeseFormRecipe) {
            for (Ingredient ingredient : cheeseFormRecipe.getIngredients()) {
                boolean found = false;
                for (Slot slot : this.slots) {
                    if (ingredient.test(slot.getStack())) {
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
