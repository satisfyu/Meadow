package net.satisfy.meadow.client.recipebook;

import de.cristelknight.doapi.client.recipebook.screen.widgets.PrivateRecipeBookWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.satisfy.meadow.registry.RecipeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CheeseFormRecipeBook extends PrivateRecipeBookWidget {
    private static final Component TOGGLE_COOKABLE_TEXT;


    @Override
    protected RecipeType<? extends Recipe<RecipeInput>> getRecipeType() {
        return RecipeRegistry.CHEESE.get();
    }

    @Override
    public void insertRecipe(Recipe<?> recipe) {
        int usedInputSlots = 1;
        for (Ingredient ingredient : recipe.getIngredients()) {
            int slotIndex = 0;
            for (Slot slot : screenHandler.slots) {
                ItemStack itemStack = slot.getItem();

                if (ingredient.test(itemStack) && usedInputSlots < 3) {
                    Minecraft.getInstance().gameMode.handleInventoryMouseClick(screenHandler.containerId, slotIndex, 0, ClickType.PICKUP, Minecraft.getInstance().player);
                    Minecraft.getInstance().gameMode.handleInventoryMouseClick(screenHandler.containerId, usedInputSlots, 0, ClickType.PICKUP, Minecraft.getInstance().player);
                    ++usedInputSlots;
                    break;
                }
                ++slotIndex;
            }
        }
    }

    @Override
    public void showGhostRecipe(Recipe<?> recipe, List<Slot> slots, RegistryAccess dynamicRegistryManager) {
        this.ghostSlots.addSlot(recipe.getResultItem(dynamicRegistryManager), slots.get(0).x, slots.get(0).y);
        int slot = 1;
        for (Ingredient ingredient : recipe.getIngredients()) {
            ItemStack[] inputStacks = ingredient.getItems();
            //if (inputStacks.length == 0) continue;
            ItemStack inputStack = inputStacks[RandomSource.create().nextIntBetweenInclusive(0, inputStacks.length - 1)];
            this.ghostSlots.addSlot(inputStack, slots.get(slot).x, slots.get(slot++).y);
        }
    }

    @Override
    public void slotClicked(@Nullable Slot slot) {
        super.slotClicked(slot);
        if (slot != null && slot.index < this.screenHandler.getCraftingSlotCount()) {
            this.ghostSlots.reset();
        }
    }

    @Override
    protected Component getToggleCraftableButtonText() {
        return TOGGLE_COOKABLE_TEXT;
    }

    static {
        TOGGLE_COOKABLE_TEXT = Component.translatable("gui.meadow.recipebook.toggleRecipes.cheeseable");
    }

    @Override
    public void setFocused(boolean focused) {

    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public void recipesShown(List<RecipeHolder<?>> list) {
    }
}