package soaryn.xycraft.core.handler;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.EnumSet;

@SideOnly(Side.CLIENT)
public class RenderTickHandler implements ITickHandler {

   private static long clientTime;


   public RenderTickHandler() {
      clientTime = 0L;
   }

   public void tickStart(EnumSet type, Object ... tickData) {}

   public void tickEnd(EnumSet type, Object ... tickData) {
      ++clientTime;
   }

   public EnumSet ticks() {
      return EnumSet.of(TickType.CLIENT);
   }

   public String getLabel() {
      return "renderTime";
   }

   public static long getTime() {
      return clientTime;
   }
}
