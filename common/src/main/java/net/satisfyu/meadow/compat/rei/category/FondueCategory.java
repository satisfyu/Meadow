package net.satisfyu.meadow.compat.rei.category;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.satisfyu.meadow.Meadow;
import net.satisfyu.meadow.compat.rei.display.FondueDisplay;
import net.satisfyu.meadow.entity.blockentities.FondueBlockEntity;
import net.satisfyu.meadow.registry.ObjectRegistry;

import java.util.List;

public class FondueCategory implements DisplayCategory<FondueDisplay> {


    public static final CategoryIdentifier<FondueDisplay> FONDUE_DISPLAY = CategoryIdentifier.of(Meadow.MOD_ID, "fondue_display");

    @Override
    public CategoryIdentifier<FondueDisplay> getCategoryIdentifier() {
        return FONDUE_DISPLAY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("rei.meadow.fondue_category");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ObjectRegistry.FONDUE.get());
    }

    @Override
    public int getDisplayWidth(FondueDisplay display) {
        return 104;
    }

    @Override
    public int getDisplayHeight() {
        return 60;
    }

    @Override
    public List<Widget> setupDisplay(FondueDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getX() + 15, bounds.getCenterY());
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));

        widgets.add(Widgets.createArrow(new Point(startPoint.x + 23, startPoint.y - 12))
                .animationDurationTicks(FondueBlockEntity.MAX_PROGRESS));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 55, startPoint.y - 7)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 55, startPoint.y - 7)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

        if (display.getInputEntries().size() < 1)
            widgets.add(Widgets.createSlotBackground(new Point(startPoint.x, startPoint.y)));
        else
            widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y)).entries(display.getInputEntries().get(1)).markInput());

        if (display.getInputEntries().size() < 2)
            widgets.add(Widgets.createSlotBackground(new Point(startPoint.x, startPoint.y - 20)));
        else
            widgets.add(Widgets.createSlot(new Point(startPoint.x, startPoint.y - 20)).entries(display.getInputEntries().get(0)).markInput());

        widgets.add(Widgets.createLabel(new Point(bounds.x + bounds.width - 5, bounds.y + bounds.height - 12),
                Component.translatable("rei.meadow.fondue_category.amount", 10)).noShadow().rightAligned().color(0xFF404040, 0xFFBBBBBB));

        return widgets;
    }
}