package de.hoque.testmod.datagen;

import java.util.List;
import java.util.Set;

import de.hoque.testmod.datagen.loot.ModBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLootTableProvider 
{
   public static LootTableProvider create(PackOutput output) 
   {
       return new LootTableProvider(output, Set.of(), List.of(
               new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
       ));
   }
}