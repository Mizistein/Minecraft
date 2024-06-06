package de.hoque.testmod.util;

import de.hoque.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
   public static class Blocks
   {
	  //Custom Item tags here...
	  public static final TagKey<Block> METAL_DETECTOR_VALUABLE = tag("metal_detector_valuable");

	  private static TagKey<Block> tag(String pName)
	  {
		 return BlockTags.create(new ResourceLocation(TestMod.MODID, pName));
	  }

   }

   public static class Items
   {
	  //Custom Item tags here...
	  
	  private static TagKey<Item> tag(String pName)
	  {
		 return ItemTags.create(new ResourceLocation(TestMod.MODID, pName));
	  }

   }
}
