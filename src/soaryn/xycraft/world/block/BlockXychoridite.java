package soaryn.xycraft.world.block;

import codechicken.lib.colour.Colour;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.core.render.IAnimationSideHandler;
import soaryn.xycraft.world.block.BlockWorld;

public class BlockXychoridite extends BlockWorld implements IAnimationSideHandler {

    public BlockXychoridite(int id, int icon) {
        super(id, icon, Material.iron);
    }
    public void registerIcons(IconRegister icon) {
        blockIcon = icon.registerIcon("xycraft:xychrodite");
    }

    public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    public float getBlockHardness(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) < 5?2.0F:8.0F;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return world.getBlockMetadata(x, y, z) < 5?10.0F:32.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int meta) {
        return !XyConfig.lairaBricks && meta < 5?XyReferences.xyColors[meta % 5].copy().multiplyC(0.5D).rgb():super.getRenderColor(meta);
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        int meta = par1iBlockAccess.getBlockMetadata(par2, par3, par4);
        return !XyConfig.lairaBricks && meta < 5?XyReferences.xyColors[meta % 5].copy().multiplyC(0.5D).rgb():super.colorMultiplier(par1iBlockAccess, par2, par3, par4);
    }

    public int getBlockTextureFromSideAndMetadata(int side, int meta) {
        return meta < 5?(XyConfig.lairaBricks?48 + meta:33):34;
    }

    public int idDropped(int meta, Random random, int bonus) {
        return super.blockID;
    }

    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List itemList) {
        for(int meta = 0; meta < 10; ++meta) {
            itemList.add(new ItemStack(this, 1, meta));
        }

    }

    /*public int getRenderType() {
        return UtilRender.RenderIds.renderXy;
    }      */

    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z) {
        return false;
    }

    public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return worldObj.getBlockMetadata(x, y, z) > 4;
    }

    public Colour getColor(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
        return XyConfig.lairaBricks && iBlockAccess.getBlockMetadata(x, y, z) < 5?null:XyReferences.xyColors[iBlockAccess.getBlockMetadata(x, y, z) % 5];
    }

    public int getAnimationIndex(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
        return 255;
    }

    public int getAnimationBrightness(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side) {
        return iBlockAccess.getBlockMetadata(x, y, z) == 4?235:220;
    }

    public Colour getItemColor(int meta, ForgeDirection side) {
        return XyConfig.lairaBricks && meta < 5?null:XyReferences.xyColors[meta % 5];
    }

    public int getAnimationIndex(int meta, ForgeDirection side) {
        return 255;
    }

    public int getAnimationBrightness(int meta, ForgeDirection side) {
        return 255;
    }
}
