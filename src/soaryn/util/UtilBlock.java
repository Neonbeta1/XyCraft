package soaryn.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class UtilBlock
{
    public static boolean cycle2DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, IBlockCycle cycle)
    {
        x += (side.offsetX == 0 ? radius / 2 : 0);
        y += (side.offsetY == 0 ? radius / 2 : 0);
        z += (side.offsetZ == 0 ? radius / 2 : 0);

        boolean isSuccessful = false;
        for (int r = 0; r < radius; r++) {
            for (int c = 0; c < radius; c++) {
                int i = side.offsetX == 0 ? r : 0;
                int j = side.offsetY == 0 ? c : side.offsetX != 0 ? r : 0;
                int k = side.offsetZ == 0 ? c : 0;
                if ((cycle.execute(player, world, x - i, y - j, z - k, side)) && (!isSuccessful)) {
                    isSuccessful = true;
                }
            }
        }
        return isSuccessful;
    }

    public static boolean cycle3DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int distance, int radius, IBlockCycle cycle)
    {
        boolean isSuccessful = false;
        for (int h = 0; h < distance; h++) {
            int i = side.offsetX * h;
            int j = side.offsetY * h;
            int k = side.offsetZ * h;
            if ((cycle2DBlock(player, world, x - i, y - j, z - k, side, radius, cycle)) && (!isSuccessful)) {
                isSuccessful = true;
            }
        }
        return isSuccessful;
    }

    public static boolean isBlockIndirectlyGettingPowered(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
            if ((side != ForgeDirection.DOWN) && (isRecieveingRedstonePower(iBlockAccess, x + side.offsetX, y + side.offsetY, z + side.offsetZ))) return true;
        }
        return isBlockGettingPowered(iBlockAccess, x, y, z);
    }

    public static int isBlockIndirectlyProvidingPowerTo(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side)
    {
        Block block = Block.blocksList[iBlockAccess.getBlockId(x, y, z)];
        if (block != null) return block.isProvidingWeakPower(iBlockAccess, x, y, z, side.ordinal());
        return x;
    }

    public static boolean isBlockGettingPowered(IBlockAccess iBlockAccess, int x, int y, int z)
    {
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {

            if ((side != ForgeDirection.DOWN) && (isRecieveingRedstonePower(iBlockAccess, x + side.offsetX, y + side.offsetY, z + side.offsetZ))) return true;
        }
        return false;
    }

    public static boolean isRecieveingRedstonePower(IBlockAccess iBlockAccess, int x, int y, int z) {
        int id = iBlockAccess.getBlockId(x, y, z);
        if (id == Block.redstoneWire.blockID) {
            int meta = iBlockAccess.getBlockMetadata(x, y, z);
            if (meta > 0) return true;
        }
        return false;
    }

    public static int isBlockProvidingPowerTo(IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side)
    {
        Block block = Block.blocksList[iBlockAccess.getBlockId(x, y, z)];
        if (block != null) return block.isProvidingStrongPower(iBlockAccess, x, y, z, side.ordinal());

        return x;
    }

    public static abstract interface IBlockCycle
    {
        public abstract boolean execute(EntityPlayer paramEntityPlayer, World paramWorld, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection);
    }
}