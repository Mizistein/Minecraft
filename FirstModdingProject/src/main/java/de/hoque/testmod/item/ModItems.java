package de.hoque.testmod.item;

import de.hoque.testmod.TestMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
   public static final DeferredRegister<Item> ITEMS	= DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);

   public static final RegistryObject<Item>	  PLATE	= ITEMS.register("plate", () -> new Item(new Item.Properties()));

   public static void register(IEventBus eventBus)
   {
	  ITEMS.register(eventBus);
   }

}
