package soaryn.xycraft.api.liquid;

import java.util.ArrayList;
import java.util.List;

public class XyCraftLiquidRegistery {

   protected static List noneList = new ArrayList();
   protected static List strongList = new ArrayList();


   public static void addLiquid(String liquidName, XyCraftLiquidRegistery.EnumLiquidContainerLevel level) {
      if(liquidName != null) {
         switch(XyCraftLiquidRegistery.NamelessClass1666776894.$SwitchMap$soaryn$xycraft$api$liquid$XyCraftLiquidRegistery$EnumLiquidContainerLevel[level.ordinal()]) {
         case 1:
            noneList.add(liquidName);
            break;
         case 2:
            strongList.add(liquidName);
         }

      }
   }

   public static List getContainerLevelList(XyCraftLiquidRegistery.EnumLiquidContainerLevel level) {
      switch(XyCraftLiquidRegistery.NamelessClass1666776894.$SwitchMap$soaryn$xycraft$api$liquid$XyCraftLiquidRegistery$EnumLiquidContainerLevel[level.ordinal()]) {
      case 1:
         return noneList;
      case 2:
         return strongList;
      default:
         return null;
      }
   }


   public static enum EnumLiquidContainerLevel {

      NONE("NONE", 0),
      STRONG("STRONG", 1);
      // $FF: synthetic field
      private static final XyCraftLiquidRegistery.EnumLiquidContainerLevel[] $VALUES = new XyCraftLiquidRegistery.EnumLiquidContainerLevel[]{NONE, STRONG};


      private EnumLiquidContainerLevel(String var1, int var2) {}

   }

   // $FF: synthetic class
   static class NamelessClass1666776894 {

      // $FF: synthetic field
      static final int[] $SwitchMap$soaryn$xycraft$api$liquid$XyCraftLiquidRegistery$EnumLiquidContainerLevel = new int[XyCraftLiquidRegistery.EnumLiquidContainerLevel.values().length];


      static {
         try {
            $SwitchMap$soaryn$xycraft$api$liquid$XyCraftLiquidRegistery$EnumLiquidContainerLevel[XyCraftLiquidRegistery.EnumLiquidContainerLevel.NONE.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$soaryn$xycraft$api$liquid$XyCraftLiquidRegistery$EnumLiquidContainerLevel[XyCraftLiquidRegistery.EnumLiquidContainerLevel.STRONG.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}
