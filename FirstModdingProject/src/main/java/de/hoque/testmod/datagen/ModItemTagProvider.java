package de.hoque.testmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import de.hoque.testmod.TestMod;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagProvider extends ItemTagsProvider
{

   public ModItemTagProvider(PackOutput pOutput, CompletableFuture<Provider> pLookupProvider,
			CompletableFuture<TagLookup<Block>> pBlockTags, 
			@Nullable ExistingFileHelper pExistingFileHelper)
   {
	  super(pOutput, pLookupProvider, pBlockTags, TestMod.MODID, pExistingFileHelper);
   }

   @Override
   protected void addTags(Provider pProvider)
   {
	  
   }

}
