package de.hoque.testmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods
{	//Vanilla Food is in FoodProperties > Foods
   public static final FoodProperties DATE = new FoodProperties.Builder()
			.fast()
			.nutrition(2)
			.saturationMod(0.2F)
			.effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.1F)
			.build();

}
