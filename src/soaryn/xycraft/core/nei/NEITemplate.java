package soaryn.xycraft.core.nei;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import soaryn.xycraft.core.HiddenID;

public class NEITemplate {

   public List getHiddenIDs(Class className) {
      ArrayList ids = new ArrayList();
      Field[] arr$ = className.getFields();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Field f = arr$[i$];
         if(f.isAnnotationPresent(HiddenID.class)) {
            String fieldName = f.getName();

            try {
               Block ex = (Block)className.getField(fieldName).get((Object)null);
               if(ex != null) {
                  ids.add(Integer.valueOf(ex.blockID));
               }
            } catch (Throwable var10) {
               System.out.printf("Test %s failed: %s %n", new Object[]{f, var10.getCause()});
            }

            try {
               Item var11 = (Item)className.getField(fieldName).get((Object)null);
               if(var11 != null) {
                  ids.add(Integer.valueOf(var11.itemID));
               }
            } catch (Throwable var9) {
               ;
            }
         }
      }

      return ids;
   }
}
