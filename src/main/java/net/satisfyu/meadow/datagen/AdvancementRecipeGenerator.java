package net.satisfyu.meadow.datagen;

import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdvancementRecipeGenerator {

    public static String FOLDER = "/Users/marco/Desktop/Neuer Ordner/";

    public static void main(String[] args) {
        List<String> putRecipesHere = List.of("cobbled_limestone/chiseled_limestone_bricks",
                "cobbled_limestone/limestone_bricks",
                "cobbled_limestone/limestone_bricks_wall",
                "cobbled_limestone/limestone_bricks_stairs",
                "cobbled_limestone/limestone_slab",
                "cobbled_limestone/limestone_brick_slab",
                "cobbled_limestone/limestone_brick_stairs",

                "pine_log/pine_wood",
                "pine_log/pine_planks",
                "pine_log/pine_boat",
                "pine_log/pine_button",
                "pine_log/pine_door",
                "pine_log/pine_fence",
                "pine_log/pine_fence_gate",
                "pine_log/pine_pressure_plate",
                "pine_log/pine_sign",
                "pine_log/pine_slab",
                "pine_log/pine_stairs",
                "pine_log/pine_trapdoor",
                "pine_log/stick",
                "pine_wood/pine_planks",
                "pine_planks/pine_boat",
                "pine_planks/pine_button",
                "pine_planks/pine_door",
                "pine_planks/pine_fence",
                "pine_planks/pine_fence_gate",
                "pine_planks/pine_pressure_plate",
                "pine_planks/pine_sign",
                "pine_planks/pine_slab",
                "pine_planks/pine_stairs",
                "pine_planks/pine_trapdoor",
                "pine_planks/stick",


                "iron_axe/axe_woodstack",
                "pine_log/axe_woodstack",

                "pine_slab/bench",
                "stick/bench",

                "pine_log/big_woodstack",

                "small_fir/green_dye",

                "bowl_milk/bowl_cornflakes",
                "wheat/bowl_cornflakes",

                "bowl_milk/bowl_honey",
                "honey_bottle/bowl_honey",

                "bowl/bowl_milk",
                "milk_bucket/bowl_milk",

                "bowl_milk/bowl_sweetberries",
                "sweet_berries/bowl_sweetberries",

                "buffalo_cheese_mass/bowl_mozzarella",

                "buffalo_milk/buffalo_cheese_mass_bucket",
                "lab/buffalo_cheese_mass_bucket",
                "alpine_salt/buffalo_cheese_mass_bucket",

                "redstone_lamp/camera",
                "stick/camera",

                "iron_ingot/can",

                "stick/chair",
                "pine_slab/chair",

                "bread/cheese_bread",
                "cheese/cheese_bread",

                "pine_slab/cheese_form",

                "stick/cheese_stick",
                "cheese/cheese_stick",

                "cheese/cheese_tart",
                "egg/cheese_tart",
                "milk_bucket/cheese_tart",
                "wheat/cheese_tart",

                "cream_cheese/cheesecake",
                "egg/cheesecake",
                "milk_bucket/cheesecake",
                "wheat/cheesecake",

                "limestone/chiseled_limestone_bricks",

                "iron_ingot/climbing_rope_topmount",
                "lead/climbing_rope_topmount",

                "iron_ingot/cooking_cauldron",

                "cow_cheese_mass/cheese_block",
                "cow_milk/cow_cheese_mass_bucket",
                "lab/cow_cheese_mass_bucket",
                "alpine_salt/cow_cheese_mass_bucket",
                "cow_milk/wooden_cow_cheese_mass_bucket",
                "lab/wooden_cow_cheese_mass_bucket",
                "alpine_salt/wooden_cow_cheese_mass_bucket",

                "limestone_bricks/cracked_limestone_bricks",

                "cheese_mass/cream_cheese",


                "oat/doormat",
                "flecked_wool/flecked_bed",
                "planks/flecked_bed",
                "flecked_wool/flecked_carpet",

                "stick/frame",
                "campfire/frame",
                "cobbled_limestone/furnace_cobblestone",
                "campfire/furnace_cobblestone",

                "goat_cheese_mass/goat_cheese_block",
                "goat_milk/goat_cheese_mass_bucket",
                "lab/goat_cheese_mass_bucket",
                "alpine_salt/goat_cheese_mass_bucket",
                "goat_milk/wooden_goat_cheese_mass_bucket",
                "lab/wooden_goat_cheese_mass_bucket",
                "alpine_salt/wooden_goat_cheese_mass_bucket",

                "wheat/hayblock_rug",

                "herbs_cheese_mass/herb_cheese_block",

                "is_milk/herbs_cheese_mass_bucket",
                "lab/herbs_cheese_mass_bucket",
                "herbs_ingredients/herbs_cheese_mass_bucket",
                "is_milk/meadow:wooden_herbs_cheese_mass_bucket",
                "lab/meadow:wooden_herbs_cheese_mass_bucket",
                "herbs_ingredients/meadow:wooden_herbs_cheese_mass_bucket",

                "highland_wool/highland_bed",
                "planks/highland_bed",
                "highland_wool/highland_carpet",

                "glass_bottle/jug",
                "jug/jug_juniper_tea",
                "bag_of_juniper/jug_juniper_tea",
                "jug/jug_yarrow_tea",
                "bag_of_yarrow/jug_yarrow_tea",



                "lavender_cheese_mass/lavender_cheese_block",
                "is_milk/lavender_cheese_mass_bucket",
                "lab/lavender_cheese_mass_bucket",
                "bag_of_lavender/lavender_cheese_mass_bucket",
                "is_milk/wooden_lavender_cheese_mass_bucket",
                "lab/wooden_lavender_cheese_mass_bucket",
                "bag_of_lavender/wooden_lavender_cheese_mass_bucket",

                "cobbled_limestone/limestone",
                "limestone_bricks/limestone_brick_slab",
                "limestone_bricks/limestone_brick_slab",
                "limestone/limestone_brick_slab",
                "limestone_bricks/limestone_brick_stairs",
                "limestone_bricks/limestone_brick_stairs",
                "limestone/limestone_brick_stairs",
                "limestone_bricks/limestone_brick_wall",
                "limestone_bricks/limestone_brick_wall",
                "limestone/limestone_brick_wall",
                "limestone/limestone_bricks",
                "limestone/limestone_bricks",
                "limestone/limestone_bricks_stairs",
                "limestone/limestone_slab",
                "limestone/limestone_stairs",

                "pine_log/mid_woodstack",
                "mossy_limestone_bricks/mossy_limestone_brick_slab",
                "mossy_limestone_bricks/mossy_limestone_brick_slab",
                "mossy_limestone_bricks/mossy_limestone_brick_stairs",
                "mossy_limestone_bricks/mossy_limestone_brick_stairs",
                "mossy_limestone_bricks/mossy_limestone_brick_wall",
                "mossy_limestone_bricks/mossy_limestone_brick_wall",
                "limestone_bricks/mossy_limestone_bricks",
                "moss_block/mossy_limestone_bricks",
                "limestone_bricks/mossy_limestone_bricks",
                "vine/mossy_limestone_bricks",


                "oat/oat_bread",
                "oat_cheese_mass/oat_cheese_block",
                "oat_milk/oat_cheese_mass_bucket",
                "lab/oat_cheese_mass_bucket",
                "alpine_salt/oat_cheese_mass_bucket",
                "oat_milk/wooden_oat_cheese_mass_bucket",
                "lab/wooden_oat_cheese_mass_bucket",
                "alpine_salt/wooden_oat_cheese_mass_bucket",
                "oat/oat_oil",
                "glass_bottle/oat_oil",

                "iron_ingot/oil_lantern",
                "oat_oil/oil_lantern",
                "iron_nugget/oil_lantern",

                "patched_wool/patched_bed",
                "planks/patched_bed",
                "patched_wool/patched_carpet",

                "bag_of_juniper/ricola",
                "bad_of_yarrow/ricola",
                "sugar/ricola",

                "rocky_sheep_wool/rocky_sheep_carpet",

                "sheep_cheese_mass/sheep_cheese_block",
                "sheep_milk/sheep_cheese_mass_bucket",
                "lab/sheep_cheese_mass_bucket",
                "alpine_salt/sheep_cheese_mass_bucket",
                "sheep_milk/wooden_sheep_cheese_mass_bucket",
                "lab/wooden_sheep_cheese_mass_bucket",
                "alpine_salt/wooden_sheep_cheese_mass_bucket",

                "pine_log/small_woodstack",



                "limestone_bricks/stove_tiles",
                "iron_ingot/stove_tiles",
                "limestone_bricks/stove_tiles_bench",
                "iron_ingot/stove_tiles_bench",
                "limestone_bricks/stove_tiles_lid",
                "iron_trapdoor/stove_tiles_lid",
                "iron_ingot/stove_tiles_lid",
                "limestone_bricks/stove_tiles_wood",
                "campfire/stove_tiles_wood",
                "iron_ingot/stove_tiles_wood",

                "stripped_pine_log/stripped_pine_wood",
                "umbra_wool/umbra_bed",
                "planks/umbra_bed",
                "red_wool/red_carpet",



                "pine_planks/watering_can",
                "wooden_water_bucket/watering_can",

                "eriophorum/white_dye",
                "eriophorum_tall/white_dye",
                "glass_pane/window",
                "stained_glass_pane/window_3",
                "stained_glass_pane/window_2",

                "pine_planks/window_shutter_1",
                "poppy/window_shutter_1",
                "pine_planks/window_shutter_2",
                "sweet_berries/window_shutter_2",
                "pine_planks/window_shutter_3",
                "spruce_sapling/window_shutter_3",

                "iron_axe/woodcutter",
                "pine_log/woodcutter",

                "planks/wooden_bucket",
                "planks/wooden_cauldron",
                "iron_ingot/wooden_cauldron",
                "brick/wooden_flower_pot",
                "blue_dye/wooden_flower_pot",

                "wooly_wool/wooly_bed",
                "planks/wooly_bed");

        for(String s : putRecipesHere){
            List<String> list1 = Arrays.stream(s.split("/")).toList();

            if(list1.size() < 2){
                System.out.println("False entry: " + s);
                continue;
            }

            write(list1.get(0), list1.get(1));
        }
    }


    public static void write(String condition, String recipe) {
        String fullRecipe = "meadow:" + recipe;
        String fullCondition = "meadow:" + condition;

        if(condition.contains(":")){
            fullCondition = condition;
            condition = Arrays.stream(condition.split(":")).toList().get(1);
        }

        if(recipe.contains(":")){
            fullRecipe = condition;
            recipe = Arrays.stream(recipe.split(":")).toList().get(1);
        }



        // Writing
        try (FileWriter fileWriter = new FileWriter(FOLDER + recipe + ".json"); JsonWriter jsonWriter = new JsonWriter(fileWriter)) {
            jsonWriter.setIndent("  ");
            jsonWriter.beginObject()
                    .name("parent").value("minecraft:recipes/root")
                    .name("rewards").beginObject().name("recipes").beginArray().value(fullRecipe).endArray().endObject()
                    .name("criteria").beginObject().name("has_" + condition).beginObject().name("trigger").value("minecraft:inventory_changed").name("conditions").beginObject().name("items").beginArray().beginObject().name("items").beginArray().value(fullCondition).endArray().endObject().endArray().endObject().endObject().name("has_the_recipe").beginObject().name("trigger").value("minecraft:recipe_unlocked").name("conditions").beginObject().name("recipe").value(fullRecipe).endObject().endObject().endObject()
                    .name("requirements").beginArray().beginArray().value("has_" + condition).value("has_the_recipe").endArray().endArray()

                    .endObject();
        } catch (IOException e) {
            System.out.printf("[Meadow] Couldn't write recipe to " + FOLDER + recipe);
            e.printStackTrace();
        }
    }
}
