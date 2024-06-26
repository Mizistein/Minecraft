package de.hoque.testmod.item;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs
{
   public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MODID);
   
   public static final RegistryObject<CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register(
			"test_tab", 
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PLATE.get()))
			.title(Component.translatable("creativetab.test_tab"))
			.displayItems((pParameters, pOutput) -> {
			   pOutput.accept(ModBlocks.ROLLING_MILL_STATION_BLOCK.get());
			   pOutput.accept(ModItems.PLATE.get());
			   pOutput.accept(ModBlocks.PLATE_BLOCK.get());
			   pOutput.accept(ModBlocks.PLATE_BLOCK_CORRODED.get());
			   pOutput.accept(ModItems.SCREW_BOX.get());
			   
			   pOutput.accept(ModItems.BAUXITE_CHUNK.get());
			   pOutput.accept(ModBlocks.BAUXITE_ORE.get());

			   
			   
			   pOutput.accept(ModItems.METAL_DETECTOR.get());
			   pOutput.accept(ModBlocks.GHANDI_BLOCK.get());

			   
			   pOutput.accept(ModItems.DATE.get());
			   pOutput.accept(ModItems.JADSCHE_POWDER.get());
			   pOutput.accept(ModItems.SAPPHIRE_STAFF.get());
			   
			})
			.build());

   public static void register(IEventBus eventBus)
   {
	  CREATIVE_MODE_TABS.register(eventBus);
   }
}
