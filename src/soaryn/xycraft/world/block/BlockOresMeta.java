package soaryn.xycraft.world.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockOresMeta extends ItemBlock {

   public static final String[] blockType = new String[]{"BlueOre", "GreenOre", "RedOre", "DarkOre", "LightOre", "AluminumOre", "BlueBlock", "GreenBlock", "RedBlock", "DarkBlock", "LightBlock", "AluminumBlock"};


   public BlockOresMeta(int i) {
      super(i);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int metadata) {
      return metadata;
   }

   public String getUnlocalizedName(ItemStack itemstack) {
      return "xych" + blockType[itemstack.getItemDamage()];
   }

}
