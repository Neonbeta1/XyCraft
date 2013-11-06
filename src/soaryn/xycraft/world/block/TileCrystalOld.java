package soaryn.xycraft.world.block;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import soaryn.xycraft.world.block.BlockCrystal;

public class TileCrystalOld extends TileEntity {

   public void updateEntity() {
      Block block = Block.blocksList[super.worldObj.getBlockId(super.xCoord, super.yCoord, super.zCoord)];
      if(block instanceof BlockCrystal && block.canPlaceBlockAt(super.worldObj, super.xCoord, super.yCoord, super.zCoord)) {
         super.worldObj.removeBlockTileEntity(super.xCoord, super.yCoord, super.zCoord);
         super.worldObj.markBlockForUpdate(super.xCoord, super.yCoord, super.zCoord);
         super.worldObj.getChunkFromBlockCoords(super.xCoord, super.zCoord).setChunkModified();
      } else {
         super.worldObj.setBlock(super.xCoord, super.yCoord, super.zCoord, 0);
      }
   }
}
