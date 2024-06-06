package de.hoque.testmod.item.custom;

import java.util.List;

import de.hoque.testmod.block.ModBlocks;
import de.hoque.testmod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GhandiHuakbarItem extends Item
{

   public GhandiHuakbarItem(Properties pProperties)
   {
	  super(pProperties);
   }
   
   @Override
   public InteractionResult useOn(UseOnContext pContext)
   {
	  if(!pContext.getLevel().isClientSide())
	  {
		 BlockPos postionedClicked = pContext.getClickedPos();
		 Player player = pContext.getPlayer();
		 boolean foundBlock = false;
		 
		 for(int i = 0; i <= postionedClicked.getY() + 64; i++)
		 {
			BlockState blockState = pContext.getLevel().getBlockState(postionedClicked.below(i));
			
			if(isValuableBlock(blockState))
			{
			   outputValueableCoordinates(postionedClicked.below(i), player, blockState.getBlock());
			   foundBlock = true;
			   
			   break;
			}
			
		 }
		 if(!foundBlock)
		 {
			player.sendSystemMessage(Component.literal("No valuable ore was found below this block"));
		 }
	  }
	  
	  pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),  player -> player.broadcastBreakEvent(player.getUsedItemHand()));
	  
	  return InteractionResult.SUCCESS;
   }

   private void outputValueableCoordinates(BlockPos pBlockPos, Player pPlayer, Block pBlock)
   {
	  pPlayer.sendSystemMessage(Component.literal("Found " + I18n.get(pBlock.getDescriptionId()) + " at " + "(" + pBlockPos.getX() + ", " + pBlockPos.getY() + ", " + pBlockPos.getZ() + ")"));
	  
   }
   
   @Override
   public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents,
    		TooltipFlag pIsAdvanced)
   {
	  pTooltipComponents.add(Component.translatable("tooltip.mizis.ghandihuakbaritem.tooltip"));
	  super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
   }

   private boolean isValuableBlock(BlockState pBlockState)
   {
	  return pBlockState.is(ModTags.Blocks.METAL_DETECTOR_VALUABLE);
   }

}
