package net.satisfyu.meadow.block.cheeseForm;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlockEntity;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;
import net.satisfyu.meadow.screenHandler.RecipeScreenHandler;

public class CheeseFormScreenHandler extends RecipeScreenHandler {

    public CheeseFormScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory,  new SimpleInventory(2), new ArrayPropertyDelegate(1));
    }

    public CheeseFormScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.CHEESE_FORM_SCREEN_HANDLER, syncId, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(PlayerInventory playerInventory, Inventory inventory) {
        this.addSlot(new Slot(inventory, 0, 29 , 38));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 1, 104, 38));
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

    public int getScaledXProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CookingCauldronBlockEntity.MAX_COOKING_TIME;
        if (progress == 0) {
            return 0;
        }
        return progress * 22 / totalProgress + 1;
    }

    public int getScaledYProgress() {
        final int progress = this.propertyDelegate.get(0);
        final int totalProgress = CookingCauldronBlockEntity.MAX_COOKING_TIME;
        if (progress == 0) {
            return 0;
        }
        return progress * 24 / totalProgress + 1;
    }

}
