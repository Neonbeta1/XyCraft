package soaryn.xycraft.world.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.render.FXHandler;
import soaryn.xycraft.world.XyCraftWorld;
import soaryn.xycraft.world.block.BlockWorld;
import soaryn.xycraft.world.block.TileCrystal;

public class BlockCrystal extends BlockWorld {

   public BlockCrystal(int id) {
      super(id, 15, Material.glass);
      this.setLightValue(0.65F);
      this.setTickRandomly(true);
      this.setStepSound(Block.soundGlassFootstep);
      this.setHardness(0.2F);
   }

   @SideOnly(Side.CLIENT)
   public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
      FXHandler.sulfurTorchFx(world, (float)x - 0.25F, (float)y, (float)z - 0.15F);
      return true;
   }

   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity ent) {
      super.onEntityCollidedWithBlock(par1World, par2, par3, par4, ent);
      ent.attackEntityFrom(XyCraftWorld.crystal, 1);
   }

   public int getRenderType() {
      return UtilRender.RenderIds.renderCrystal;
   }

   public boolean hasTileEntity(int metadata) {
      return true;
   }

   public int quantityDropped(int meta, int fortune, Random random) {
      return 0;
   }

   public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
      this.dropBlockAsItem(world, x, y, z, side, 0);
   }

   public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {}

   public ArrayList getBlockDropped(World world, int x, int y, int z, int md, int fortune) {
      ArrayList items = new ArrayList();
      if(world.isRemote) {
         return items;
      } else {
         int quantity = getQuantity(world, x, y, z) + world.rand.nextInt(fortune + 1);
         items.add(new ItemStack(this, quantity));
         return items;
      }
   }

   public static int genQuantity(int x, int y, int z) {
      return Math.abs((x + y + z) % 3) + 1;
   }

   public static int getQuantity(IBlockAccess world, int x, int y, int z) {
      TileEntity tile = world.getBlockTileEntity(x, y, z);
      return tile instanceof TileCrystal?((TileCrystal)tile).quantity:genQuantity(x, y, z);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean canPlaceBlockAt(World world, int x, int y, int z) {
      return this.canExist(world, x, y, z);
   }

   public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
      ForgeDirection fside = ForgeDirection.getOrientation(side);
      ForgeDirection iside = fside.getOpposite();
      return world.isBlockSolidOnSide(x + iside.offsetX, y + iside.offsetY, z + iside.offsetZ, fside);
   }

   public boolean canPlaceOnSide(World world, int x, int y, int z, ForgeDirection fside) {
      ForgeDirection iside = fside.getOpposite();
      return world.isBlockSolidOnSide(x + iside.offsetX, y + iside.offsetY, z + iside.offsetZ, fside);
   }

   private boolean canExist(World world, int x, int y, int z) {
      boolean rechecking = world.getBlockId(x, y, z) == super.blockID;
      if(!rechecking) {
         ForgeDirection[] var10 = ForgeDirection.VALID_DIRECTIONS;
         int len$ = var10.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            ForgeDirection fside = var10[i$];
            if(this.canPlaceOnSide(world, x, y, z, fside)) {
               return true;
            }
         }

         return false;
      } else {
         int arr$ = world.getBlockMetadata(x, y, z);
         return arr$ < 6 && this.canPlaceOnSide(world, x, y, z, ForgeDirection.getOrientation(arr$));
      }
   }

   public void onNeighborBlockChange(World world, int x, int y, int z, int side) {
      if(!this.canPlaceOnSide(world, x, y, z, ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)))) {
         this.dropBlockAsItem(world, x, y, z, 0, 0);
         world.setBlock(x, y, z, 0);
      }

   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving par5EntityLiving) {
      world.setBlockTileEntity(x, y, z, new TileCrystal());
   }

   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
      return true;
   }

   public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
      ForgeDirection fside = ForgeDirection.getOrientation(side);
      if(this.canPlaceOnSide(world, x, y, z, fside)) {
         return fside.ordinal();
      } else {
         ForgeDirection[] arr$ = ForgeDirection.VALID_DIRECTIONS;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            ForgeDirection fside2 = arr$[i$];
            if(this.canPlaceOnSide(world, x, y, z, fside2)) {
               return fside2.ordinal();
            }
         }

         return 0;
      }
   }

   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
      FXHandler.crystalSparkleFx(world, x, y, z, 6);
   }

   public void addCollidingBlockToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity ent) {
      world.getBlockMetadata(i, j, k);
      float hMin = 0.0F;
      float hMax = 1.0F;
      float wMin = 0.3F;
      float wMax = 0.7F;
      int orient = world.getBlockMetadata(i, j, k);
      switch(orient) {
      case 0:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax, wMax);
         break;
      case 1:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax, wMax);
         break;
      case 2:
      case 3:
         this.setBlockBounds(wMin, wMin, hMin, wMax, wMax, wMax);
         break;
      case 4:
      case 5:
         this.setBlockBounds(hMin, wMin, wMin, wMax, wMax, wMax);
      }

      //super.addCollidingBlockToList(world, i, j, k, axisalignedbb, arraylist, ent);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int i, int j, int k) {
      float hMin = 0.0F;
      float hMax = 0.6F;
      float wMin = 0.3F;
      float wMax = 0.7F;
      int orient = iBlockAccess.getBlockMetadata(i, j, k);
      TileEntity tile = iBlockAccess.getBlockTileEntity(i, j, k);
      int cluster = tile instanceof TileCrystal?((TileCrystal)tile).quantity:(i * 31 ^ j) * 31 ^ k;
      wMin -= cluster == 1?0.0F:0.1F;
      wMax += cluster == 1?0.0F:0.1F;
      switch(orient) {
      case 0:
         this.setBlockBounds(wMin, 1.0F - hMax, wMin, wMax, 1.0F, wMax);
         break;
      case 1:
         this.setBlockBounds(wMin, hMin, wMin, wMax, hMax, wMax);
         break;
      case 2:
         this.setBlockBounds(wMin, wMin, 1.0F - hMax, wMax, wMax, 1.0F);
         break;
      case 3:
         this.setBlockBounds(wMin, wMin, 0.0F, wMax, wMax, hMax);
         break;
      case 4:
         this.setBlockBounds(1.0F - hMax, wMin, wMin, 1.0F, wMax, wMax);
         break;
      case 5:
         this.setBlockBounds(hMin, wMin, wMin, hMax, wMax, wMax);
      }

   }

   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
      return new ItemStack(this, 1, 0);
   }
}
