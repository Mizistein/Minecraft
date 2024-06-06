package de.hoque.testmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.ModBlocks;
import de.hoque.testmod.util.ModTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagProvider extends BlockTagsProvider
{

   public ModBlockTagProvider(PackOutput pOutput, CompletableFuture<Provider> pLookupProvider,
			@Nullable ExistingFileHelper pExistingFileHelper)
   {
	  super(pOutput, pLookupProvider, TestMod.MODID, pExistingFileHelper);
   }

   @Override
   protected void addTags(Provider pProvider)
   {
	  this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLE)
	  		.add(ModBlocks.BAUXITE_ORE.get())
	  		.addTag(Tags.Blocks.ORES);
	  
	  this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
	  		.add(
	  				 ModBlocks.PLATE_BLOCK.get(), 
	  				 ModBlocks.PLATE_BLOCK_CORRODED.get(), 
	  				 ModBlocks.BAUXITE_ORE.get()
	  				 );
	  
	  this.tag(BlockTags.NEEDS_IRON_TOOL)
	  		.add(
				 ModBlocks.PLATE_BLOCK.get(), 
				 ModBlocks.PLATE_BLOCK_CORRODED.get(), 
				 ModBlocks.BAUXITE_ORE.get()
				 );
	  
	  //this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

   }

}
