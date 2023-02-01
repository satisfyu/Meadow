package net.satisfyu.meadow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import net.satisfyu.meadow.Meadow;

import java.util.function.BiConsumer;

public class LootTableGenerator extends SimpleFabricLootTableProvider {
    public LootTableGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {

        identifierBuilderBiConsumer.accept(new Identifier(Meadow.MOD_ID, "blocks/alpine_birch_log"),
                BlockLootTableGenerator.drops(Blocks.BIRCH_LOG));
        identifierBuilderBiConsumer.accept(new Identifier(Meadow.MOD_ID, "blocks/alpine_oak_log"),
                BlockLootTableGenerator.drops(Blocks.OAK_LOG));

    }
}