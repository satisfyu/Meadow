package net.satisfyu.meadow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.block.ModBlocks;

import java.util.function.BiConsumer;

public class LootTableGen extends SimpleFabricLootTableProvider {
    public LootTableGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> i) {

        generateLootTable(i, "limestone", ModBlocks.LIMESTONE);

    }

    public void generateLootTable(BiConsumer<Identifier, LootTable.Builder> i, String name, ItemConvertible item) {
        i.accept(new Identifier("meadow", "blocks/" + name), BlockLootTableGenerator.drops(item));
    }
}