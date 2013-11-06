package soaryn.xycraft.world.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import soaryn.util.UtilItem;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.world.item.ItemWorld;

public class ItemSulfurGoo extends ItemWorld {

   public ItemSulfurGoo(int id, int x, int y) {
      super(id, x, y, new String[]{"Seems to be a form of " + XyReferences.FormatText.formatS("red dye", new XyReferences.FormatText.EnumFormatting[]{XyReferences.FormatText.EnumFormatting.RED}), "Also seems to work as a " + XyReferences.FormatText.formatS("fertilizer", new XyReferences.FormatText.EnumFormatting[]{XyReferences.FormatText.EnumFormatting.LIME_GREEN})});
   }

   public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {
      if(UtilItem.emulateBonemeal(stack, player, world, x, y, z, Item.itemRand)) {
         if(!world.isRemote) {
            --stack.stackSize;
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean itemInteractionForEntity(ItemStack stack, EntityLiving ent) {
      if(ent instanceof EntitySheep) {
         EntitySheep sheep = (EntitySheep)ent;
         byte color = 14;
         if(!sheep.getSheared() && sheep.getFleeceColor() != color) {
            sheep.setFleeceColor(color);
            --stack.stackSize;
         }

         return true;
      } else {
         return false;
      }
   }
}
