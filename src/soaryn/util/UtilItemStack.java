package soaryn.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class UtilItemStack {

   private static ItemStack localStack;


   public static String getItemStackDispName(int id, int meta) {
      if(localStack == null) {
         localStack = new ItemStack(id, 1, meta);
      } else {
         localStack.itemID = id;
         localStack.setItemDamage(meta);
      }

      return localStack.getDisplayName();
   }

   public static boolean areStacksSimilar(ItemStack stack1, ItemStack stack2) {
      return stack1 == stack2?true:(stack1 != null && stack2 != null?stack1.itemID == stack2.itemID && stack1.getItemDamage() == stack2.getItemDamage():false);
   }

   public static NBTTagCompound initTag(ItemStack stack) {
      if(stack == null) {
         return null;
      } else {
         NBTTagCompound tag = stack.stackTagCompound;
         if(tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
         }

         return tag;
      }
   }

   public static void writeStackToNBT(ItemStack equipment, ItemStack upgrade, String key) {
      if(upgrade == null) {
         initTag(equipment).removeTag(key);
      } else {
         initTag(equipment).setCompoundTag(key, upgrade.writeToNBT(new NBTTagCompound()));
      }

   }

   public static ItemStack loadStackfromNBT(ItemStack equipment, String key) {
      return ItemStack.loadItemStackFromNBT(initTag(equipment).getCompoundTag(key));
   }

   public static float getFloat(ItemStack stack, String key) {
      return initTag(stack).getFloat(key);
   }

   public static void setFloat(ItemStack stack, String key, float value) {
      initTag(stack).setFloat(key, value);
   }

   public static boolean removeFloat(ItemStack stack, String key) {
      NBTTagCompound tag = initTag(stack);
      if(tag.hasKey(key)) {
         tag.removeTag(key);
         return true;
      } else {
         return false;
      }
   }
}
