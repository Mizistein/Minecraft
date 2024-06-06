package de.hoque.testmod.item;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.item.custom.FuelItem;
import de.hoque.testmod.item.custom.GhandiHuakbarItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
   public static final DeferredRegister<Item> ITEMS	= DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);

   public static final RegistryObject<Item>	  PLATE			 = ITEMS.register("plate", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item>	  SCREW_BOX		 = ITEMS.register("screw_box", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item>	  BAUXITE_CHUNK	 = ITEMS.register("bauxite_chunk", () -> new Item(new Item.Properties()));

   
   public static final RegistryObject<Item>	  METAL_DETECTOR =  ITEMS.register("metal_detector", () -> new GhandiHuakbarItem(new Item.Properties().durability(100)));
   
   public static final RegistryObject<Item>	  DATE =  ITEMS.register("date", () -> new Item(new Item.Properties().food(ModFoods.DATE)));
   
   public static final RegistryObject<Item>   JADSCHE_POWDER = ITEMS.register("jadsche_powder", () -> new FuelItem(new Item.Properties(), 12000));

   public static void register(IEventBus eventBus)
   {
	  ITEMS.register(eventBus);
   }

}
