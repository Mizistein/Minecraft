package de.hoque.testmod.datagen;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
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
	  
   }
   
   private void blockWithItem(RegistryObject<Block> pRegistryObject)
   {
	  simpleBlockItem(pRegistryObject.get(), cubeAll(pRegistryObject.get()));
   }

}
