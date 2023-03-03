package net.satisfyu.meadow.compat.rei.cooking;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.block.ModBlocks;
import net.satisfyu.meadow.block.cookingCauldron.CookingCauldronBlockEntity;

import java.util.List;

public class CookingCauldronCategory implements DisplayCategory<CookingCauldronDisplay> {


    public static final CategoryIdentifier<CookingCauldronDisplay> COOKING_CAULDRON_DISPLAY = CategoryIdentifier.of(Meadow.MOD_ID, "cooking_cauldron_display");

    @Override
    public CategoryIdentifier<CookingCauldronDisplay> getCategoryIdentifier() {
        return COOKING_CAULDRON_DISPLAY;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("rei.meadow.cooking_cauldron_category");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.COOKING_CAULDRON);
    }

    @Override
    public int getDisplayWidth(CookingCauldronDisplay display) {
        return 150;
    }

    @Override
    public int getDisplayHeight() {
        return 99;
    }

    @Override
    public List<Widget> setupDisplay(CookingCauldronDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 55, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 54, startPoint.y - 1)).animationDurationTicks(CookingCauldronBlockEntity.getTimeToCook()));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 90, startPoint.y)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 90, startPoint.y)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        for(int i = 0; i < 3; i++){
            int x = i * 18;
            int y = -4;
            x-=8;
            if(i >= display.getInputEntries().size() - 1) widgets.add(Widgets.createSlotBackground(new Point(startPoint.x + x, startPoint.y + y)));
            else widgets.add(Widgets.createSlot(new Point(startPoint.x + x, startPoint.y + y)).entries(display.getInputEntries().get(i + 1)).markInput());
        }
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 23)).entries(display.getInputEntries().get(0)).markInput());
        return widgets;
    }
}
