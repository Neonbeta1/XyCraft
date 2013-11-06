package soaryn.xycraft.world.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockXyLightInvMeta extends ItemBlock {

   public static final String[] blockType = new String[]{"WhiteLight", "OrangeLight", "MagentaLight", "LightBlueLight", "YellowLight", "LimeGreenLight", "PinkLight", "GrayLight", "LightGrayLight", "CyanLight", "Purple", "BlueLight", "BrownLight", "GreenLight", "RedLight", "BlackLight"};


   public BlockXyLightInvMeta(int i) {
      super(i);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public int getMetadata(int meta) {
      return meta;
   }

   public String getUnlocalizedName(ItemStack itemstack) {
      return "aesth" + blockType[itemstack.getItemDamage()] + "Inv";
   }

}
