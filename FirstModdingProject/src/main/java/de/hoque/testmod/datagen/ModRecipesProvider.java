package de.hoque.testmod.datagen;

import java.util.List;
import java.util.function.Consumer;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.ModBlocks;
import de.hoque.testmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

public class ModRecipesProvider extends RecipeProvider
{
   private static final List<ItemLike> PLATE_SMELTABLES = List.of(Items.IRON_INGOT, ModItems.BAUXITE_CHUNK.get());

   public ModRecipesProvider(PackOutput pOutput)
   {
	  super(pOutput);
   }

   @Override
   protected void buildRecipes(Consumer<FinishedRecipe> pWriter)
   {
	  oreBlasting(pWriter, PLATE_SMELTABLES, RecipeCategory.MISC, ModItems.PLATE.get(), 10.0F, 25, "plate");
	  oreSmelting(pWriter, PLATE_SMELTABLES, RecipeCategory.MISC, ModItems.PLATE.get(), 5.0F, 200, "plate");
	  
	  ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_DETECTOR.get())
	  .pattern(" G ")
	  .pattern("RBW")
	  .pattern("PSP")
	  .define('G', Items.GOLD_INGOT)
	  .define('R', Items.REDSTONE_BLOCK)
	  .define('B', ModItems.BAUXITE_CHUNK.get())
	  .define('W', ModItems.SCREW_BOX.get())
	  .define('P', ModItems.PLATE.get())
	  .define('S', Items.STICK)
	  .unlockedBy(getHasName(ModItems.BAUXITE_CHUNK.get()), has(ModItems.BAUXITE_CHUNK.get()))
	  .save(pWriter);
	  
	  ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PLATE.get())
	  .requires(ModBlocks.PLATE_BLOCK.get())
	  .unlockedBy(getHasName(ModBlocks.PLATE_BLOCK.get()), has(ModBlocks.PLATE_BLOCK.get()))
	  .save(pWriter);
	  
	  ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PLATE_BLOCK.get())
	  .requires(ModItems.PLATE.get(), 9)
	  .unlockedBy(getHasName(ModItems.PLATE.get()), has(ModItems.PLATE.get()))
	  .save(pWriter);
	  
   }
   
   protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
      oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
   }

   protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
      oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
   }

   protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
      for(ItemLike itemlike : pIngredients) {
         SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
         .group(pGroup)
         .unlockedBy(getHasName(itemlike), has(itemlike))
         .save(pFinishedRecipeConsumer, TestMod.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
      }

   }

}
