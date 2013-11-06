package soaryn.xycraft.core.render;

import codechicken.lib.vec.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import soaryn.xycraft.core.render.ConnectedTextureRenderer;

public class GlassConnectedTexture extends ConnectedTextureRenderer {

   private int[] textureIndexMap = new int[256];


   public GlassConnectedTexture(int blockID, String textureFile) {
      super(blockID, textureFile);
      this.loadTextureMap();
   }

   private void loadTextureMap() {
      this.textureIndexMap[0] = 17;
      this.textureIndexMap[1] = 24;
      this.textureIndexMap[4] = 23;
      this.textureIndexMap[5] = 27;
      this.textureIndexMap[7] = 1;
      this.textureIndexMap[16] = 7;
      this.textureIndexMap[17] = 43;
      this.textureIndexMap[20] = 53;
      this.textureIndexMap[21] = 25;
      this.textureIndexMap[23] = 39;
      this.textureIndexMap[28] = 18;
      this.textureIndexMap[29] = 58;
      this.textureIndexMap[31] = 2;
      this.textureIndexMap[64] = 8;
      this.textureIndexMap[65] = 54;
      this.textureIndexMap[68] = 59;
      this.textureIndexMap[69] = 26;
      this.textureIndexMap[71] = 40;
      this.textureIndexMap[80] = 11;
      this.textureIndexMap[81] = 10;
      this.textureIndexMap[84] = 9;
      this.textureIndexMap[85] = 21;
      this.textureIndexMap[87] = 5;
      this.textureIndexMap[92] = 42;
      this.textureIndexMap[93] = 22;
      this.textureIndexMap[95] = 6;
      this.textureIndexMap[112] = 33;
      this.textureIndexMap[113] = 56;
      this.textureIndexMap[116] = 55;
      this.textureIndexMap[117] = 37;
      this.textureIndexMap[119] = 49;
      this.textureIndexMap[124] = 34;
      this.textureIndexMap[125] = 38;
      this.textureIndexMap[127] = 50;
      this.textureIndexMap[193] = 16;
      this.textureIndexMap[197] = 57;
      this.textureIndexMap[199] = 0;
      this.textureIndexMap[209] = 41;
      this.textureIndexMap[213] = 20;
      this.textureIndexMap[215] = 4;
      this.textureIndexMap[221] = 19;
      this.textureIndexMap[223] = 3;
      this.textureIndexMap[241] = 32;
      this.textureIndexMap[245] = 36;
      this.textureIndexMap[247] = 48;
      this.textureIndexMap[253] = 35;
      this.textureIndexMap[255] = 51;
   }

   public boolean canConnectOnSide(IBlockAccess iBlockAccess, BlockCoord coord, int side, int face) {
      int block = this.getBlockID(iBlockAccess, coord.offset(side));
      int blockabove = this.getBlockID(iBlockAccess, coord.offset(face));
      return block == this.blockID && blockabove != this.blockID;
   }

   private int getBlockID(IBlockAccess iBlockAccess, BlockCoord coord) {
      Block block = Block.blocksList[iBlockAccess.getBlockId(coord.x, coord.y, coord.z)];
      return block == null?0:block.blockID;
   }

   public int getTextureFromMap(int map) {
      return this.textureIndexMap[map];
   }
}
