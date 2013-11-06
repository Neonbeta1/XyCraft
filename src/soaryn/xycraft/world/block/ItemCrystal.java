package soaryn.xycraft.world.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import soaryn.xycraft.world.block.BlockCrystal;
import soaryn.xycraft.world.block.TileCrystal;

public class ItemCrystal extends ItemBlock {

   public ItemCrystal(int par1) {
      super(par1);
   }

   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      if(world.getBlockId(x, y, z) == this.getBlockID()) {
         int quantity = BlockCrystal.getQuantity(world, x, y, z);
         if(quantity < 3) {
            ++quantity;
            TileEntity tile = world.getBlockTileEntity(x, y, z);
            if(tile instanceof TileCrystal) {
               ++((TileCrystal)tile).quantity;
               world.markBlockForUpdate(x, y, z);
            }

            --stack.stackSize;
            return true;
         } else {
            return false;
         }
      } else {
         return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
      }
   }

   public boolean canPlaceItemBlockOnSide(World world, int x, int y, int z, int par5, EntityPlayer player, ItemStack stack) {
      return world.getBlockId(x, y, z) == this.getBlockID()?BlockCrystal.getQuantity(world, x, y, z) < 3:super.canPlaceItemBlockOnSide(world, x, y, z, par5, player, stack);
   }
}
