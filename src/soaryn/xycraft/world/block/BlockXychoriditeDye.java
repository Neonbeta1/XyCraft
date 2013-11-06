package soaryn.xycraft.world.block;

import codechicken.lib.colour.Colour;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.core.render.IAnimationSideHandler;
import soaryn.xycraft.world.block.BlockWorld;

public class BlockXychoriditeDye extends BlockWorld implements IAnimationSideHandler {

   public BlockXychoriditeDye(int id, int icon) {
      super(id, icon, Material.iron);
      this.setLightValue(0.0F);
   }

   public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
      return 10.0F;
   }

   public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
   }

   public float getBlockHardness(World world, int x, int y, int z) {
      return 2.0F;
   }

   public int getBlockTextureFromSideAndMetadata(int side, int meta) {
      return 37;
   }

   public int idDropped(int meta, Random random, int bonus) {
      return super.blockID;
   }

   public int damageDropped(int meta) {
      return meta;
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List itemList) {
      for(int meta = 0; meta < 16; ++meta) {
         itemList.add(new ItemStack(this, 1, meta));
      }

   }

   public int getRenderType() {
      return UtilRender.RenderIds.renderXy;
   }

   public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
      return false;
   }

   public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
      return true;
   }

   public Colour getColor(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return XyReferences.mcColors[iBlockAccess.getBlockMetadata(x, y, z)];
   }

   public int getAnimationIndex(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return 255;
   }

   public int getAnimationBrightness(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return 220;
   }

   public Colour getItemColor(int meta, ForgeDirection side) {
      return XyReferences.mcColors[meta];
   }

   public int getAnimationIndex(int meta, ForgeDirection side) {
      return 255;
   }

   public int getAnimationBrightness(int meta, ForgeDirection side) {
      return 220;
   }
}
