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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.satisfyu.meadow.client.recipebook.group.CheeseFormRecipeBookGroup;
import net.satisfyu.meadow.block.entity.CheeseFormBlockEntity;
import net.satisfyu.meadow.recipes.CheeseFormRecipe;
import net.satisfyu.meadow.registry.ScreenHandlerRegistry;

import java.util.List;

public class CheeseFormGuiHandler extends AbstractRecipeBookGUIScreenHandler {

    public CheeseFormGuiHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(3), new SimpleContainerData(1));
    }

    public CheeseFormGuiHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData propertyDelegate) {
        super(ScreenHandlerRegistry.CHEESE_FORM_SCREEN_HANDLER.get(), syncId, 2, playerInventory, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(Inventory playerInventory, Container inventory) {
        this.addSlot(new FurnaceResultSlot(playerInventory.player, inventory, 0, 123, 34));
        this.addSlot(new Slot(inventory, 1, 33, 33));
        this.addSlot(new Slot(inventory, 2, 51, 33));
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

    public int getScaledXProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CheeseFormBlockEntity.COOKING_TIME_IN_TICKS;
        if (progress == 0) {
            return 0;
        }
        return progress * 24 / totalProgress + 1;
    }

    public int getScaledYProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CheeseFormBlockEntity.COOKING_TIME_IN_TICKS;
        if (progress == 0) {
            return 0;
        }
        return progress * 25 / totalProgress + 1;
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
        return 2;
    }
}
