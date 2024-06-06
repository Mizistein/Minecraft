package de.hoque.testmod.datagen.loot;

import java.util.Set;

import de.hoque.testmod.block.ModBlocks;
import de.hoque.testmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLootSubProvider
{

   public ModBlockLootTables()
   {
	  super(Set.of(), FeatureFlags.REGISTRY.allFlags());
   }


   @Override
   protected void generate()
   {
	  this.dropSelf(ModBlocks.GHANDI_BLOCK.get());
	  this.dropSelf(ModBlocks.PLATE_BLOCK.get());
	  this.dropSelf(ModBlocks.PLATE_BLOCK_CORRODED.get());
	  
	  this.add(ModBlocks.BAUXITE_ORE.get(), 
			   block -> createCopperLikeOreDrops(ModBlocks.BAUXITE_ORE.get(), ModItems.BAUXITE_CHUNK.get()));
   }
   
   
   protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item pItem) {
      return createSilkTouchDispatchTable(pBlock, 
    		   this.applyExplosionDecay(pBlock, 
    					LootItem.lootTableItem(pItem)
    					.apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
    					.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
   }
   
   @Override
   protected Iterable<Block> getKnownBlocks()
   {
      return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
   }

}
