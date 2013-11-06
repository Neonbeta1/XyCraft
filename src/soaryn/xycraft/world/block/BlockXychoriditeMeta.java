package soaryn.xycraft.world.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockXychoriditeMeta extends ItemBlock {

   public static final String[] blockType = new String[]{"BlueBrick", "GreenBrick", "RedBrick", "DarkBrick", "LightBrick", "BluePlate", "GreenPlate", "RedPlate", "DarkPlate", "LightPlate"};


   public BlockXychoriditeMeta(int i) {
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
