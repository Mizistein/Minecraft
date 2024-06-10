package de.hoque.testmod.recipe;

import de.hoque.testmod.TestMod;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PlateRollingRecipe implements Recipe<SimpleContainer>
{
   private final NonNullList<Ingredient> inputItems;
   private final ItemStack output;
   private final ResourceLocation id;

   public PlateRollingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
       this.inputItems = inputItems;
       this.output = output;
       this.id = id;
   }

   @Override
   public boolean matches(SimpleContainer pContainer, Level pLevel) {
       if(pLevel.isClientSide()) {
           return false;
       }

       return inputItems.get(0).test(pContainer.getItem(0));
   }

   @Override
   public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
       return output.copy();
   }

   @Override
   public boolean canCraftInDimensions(int pWidth, int pHeight) {
       return true;
   }

   @Override
   public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
       return output.copy();
   }

   @Override
   public ResourceLocation getId() {
       return id;
   }

   @Override
   public RecipeSerializer<?> getSerializer() {
       return Serializer.INSTANCE;
   }

   @Override
   public RecipeType<?> getType() {
       return Type.INSTANCE;
   }

   public static class Type implements RecipeType<PlateRollingRecipe> {
       public static final Type INSTANCE = new Type();
       public static final String ID = "plate_rolling";
   }

   public static class Serializer implements RecipeSerializer<PlateRollingRecipe> {
       public static final Serializer INSTANCE = new Serializer();
       public static final ResourceLocation ID = new ResourceLocation(TestMod.MODID, "plate_rolling");

       @Override
       public PlateRollingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
           ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

           JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
           NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

           for(int i = 0; i < inputs.size(); i++) {
               inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
           }

           return new PlateRollingRecipe(inputs, output, pRecipeId);
       }

       @Override
       public @Nullable PlateRollingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
           int inputSize = pBuffer.readInt();
           if (inputSize < 0) {
               TestMod.LOGGER.error("Invalid input size in recipe network data: {}", inputSize);
               return null;
           }

           NonNullList<Ingredient> inputs = NonNullList.withSize(inputSize, Ingredient.EMPTY);
           for (int i = 0; i < inputSize; i++) {
               Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
               if (ingredient == null) {
                   TestMod.LOGGER.error("Invalid ingredient in recipe network data");
                   return null;
               }
               inputs.set(i, ingredient);
           }

           ItemStack output = pBuffer.readItem();
           if (output == null) {
               TestMod.LOGGER.error("Invalid output item in recipe network data");
               return null;
           }

           return new PlateRollingRecipe(inputs, output, pRecipeId);
       }

       @Override
       public void toNetwork(FriendlyByteBuf pBuffer, PlateRollingRecipe pRecipe) {
           pBuffer.writeInt(pRecipe.inputItems.size());
           for (Ingredient ingredient : pRecipe.inputItems) {
               if (ingredient == null) {
                   TestMod.LOGGER.error("Null ingredient in recipe input list");
                   return;
               }
               ingredient.toNetwork(pBuffer);
           }

           pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
       }
   }
}