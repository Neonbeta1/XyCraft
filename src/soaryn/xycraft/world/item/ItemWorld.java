package soaryn.xycraft.world.item;

import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.XyItem;

public class ItemWorld extends XyItem {

   private List hiddenInfo;


   public ItemWorld(int id, int x, int y) {
      super(id);
     // this.setIconCoord(x, y);
      this.setCreativeTab(XyCraft.tabItems);
     // this.setTextureFile("/soaryn/xycraft/world/sprites/sprite_items.png");
   }

   public ItemWorld(int id, int x, int y, String ... message) {
      this(id, x, y);
      this.hiddenInfo = Arrays.asList(message);
   }

   public List getItemInfo(ItemStack stack) {
      return this.hiddenInfo;
   }


}
