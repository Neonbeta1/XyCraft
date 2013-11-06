package soaryn.xycraft.api.liquid;

import net.minecraftforge.liquids.LiquidStack;

public class LiquidObject {

   public String name;
   public LiquidStack liquid;


   public LiquidObject(String name, LiquidStack liquid) {
      this.name = name;
      this.liquid = liquid;
   }
}
