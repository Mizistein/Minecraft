package de.hoque.testmod.block.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.hoque.testmod.item.ModItems;
import de.hoque.testmod.screen.RollingMillStationBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class RollingMillStationBlockEntity extends BlockEntity implements MenuProvider
{
   private final ItemStackHandler handler = new ItemStackHandler(2);
   
   private static final int INPUT_SLOT = 0;
   private static final int OUTPUT_SLOT = 1;
   
   private LazyOptional<IItemHandler> lazyOptional = LazyOptional.empty();
   
   protected final ContainerData data;
   private int progress = 0;
   private int maxProgress = 78;

   public RollingMillStationBlockEntity(BlockPos pPos, BlockState pBlockState)
   {
	  super(ModBlockEntities.ROLLING_MILL_BE.get() ,pPos, pBlockState);
	  this.data = new ContainerData() {

		 @Override
		 public void set(int pIndex, int pValue)
		 {
			switch (pIndex)
			{
			   case 0 -> RollingMillStationBlockEntity.this.progress = pValue;
			   case 1 -> RollingMillStationBlockEntity.this.maxProgress = pValue;
			};
		 }

		 @Override
		 public int getCount()
		 {
			return 2;
		 }

		 @Override
		 public int get(int pIndex)
		 {
			return switch (pIndex)
			{
			   case 0 -> RollingMillStationBlockEntity.this.progress;
			   case 1 -> RollingMillStationBlockEntity.this.maxProgress;
			   default -> 0;
			};
		 }
	  };
   }
   
   @Override
   public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> pCap, @Nullable Direction pSide)
   {
	  if(pCap == ForgeCapabilities.ITEM_HANDLER)
	  {
		 return lazyOptional.cast();
	  }
	  
	  return super.getCapability(pCap, pSide);
   }
   
   @Override
   public void onLoad()
   {
	  super.onLoad();
	  lazyOptional = LazyOptional.of(() -> handler);
      
   }
   
   @Override
   public void invalidateCaps()
   {
      super.invalidateCaps();
      lazyOptional.invalidate();
   }
   
   @Override
   public Component getDisplayName()
   {
	  return Component.translatable("block.mizis.rolling_mill_station_block");
   }
   
   public void drops()
   {
	  SimpleContainer container = new SimpleContainer(handler.getSlots());
	  for (int i = 0; i < handler.getSlots(); i++)
	  {
		 container.setItem(i, handler.getStackInSlot(i));
	  }
	  Containers.dropContents(this.level, this.worldPosition, container);
   }

   @Override
   public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
   {
	  return new RollingMillStationBlockMenu(pContainerId, pPlayerInventory, this, this.data);
   }
   
   @Override
   protected void saveAdditional(CompoundTag pTag)
   {
	  pTag.put("inventory", handler.serializeNBT());
	  pTag.putInt("rolling_mill_station_block.progress", progress);
	  
      super.saveAdditional(pTag);
   }
   
   @Override
   public void load(CompoundTag pTag)
   {
	  handler.deserializeNBT(pTag.getCompound("inventory"));
	  progress = pTag.getInt("rolling_mill_station_block.progress");
	  
	  super.load(pTag);
   }

   public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
      if(hasRecipe()) {
          increaseCraftingProgress();
          setChanged(pLevel, pPos, pState);

          if(hasProgressFinished()) {
              craftItem();
              resetProgress();
          }
      } else {
          resetProgress();
      }
  }

  private void resetProgress() {
      progress = 0;
  }

  private void craftItem() {
      ItemStack result = new ItemStack(ModItems.PLATE.get(), 1);
      this.handler.extractItem(INPUT_SLOT, 1, false);

      this.handler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
              this.handler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
  }

  private boolean hasRecipe() {
      boolean hasCraftingItem = this.handler.getStackInSlot(INPUT_SLOT).getItem() == Items.IRON_INGOT;
      ItemStack result = new ItemStack(ModItems.PLATE.get());

      return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
  }

  private boolean canInsertItemIntoOutputSlot(Item item) {
      return this.handler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.handler.getStackInSlot(OUTPUT_SLOT).is(item);
  }

  private boolean canInsertAmountIntoOutputSlot(int count) {
      return this.handler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.handler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
  }

  private boolean hasProgressFinished() {
      return progress >= maxProgress;
  }

  private void increaseCraftingProgress() {
      progress++;
  }

}
