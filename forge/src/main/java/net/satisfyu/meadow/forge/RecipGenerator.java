package net.satisfyu.meadow.forge;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

public class RecipGenerator extends RecipeProvider {
    public RecipGenerator(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(RecipeOutput p_297267_) {
        ConditionalRecipe.builder().condition(new ModLoadedCondition("meadow")).recipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.DAMAGED_ANVIL).requires(Items.DANDELION)::save).save(p_297267_, new ResourceLocation("meadow", "pls"));
    }
}
