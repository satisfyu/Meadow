package net.satisfyu.meadow.client.recipebook;

import de.cristelknight.doapi.client.recipebook.screen.widgets.PrivateRecipeBookWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.recipes.cooking.CookingCauldronRecipe;
import net.satisfyu.meadow.registry.RecipeRegistry;

import java.util.List;

public class CookingCauldronRecipeBook extends PrivateRecipeBookWidget {
    private static final Component TOGGLE_COOKABLE_TEXT;

    public CookingCauldronRecipeBook() {
    }

    @Override
    protected RecipeType<? extends Recipe<Container>> getRecipeType() {
        return RecipeRegistry.COOKING.get();
    }

    @Override
    public void insertRecipe(Recipe<?> recipe) {
        int usedInputSlots = 1;

        Meadow.LOGGER.error(recipe.getIngredients().size());

        for (Ingredient ingredient : recipe.getIngredients()) {
            int slotIndex = 0;
            for (Slot slot : screenHandler.slots) {
                ItemStack itemStack = slot.getItem();

                if (ingredient.test(itemStack) && usedInputSlots < 7) {
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
        if (recipe instanceof CookingCauldronRecipe potRecipe) {
            this.ghostSlots.addSlot(potRecipe.getResultItem(dynamicRegistryManager), slots.get(0).x, slots.get(0).y);

            int slot = 1;
            for (Ingredient ingredient : potRecipe.getIngredients()) {
                ItemStack[] inputStacks = ingredient.getItems();
                if (inputStacks.length == 0) continue;
                ItemStack inputStack = inputStacks[RandomSource.create().nextIntBetweenInclusive(0, inputStacks.length - 1)];
                this.ghostSlots.addSlot(inputStack, slots.get(slot).x, slots.get(slot++).y);
            }
        }
    }

    @Override
    protected Component getToggleCraftableButtonText() {
        return TOGGLE_COOKABLE_TEXT;
    }

    static {
        TOGGLE_COOKABLE_TEXT = Component.translatable("gui.meadow.recipebook.toggleRecipes.cookable");
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