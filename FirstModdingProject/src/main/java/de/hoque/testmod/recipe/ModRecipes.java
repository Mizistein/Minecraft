package de.hoque.testmod.recipe;

import de.hoque.testmod.TestMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes
{
   public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TestMod.MODID);

    public static final RegistryObject<RecipeSerializer<PlateRollingRecipe>> PLATE_ROLLING_SERIALIZER =
            SERIALIZERS.register("plate_rolling", () -> PlateRollingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
