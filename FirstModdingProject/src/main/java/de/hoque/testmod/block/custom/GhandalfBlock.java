package de.hoque.testmod.block.custom;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags.EntityTypes;
import net.minecraftforge.event.level.LevelEvent.CreateSpawnPosition;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class GhandalfBlock extends Block
{

   public GhandalfBlock(Properties pProperties)
   {
	  super(pProperties);
   }
   
   @Override
   public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity)
   {
	  
      pEntity.sendSystemMessage(Component.literal("GET OFF MEEE ALLLAAAAAAAAAAAAHUAKBAAAAAAAAAAAAAAAR"));
      
      super.stepOn(pLevel, pPos, pState, pEntity);
   }
   
   @Override
   public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
    		BlockHitResult pHit)
   {
	  pLevel.playSound(pPlayer, pPos, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
	  
	  if(!pLevel.isClientSide())
	  {
		 if((Math.random() * 100.0) < 25.0)
		  {
			 pPlayer.sendSystemMessage(Component.literal("Allahuakbar"));
			 //pLevel.explode(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), 3.0F, false, Level.ExplosionInteraction.TNT);
			 //pPlayer.kill();
			 if(pPlayer.isDeadOrDying())
			 {
				pLevel.playSound(pPlayer, pPos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 0.0F, 0.0F);
				pPlayer.sendSystemMessage(Component.literal("BOOM"));
			 }
			 
			 Creeper creeper = EntityType.CREEPER.create(pLevel);
			 creeper.setPos(pPlayer.getX(), pPlayer.getY() + 5, pPlayer.getZ() );
			 creeper.setCustomName(Component.literal("Ghandolf der Scharfe")); // Set the nametag
			 creeper.setCustomNameVisible(true); // Make the nametag visible
			 
			 
			 CompoundTag creeperNBT =  creeper.serializeNBT();
			 creeperNBT.putBoolean("powered", true);
			 creeper.deserializeNBT(creeperNBT);
			 
			// Get a list of nearby villagers
			 List<Villager> nearbyVillagers = pLevel.getEntitiesOfClass(Villager.class, creeper.getBoundingBox().inflate(50, 10, 50));

			 // Choose a target villager (e.g., the closest one)
			 Villager targetVillager = null;
			 double closestDistance = Double.MAX_VALUE;
			 for (Villager villager : nearbyVillagers) {
			     double distance = creeper.distanceTo(villager);
			     if (distance < closestDistance) {
			         closestDistance = distance;
			         targetVillager = villager;
			     }
			 }

			 // Set the creeper's target
			 if (targetVillager != null) {
			     creeper.setTarget(targetVillager);
			 }
			 
			 for(int i = 0; i < 10; i++)
			 {
				pLevel.addFreshEntity(creeper);
			 }
		  }
	  }
	  
	  
      return InteractionResult.SUCCESS;
   }
   

}
