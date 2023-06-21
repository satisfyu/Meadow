package net.satisfyu.meadow.client.screen.handler;

import de.cristelknight.doapi.client.recipebook.IRecipeBookGroup;
import de.cristelknight.doapi.client.recipebook.handler.AbstractRecipeBookGUIScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.meadow.client.recipebook.CheeseFormRecipeBookGroup;
import net.satisfyu.meadow.entity.blockentities.CheeseFormBlockEntity;
import net.satisfyu.meadow.recipes.cheese.CheeseFormRecipe;
import net.satisfyu.meadow.registry.ScreenHandlerRegistry;

import java.util.List;

public class CheeseFormGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public CheeseFormGuiHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(2));
    }

    public CheeseFormGuiHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlerRegistry.CHEESE_FORM_SCREEN_HANDLER.get(), syncId, 2, playerInventory, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(PlayerInventory playerInventory, Inventory inventory) {
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 0, 123, 34));
        this.addSlot(new Slot(inventory, 1, 33, 33));
        this.addSlot(new Slot(inventory, 2, 51, 33));
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

    public int getScaledXProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CheeseFormBlockEntity.COOKING_TIME_IN_TICKS;
        if (progress == 0) {
            return 0;
        }
        return progress * 22 / totalProgress + 1;
    }

    public int getScaledYProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CheeseFormBlockEntity.COOKING_TIME_IN_TICKS;
        if (progress == 0) {
            return 0;
        }
        return progress * 24 / totalProgress + 1;
    }

    @Override
    public List<IRecipeBookGroup> getGroups() {
        return CheeseFormRecipeBookGroup.CHEESE_GROUPS;
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
        return 2;
    }
}
