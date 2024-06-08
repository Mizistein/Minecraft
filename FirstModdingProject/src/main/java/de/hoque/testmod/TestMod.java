package de.hoque.testmod;

import com.mojang.logging.LogUtils;

import de.hoque.testmod.block.ModBlocks;
import de.hoque.testmod.block.entity.ModBlockEntities;
import de.hoque.testmod.item.ModCreativeModTabs;
import de.hoque.testmod.item.ModItems;
import de.hoque.testmod.screen.ModMenuTypes;
import de.hoque.testmod.screen.RollingMillStationBlockScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(TestMod.MODID)
public class TestMod
{
   public static final String  MODID  = "mizis";
   private static final Logger LOGGER = LogUtils.getLogger();

   public TestMod()
   {
	  IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

	  ModCreativeModTabs.register(modEventBus);
	  
	  ModItems.register(modEventBus);
	  ModBlocks.register(modEventBus);
	  
	  ModBlockEntities.register(modEventBus);
	  ModMenuTypes.register(modEventBus);
	  
	  modEventBus.addListener(this::commonSetup);

	  MinecraftForge.EVENT_BUS.register(this);

	  modEventBus.addListener(this::addCreative);

	  ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
   }

   private void commonSetup(final FMLCommonSetupEvent event)
   {

   }

   private void addCreative(BuildCreativeModeTabContentsEvent event)
   {
	  if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) //example
	  {
		 event.accept(ModItems.PLATE);
		 event.accept(ModItems.SCREW_BOX);
	  }
   }

   @SubscribeEvent
   public void onServerStarting(ServerStartingEvent event)
   {
	  LOGGER.info("DO SMTH AT SERVER START");
   }

   @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
   public static class ClientModEvents
   {
	  @SubscribeEvent
	  public static void onClientSetup(FMLClientSetupEvent event)
	  {
		 MenuScreens.register(ModMenuTypes.ROLLING_MILL_STATION_BLOCK_MENU.get(), RollingMillStationBlockScreen::new);
		 LOGGER.info("HELLO FROM CLIENT SETUP");
		 LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
	  }
   }
}
