package soaryn.xycraft.world.gen;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import soaryn.xycraft.world.XyCraftWorldBlocks;
import soaryn.xycraft.world.block.BlockCrystal;

public class WorldPopCrystal extends WorldGenerator {

   ArrayList genSides = new ArrayList();
   BlockCrystal blockCrystal;


   public WorldPopCrystal() {
      this.blockCrystal = (BlockCrystal)XyCraftWorldBlocks.crystal;
   }

   public boolean generate(World world, Random rand, int x, int y, int z) {
      Block existingBlock = Block.blocksList[world.getBlockId(x, y, z)];
      if(existingBlock != null && existingBlock.isGenMineableReplaceable(world, x, y, z, 0)) {
         this.genSides.clear();
         ForgeDirection[] fside = ForgeDirection.VALID_DIRECTIONS;
         int len$ = fside.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            ForgeDirection fside1 = fside[i$];
            if(world.blockExists(x + fside1.offsetX, y + fside1.offsetY, z + fside1.offsetZ) && this.blockCrystal.canPlaceBlockOnSide(world, x + fside1.offsetX, y + fside1.offsetY, z + fside1.offsetZ, fside1.ordinal())) {
               this.genSides.add(fside1);
            }
         }

         if(!this.genSides.isEmpty()) {
            ForgeDirection var11 = (ForgeDirection)this.genSides.get(rand.nextInt(this.genSides.size()));
            world.setBlockMetadataWithNotify(x, y, z, this.blockCrystal.blockID, var11.ordinal());
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
