package de.hoque.testmod.block;

import java.util.function.Supplier;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.custom.GhandalfBlock;
import de.hoque.testmod.block.custom.RollingMillStationBlock;
import de.hoque.testmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks
{

   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MODID);
   
   public static final RegistryObject<Block> PLATE_BLOCK = registerBlock("plate_block", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
   
   public static final RegistryObject<Block> PLATE_BLOCK_CORRODED = registerBlock("plate_block_corroded", 
			() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

   public static final RegistryObject<Block> BAUXITE_ORE = registerBlock("bauxite_ore", 
			() -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
					 .strength(2.0F, 6.0F), UniformInt.of(3, 6)));
   
   
   public static final RegistryObject<Block> GHANDI_BLOCK = registerBlock("ghandi_block", 
			() -> new GhandalfBlock(BlockBehaviour.Properties.copy(Blocks.TNT)));
   
   public static final RegistryObject<Block> ROLLING_MILL_STATION_BLOCK = registerBlock("rolling_mill_station_block", 
			() -> new RollingMillStationBlock(BlockBehaviour.Properties.copy(Blocks.ANVIL).sound(SoundType.METAL).noOcclusion()));
   
   
   
   
   
   private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
   {
	  RegistryObject<T> toReturn = BLOCKS.register(name, block);
	  registerBlockItem(name, toReturn);
	  return toReturn;
   }

   private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> blocks)
   {
	  return ModItems.ITEMS.register(name, () -> new BlockItem(blocks.get(), new Item.Properties()));
   }

   public static void register(IEventBus eventBus)
   {
	  BLOCKS.register(eventBus);
   }
}
