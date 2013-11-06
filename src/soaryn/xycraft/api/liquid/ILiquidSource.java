package soaryn.xycraft.api.liquid;

import net.minecraft.world.IBlockAccess;
import net.minecraftforge.liquids.LiquidStack;

public interface ILiquidSource {

   LiquidStack getLiquid(IBlockAccess var1, int var2, int var3, int var4);
}
