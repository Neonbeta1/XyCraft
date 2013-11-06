package soaryn.util;

import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import soaryn.xycraft.world.XyCraftWorldItems;

public class UtilLiquid {

   public static boolean areLiquidStacksEqual(LiquidStack ... liquids) {
      LiquidStack baseLiquid = liquids[0];
      LiquidStack[] arr$ = liquids;
      int len$ = liquids.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         LiquidStack liquid = arr$[i$];
         if(baseLiquid.itemID != liquid.itemID || baseLiquid.itemMeta != liquid.itemMeta) {
            return false;
         }
      }

      return true;
   }

   public static boolean isDye(LiquidStack liquid) {
      return liquid.itemID == XyCraftWorldItems.liquids.itemID && liquid.itemMeta < 16;
   }

   public static LiquidStack getPrimer() {
      return LiquidDictionary.getLiquid("Primer", 1000);
   }

   public static LiquidStack getNitrogen() {
      return LiquidDictionary.getLiquid("Nitrogen", 1000);
   }

   public static LiquidStack getLiquidNitrogen() {
      return LiquidDictionary.getLiquid("Liquid Nitrogen", 1000);
   }
}
