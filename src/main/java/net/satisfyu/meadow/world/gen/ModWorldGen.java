package net.satisfyu.meadow.world.gen;

public class ModWorldGen {

    public static void generateWorldGen() {
        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlowers();
        ModOreGeneration.generateOres();
    }
}
