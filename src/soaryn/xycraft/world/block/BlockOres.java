package soaryn.xycraft.world.block;

import codechicken.lib.colour.Colour;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.core.render.IAnimationSideHandler;
import soaryn.xycraft.world.XyCraftWorldItems;
import soaryn.xycraft.world.block.BlockWorld;

public class BlockOres extends BlockWorld implements IAnimationSideHandler {
    private Icon[] icons;
   public BlockOres(int id, int icon) {
      super(id, icon, Material.iron);
      this.setHardness(1.5F);
      this.setResistance(8.0F);
      this.setTickRandomly(true);
   }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        icons = new Icon[12];

        for(int i = 0; i < icons.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon("xycraft:" + (this.getUnlocalizedName().substring(12)) + i);
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return icons[par2];
    }

   /*public int getRenderType() {
      return UtilRender.RenderIds.renderXy;
   }       */

   /*public int getBlockTextureFromSideAndMetadata(int side, int meta) {
      return meta < 6?super.blockIndexInTexture + meta % 7:(meta == 11?21:32);
   }      */

   public int quantityDropped(int meta, int fortune, Random random) {
      return meta < 5?3 + random.nextInt(2):1;
   }

   public int damageDropped(int meta) {
      return meta < 5?0:meta;
   }

   public int idDropped(int meta, Random random, int bonus) {
      switch(meta) {
      case 0:
         return XyCraftWorldItems.blueXychorium.itemID;
      case 1:
         return XyCraftWorldItems.greenXychorium.itemID;
      case 2:
         return XyCraftWorldItems.redXychorium.itemID;
      case 3:
         return XyCraftWorldItems.darkXychorium.itemID;
      case 4:
         return XyCraftWorldItems.lightXychorium.itemID;
      default:
         return super.blockID;
      }
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List itemList) {
      for(int meta = 0; meta < 12; ++meta) {
         itemList.add(new ItemStack(this, 1, meta));
      }

   }

   public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
      return world.getBlockMetadata(x, y, z) < 6;
   }

   public boolean isBeaconBase(World world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
      return world.getBlockMetadata(x, y, z) >= 6;
   }

   public Colour getColor(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      int meta = iBlockAccess.getBlockMetadata(x, y, z);
      return meta < 5?XyReferences.xyColors[meta % 5]:(meta > 5 && meta < 11?XyReferences.xyColors[(meta - 6) % 5]:null);
   }

   public int getAnimationIndex(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return iBlockAccess.getBlockMetadata(x, y, z) < 6?20:255;
   }

   public int getAnimationBrightness(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return iBlockAccess.getBlockMetadata(x, y, z) < 6?200:220;
   }

   public Colour getItemColor(int meta, ForgeDirection side) {
      return meta < 5?XyReferences.xyColors[meta % 5]:(meta > 5 && meta < 11?XyReferences.xyColors[(meta - 6) % 5]:null);
   }

   public int getAnimationIndex(int meta, ForgeDirection side) {
      return meta < 6?20:255;
   }

   public int getAnimationBrightness(int meta, ForgeDirection side) {
      return meta < 6?220:200;
   }
}
