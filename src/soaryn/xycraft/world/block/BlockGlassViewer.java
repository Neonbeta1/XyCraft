package soaryn.xycraft.world.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.render.GlassConnectedTexture;

public class BlockGlassViewer extends Block {

   public BlockGlassViewer(int id, int icon) {
      super(id, Material.glass);
      //this.setTextureFile("/soaryn/xycraft/world/sprites/connectedtextures_glass_viewer.png");
      this.setCreativeTab(XyCraft.tabBlocks);
      this.setHardness(2.0F);
      this.setResistance(32.0F);
      MinecraftForge.setBlockHarvestLevel(this, "pickaxe", 0);
   }
    public void registerIcons(IconRegister icon) {
        blockIcon = icon.registerIcon("xycraft:connectedtextures_glass_viewer");
    }
   public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
      return false;
   }

   public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
      return true;
   }

   public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
      return true;
   }

   /*@SideOnly(Side.CLIENT)
   public int getBlockTexture(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
      return GlassConnectedTexture.getRenderer(super.blockID).getBlockTexture(iBlockAccess, x, y, z, side);
   }   */

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 0;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int par2, int par3, int par4, int par5) {
      return iBlockAccess.getBlockId(par2, par3, par4) != super.blockID;
   }
}
