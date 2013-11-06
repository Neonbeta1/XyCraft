package soaryn.xycraft.world.block;

import codechicken.lib.colour.Colour;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import soaryn.util.UtilBlock;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.core.render.IAnimationSideHandler;
import soaryn.xycraft.world.block.BlockWorld;

public class BlockXyLight extends BlockWorld implements IAnimationSideHandler {

   private boolean defaultState;


   public BlockXyLight(int id, int icon, boolean defaultState) {
      super(id, icon, Material.rock);
      this.defaultState = defaultState;
      //this.setTextureFile("/soaryn/xycraft/world/sprites/sprite_blocks.png");
   }

   public int damageDropped(int meta) {
      return meta;
   }

   public int getRenderType() {
      return UtilRender.RenderIds.renderXy;
   }

   public boolean getDefaultState() {
      return this.defaultState;
   }

   public void onNeighborBlockChange(World world, int x, int y, int z, int side) {
      super.onNeighborBlockChange(world, x, y, z, side);
      world.markBlockForUpdate(x, y, z);
   }

   public int getLightValue(IBlockAccess iBlockAccess, int x, int y, int z) {
      return this.getToggle(iBlockAccess, x, y, z)?15:super.getLightValue(iBlockAccess, x, y, z);
   }

   public boolean canProvidePower() {
      return true;
   }

   public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
      return true;
   }

   private boolean getToggle(IBlockAccess iBlockAccess, int x, int y, int z) {
      return this.defaultState && !UtilBlock.isBlockIndirectlyGettingPowered(iBlockAccess, x, y, z)?true:!this.defaultState && UtilBlock.isBlockIndirectlyGettingPowered(iBlockAccess, x, y, z);
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List itemList) {
      for(int meta = 0; meta < 16; ++meta) {
         itemList.add(new ItemStack(this, 1, meta));
      }

   }

   public Colour getColor(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return XyReferences.mcColors[iBlockAccess.getBlockMetadata(x, y, z)];
   }

   public int getAnimationIndex(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return 255;
   }

   public int getAnimationBrightness(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
      return this.getToggle(iBlockAccess, x, y, z)?220:75;
   }

   public Colour getItemColor(int meta, ForgeDirection side) {
      return XyReferences.mcColors[meta];
   }

   public int getAnimationIndex(int meta, ForgeDirection side) {
      return 255;
   }

   public int getAnimationBrightness(int meta, ForgeDirection side) {
      return this.defaultState?220:75;
   }
}
