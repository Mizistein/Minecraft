package de.hoque.testmod.block.entity;

import de.hoque.testmod.TestMod;
import de.hoque.testmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities
{
   public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestMod.MODID);
   
   public static final RegistryObject<BlockEntityType<RollingMillStationBlockEntity>> ROLLING_MILL_BE =
			BLOCK_ENTITY.register("rolling_mill_station_be", () -> 
				BlockEntityType.Builder.of(RollingMillStationBlockEntity::new, 
						 ModBlocks.ROLLING_MILL_STATION_BLOCK.get()).build(null));
   
   
   public static void register(IEventBus pEventBus)
   {
	  BLOCK_ENTITY.register(pEventBus);
   }

}
