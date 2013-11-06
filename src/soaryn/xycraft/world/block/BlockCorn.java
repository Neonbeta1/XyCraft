package soaryn.xycraft.world.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import soaryn.xycraft.world.XyCraftWorldItems;

public class BlockCorn extends BlockCrops {

   public BlockCorn(int id, int index) {
      super(id);
      this.setCreativeTab((CreativeTabs)null);
      this.setTickRandomly(true);
   }

   public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
      super.breakBlock(world, x, y, z, par5, par6);
      if(par6 == 5) {
         this.dropBlockAsItem(world, x, y - 1, z, 4, 1);
         world.setBlockMetadataWithNotify(x, y - 1, z, 0, y);
      }

   }

   public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int i, int j, int k) {
      float hMin = 0.0F;
      float hMax = 1.0F;
      float wMin = 0.1F;
      float wMax = 0.9F;
      int meta = iBlockAccess.getBlockMetadata(i, j, k);
      switch(meta) {
      case 0:
      case 1:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax - 0.8F, wMax);
         break;
      case 2:
      case 3:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax - 0.4F, wMax);
         break;
      case 4:
      case 5:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax, wMax);
      }

   }

   protected boolean canThisPlantGrowOnThisBlockID(int blockId) {
      return blockId == Block.tilledField.blockID;
   }

   public void updateTick(World world, int x, int y, int z, Random rand) {
      if(!this.isFullyGrown(world, x, y, z)) {
         if(world.getBlockLightValue(x, y + 1, z) >= 9) {
            int meta = world.getBlockMetadata(x, y, z);
            float growth = this.getGrowthRate(world, x, y, z);
            int k = rand.nextInt((int)(25.0F / growth) + 1);
            if(meta < 4 && k == 0) {
               ++meta;
               world.setBlock(x, y, z, meta);
               if(meta == 4) {
                  world.setBlockMetadataWithNotify(x, y + 1, z, super.blockID, 5);
                  return;
               }
            }
         }

      }
   }

   public boolean isFullyGrown(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      return world.getBlockId(x, y, z) == super.blockID && (meta == 4 || meta == 5);
   }

   public void fertilize(World world, int x, int y, int z) {
      if(world.getBlockId(x, y + 1, z) == 0 && !this.isFullyGrown(world, x, y, z)) {
         world.setBlock(x, y, z, 4);
         world.setBlockMetadataWithNotify(x, y + 1, z, super.blockID, 5);
      }

   }

   public boolean canBlockStay(World world, int x, int y, int z) {
      Block blockBottom = Block.blocksList[world.getBlockId(x, y - 1, z)];
      return blockBottom != null && (blockBottom.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) || blockBottom == this)?world.getBlockLightValue(x, y, z) > 8 || world.canBlockSeeTheSky(x, y, z):false;
   }

   private float getGrowthRate(World world, int x, int y, int z) {
      float growth = 0.5F;

      for(int x1 = x - 1; x1 <= x + 1; ++x1) {
         for(int z1 = z - 1; z1 <= z + 1; ++z1) {
            int blockId = world.getBlockId(x1, y - 1, z1);
            float modifier = 0.0F;
            if(blockId == Block.tilledField.blockID) {
               modifier = 0.5F;
               if(world.getBlockMetadata(x1, y - 1, z1) > 0) {
                  modifier = 1.5F;
               }
            }

            growth += modifier;
         }
      }

      return growth / 6.0F;
   }

   /*public int getBlockTextureFromSideAndMetadata(int side, int meta) {
      return super.blockIndexInTexture + meta;
   }   */

   public int getRenderType() {
      return 1;
   }

   public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
      super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
   }

   public ArrayList getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
      ArrayList drop = new ArrayList();
      if(metadata > 4) {
         drop.add(new ItemStack(XyCraftWorldItems.corn));
      }

      for(int n = 0; n < 2 + fortune; ++n) {
         if(world.rand.nextInt(15) <= metadata) {
            drop.add(new ItemStack(XyCraftWorldItems.kernel));
         }
      }

      return drop;
   }

   public int idDropped(int meta, Random rand, int par3) {
      return meta == 5?XyCraftWorldItems.corn.itemID:-1;
   }

   public int quantityDropped(Random par1Random) {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public int idPicked(World par1World, int par2, int par3, int par4) {
      return Item.seeds.itemID;
   }

   public String getTextureFile() {
      return "/soaryn/xycraft/world/sprites/sprite_blocks.png";
   }
}
