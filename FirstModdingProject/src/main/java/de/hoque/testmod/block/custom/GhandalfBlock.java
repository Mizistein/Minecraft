package de.hoque.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

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
	  if(Math.random() * 100 < 90.0)
	  {
		 pPlayer.sendSystemMessage(Component.literal("Allahuakbar"));
		 pPlayer.kill();
	  }
	  
      return InteractionResult.SUCCESS;
   }
   

}