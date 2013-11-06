package soaryn.xycraft.world.handler;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;
import soaryn.xycraft.world.XyCraftWorldItems;

public class FuelHandlerWorld implements IFuelHandler {

   public int getBurnTime(ItemStack fuel) {
      return fuel != null && fuel.getItem() == XyCraftWorldItems.henequenLeaf?100:0;
   }
}
