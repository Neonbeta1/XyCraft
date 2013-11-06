package soaryn.xycraft.world.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import soaryn.xycraft.world.XyCraftWorldBlocks;
import soaryn.xycraft.world.item.ItemWorld;

public class ItemHenequen extends ItemWorld implements IPlantable {

   public ItemHenequen(int id) {
      super(id, 0, 6);
   }

   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      if(side != 1) {
         return false;
      } else if(player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack) && player.canPlayerEdit(x, y + 2, z, side, stack)) {
         int var11 = world.getBlockId(x, y, z);
         Block soil = Block.blocksList[var11];
         if(soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
            world.setBlock(x, y + 1, z, XyCraftWorldBlocks.henequen.blockID);
            --stack.stackSize;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public EnumPlantType getPlantType(World world, int x, int y, int z) {
      return EnumPlantType.Crop;
   }

   public int getPlantID(World world, int x, int y, int z) {
      return XyCraftWorldBlocks.henequen.blockID;
   }

   public int getPlantMetadata(World world, int x, int y, int z) {
      return 0;
   }
}
