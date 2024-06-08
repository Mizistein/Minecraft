package de.hoque.testmod.datagen;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider
{

   public ModBlockStateProvider(PackOutput pOutput, ExistingFileHelper pExFileHelper)
   {
	  super(pOutput, TestMod.MODID, pExFileHelper);
   }

   @Override
   protected void registerStatesAndModels()
   {
	  blockWithItem(ModBlocks.BAUXITE_ORE);
	  blockWithItem(ModBlocks.GHANDI_BLOCK);
	  blockWithItem(ModBlocks.PLATE_BLOCK);
	  blockWithItem(ModBlocks.PLATE_BLOCK_CORRODED);
	  
	  simpleBlockWithItem(ModBlocks.ROLLING_MILL_STATION_BLOCK.get(), 
			   new ModelFile.UncheckedModelFile(modLoc("block/rolling_mill_station_block")));
	  
   }
   
   private void blockWithItem(RegistryObject<Block> pRegistryObject)
   {
	  simpleBlockWithItem(pRegistryObject.get(), cubeAll(pRegistryObject.get()));
   }

}
