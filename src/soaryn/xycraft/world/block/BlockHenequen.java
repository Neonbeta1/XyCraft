package soaryn.xycraft.world.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import soaryn.xycraft.world.XyCraftWorldItems;

public class BlockHenequen extends BlockCrops {

   public BlockHenequen(int id, int index) {
      super(id);
      this.setCreativeTab((CreativeTabs)null);
      this.setTickRandomly(true);
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
      case 2:
      case 3:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax - 0.8F, wMax);
         break;
      case 4:
      case 5:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax - 0.4F, wMax);
         break;
      case 6:
      case 7:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax - 0.2F, wMax);
      }

   }

   protected boolean canThisPlantGrowOnThisBlockID(int blockId) {
      return blockId == Block.tilledField.blockID;
   }

   public boolean isFullyGrown(World world, int x, int y, int z) {
      return world.getBlockId(x, y, z) == super.blockID && world.getBlockMetadata(x, y, z) == 7;
   }

   public void fertilize(World world, int x, int y, int z) {
      world.setBlock(x, y, z, 7);
   }

   /*public int getBlockTextureFromSideAndMetadata(int side, int meta) {
      return super.blockIndexInTexture + meta;
   }   */

   public int getRenderType() {
      return 6;
   }

   public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
      super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
   }

   public ArrayList getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
      ArrayList drop = new ArrayList();
      if(metadata == 7) {
         drop.add(new ItemStack(XyCraftWorldItems.henequenLeaf));
      }

      for(int n = 0; n < 4 + fortune; ++n) {
         if(world.rand.nextInt(15) <= metadata) {
            drop.add(new ItemStack(XyCraftWorldItems.henequenSeeds));
         }
      }

      return drop;
   }

   public int idDropped(int meta, Random rand, int par3) {
      return meta == 7?XyCraftWorldItems.henequenLeaf.itemID:-1;
   }

   public int quantityDropped(Random par1Random) {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public int idPicked(World par1World, int par2, int par3, int par4) {
      return XyCraftWorldItems.henequenSeeds.itemID;
   }

   public EnumPlantType getPlantType(World world, int x, int y, int z) {
      return EnumPlantType.Crop;
   }

   public String getTextureFile() {
      return "/soaryn/xycraft/world/sprites/sprite_blocks.png";
   }
}
