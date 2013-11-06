package soaryn.xycraft.world.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockXychoriditeDyeMeta extends ItemBlock {

   public static final String[] blockType = new String[]{"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime Green", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};


   public BlockXychoriditeDyeMeta(int i) {
      super(i);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int meta) {
      return meta;
   }

   public String getUnlocalizedName(ItemStack itemstack) {
      return "aesth" + blockType[itemstack.getItemDamage()].replace(' ', '_').toLowerCase() + "_xych";
   }

}
