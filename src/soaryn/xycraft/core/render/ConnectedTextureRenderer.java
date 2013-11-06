package soaryn.xycraft.core.render;

import codechicken.lib.vec.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public abstract class ConnectedTextureRenderer {

   private static ConnectedTextureRenderer[] renderers = new ConnectedTextureRenderer[Block.blocksList.length];
   public final int blockID;
   public final String textureFile;
   private BlockCoord coord = new BlockCoord();


   public ConnectedTextureRenderer(int blockID, String textureFile) {
      this.blockID = blockID;
      this.textureFile = textureFile;
      renderers[blockID] = this;
   }

   public static ConnectedTextureRenderer getRenderer(int blockID) {
      return renderers[blockID];
   }

   public int getBlockTexture(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
      int[][] sideSideMap = new int[][]{{2, 5, 3, 4}, {2, 5, 3, 4}, {1, 4, 0, 5}, {1, 5, 0, 4}, {1, 3, 0, 2}, {1, 2, 0, 3}};
      int map = 0;

      for(int b = 0; b < 4; ++b) {
         int side0 = sideSideMap[side][(b + 3) % 4];
         int side1 = sideSideMap[side][b];
         if(!this.canConnectOnSide(iBlockAccess, this.coord.set(x, y, z), sideSideMap[side][b], side)) {
            map |= (7 << b * 2) % 256 | 7 >>> 8 - b * 2;
         } else if(!this.canConnectOnSide(iBlockAccess, this.coord.set(x, y, z).offset(side0), side1, side) || !this.canConnectOnSide(iBlockAccess, this.coord.set(x, y, z).offset(side1), side0, side)) {
            map |= 1 << b * 2;
         }
      }

      return this.getTextureFromMap(map);
   }

   public abstract int getTextureFromMap(int var1);

   public abstract boolean canConnectOnSide(IBlockAccess var1, BlockCoord var2, int var3, int var4);

}
