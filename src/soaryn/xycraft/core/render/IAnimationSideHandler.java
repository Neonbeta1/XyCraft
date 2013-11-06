package soaryn.xycraft.core.render;

import codechicken.lib.colour.Colour;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

public interface IAnimationSideHandler {

   Colour getItemColor(int var1, ForgeDirection var2);

   int getAnimationIndex(int var1, ForgeDirection var2);

   int getAnimationBrightness(int var1, ForgeDirection var2);

   Colour getColor(IBlockAccess var1, int var2, int var3, int var4, ForgeDirection var5);

   int getAnimationIndex(IBlockAccess var1, int var2, int var3, int var4, ForgeDirection var5);

   int getAnimationBrightness(IBlockAccess var1, int var2, int var3, int var4, ForgeDirection var5);
}
