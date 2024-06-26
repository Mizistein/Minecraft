package de.hoque.testmod.block.custom;

import de.hoque.testmod.block.entity.ModBlockEntities;
import de.hoque.testmod.block.entity.RollingMillStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

public class RollingMillStationBlock extends BaseEntityBlock
{
   public static final VoxelShape SHAPE_WHOLE = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 8.5D, 14.0D);
   
   
   //example for multi Voxel shape
   /*public static final VoxelShape SHAPE_BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
   public static final VoxelShape SHAPE_POST = Block.box(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D);
   public static final VoxelShape SHAPE_COMMON = Shapes.or(SHAPE_BASE, SHAPE_POST); */
   
   public RollingMillStationBlock(Properties pProperties)
   {
	  super(pProperties);
   }
   
   @Override
   public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
   {
	  return SHAPE_WHOLE;
	  
   }
   
   @Override
   public RenderShape getRenderShape(BlockState pState)
   {
	  return RenderShape.MODEL;
   }

   @Override
   public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
   {
	  return new RollingMillStationBlockEntity(pPos, pState);
   }
   
   @Override
   public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) 
   {
      if (!pLevel.isClientSide()) {
          BlockEntity entity = pLevel.getBlockEntity(pPos);
          if(entity instanceof RollingMillStationBlockEntity) {
              NetworkHooks.openScreen(((ServerPlayer)pPlayer), (RollingMillStationBlockEntity)entity, pPos);
          } else {
              throw new IllegalStateException("Our Container provider is missing!");
          }
      }

      return InteractionResult.sidedSuccess(pLevel.isClientSide());
  }
   
   @Override
   public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType)
   {
	  if(pLevel.isClientSide)
	  {
		 return null;
	  }
	  
	  return createTickerHelper(pBlockEntityType, ModBlockEntities.ROLLING_MILL_BE.get(),
               (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
   }
   
   @Override
   public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) 
   {
      if (pState.getBlock() != pNewState.getBlock()) {
          BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
          if (blockEntity instanceof RollingMillStationBlockEntity) {
              ((RollingMillStationBlockEntity) blockEntity).drops();
          }
      }

      super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
  }
   


}
