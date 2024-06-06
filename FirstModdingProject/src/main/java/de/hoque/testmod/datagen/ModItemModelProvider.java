package de.hoque.testmod.datagen;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{

   public ModItemModelProvider(PackOutput pOutput, ExistingFileHelper pExistingFileHelper)
   {
	  super(pOutput, TestMod.MODID, pExistingFileHelper);
   }

   @Override
   protected void registerModels()
   {
	  simpleItem(ModItems.BAUXITE_CHUNK);
	  simpleItem(ModItems.DATE);
	  simpleItem(ModItems.JADSCHE_POWDER);
	  simpleItem(ModItems.METAL_DETECTOR);
	  simpleItem(ModItems.SCREW_BOX);
	  
   }
   
   private ItemModelBuilder simpleItem(RegistryObject<Item> pItem)
   {
	  return withExistingParent(pItem.getId().getPath(), 
			   new ResourceLocation("item/generated")).texture("layer0", 
						new ResourceLocation(TestMod.MODID, "item/" + pItem.getId().getPath()));
   }

}
