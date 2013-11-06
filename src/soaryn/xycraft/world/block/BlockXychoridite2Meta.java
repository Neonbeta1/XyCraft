package soaryn.xycraft.world.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockXychoridite2Meta extends ItemBlock {

   public static final String[] blockType = new String[]{"BluePlat", "GreenPlat", "RedPlat", "DarkPlat", "LightPlat", "BlueShield", "GreenShield", "RedShield", "DarkShield", "LightShield"};


   public BlockXychoridite2Meta(int i) {
      super(i);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int meta) {
      return meta;
   }

   public String getUnlocalizedName(ItemStack itemstack) {
      return "aesth" + blockType[itemstack.getItemDamage()];
   }

}
