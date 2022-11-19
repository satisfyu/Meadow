package net.satisfyu.meadow.block.cheeseForm;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.satisfyu.meadow.screenHandler.ModScreenHandlers;

public class CheeseFormScreenHandler extends ScreenHandler {
    private final PropertyDelegate propertyDelegate;

    private final Inventory inventory;

    public CheeseFormScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory, new ArrayPropertyDelegate(1),  new SimpleInventory(2));
    }

    public CheeseFormScreenHandler(int syncId, PlayerInventory playerInventory, PropertyDelegate propertyDelegate, Inventory inv) {
        super(ModScreenHandlers.CHEESE_FORM_SCREEN_HANDLER, syncId);
        this.propertyDelegate = propertyDelegate;
        CheeseFormScreenHandler.checkSize(inv, 2);
        this.inventory = inv;
        inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(inv, 0, 30, 40));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inv, 1, 130, 40));

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
        this.addProperties(propertyDelegate);
    }



    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public int getSyncedNumber(){
        return propertyDelegate.get(0);
    }

    public boolean getIsCooking(){
        return propertyDelegate.get(1) != 0;
    }


    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()/* - 1*/) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }


}
