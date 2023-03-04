package net.satisfyu.meadow.block.cookingCauldron;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;
import net.satisfyu.meadow.screenHandler.RecipeScreenHandler;

public class CookingCauldronScreenHandler extends RecipeScreenHandler {

    public CookingCauldronScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory, new SimpleInventory(4), new ArrayPropertyDelegate(2));
    }

    public CookingCauldronScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.COOKING_CAULDRON_SCREEN_HANDLER, syncId, inventory, propertyDelegate);
        buildBlockEntityContainer(playerInventory, inventory);
        buildPlayerContainer(playerInventory);
    }

    private void buildBlockEntityContainer(PlayerInventory playerInventory, Inventory inventory) {
        this.addSlot(new Slot(inventory, 0, 30, 26));
        this.addSlot(new Slot(inventory, 1, 48, 26));
        this.addSlot(new Slot(inventory, 2, 66, 26));

        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 3, 120, 25));
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
        return this.inventory.canPlayerUse(player);
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

}
