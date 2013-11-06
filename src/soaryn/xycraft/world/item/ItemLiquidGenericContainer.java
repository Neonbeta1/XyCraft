package soaryn.xycraft.world.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import soaryn.xycraft.api.liquid.XyCraftLiquidRegistery;
import soaryn.xycraft.world.item.ItemWorld;

public class ItemLiquidGenericContainer extends ItemWorld {

   private List liquidName = new ArrayList();


   public ItemLiquidGenericContainer(int id) {
      super(id, 6, 2);
      this.setHasSubtypes(true);
   }

   public void onUpdate(ItemStack stack, World par2World, Entity ent, int par4, boolean par5) {
      if(ent instanceof EntityPlayer) {
         ((EntityPlayer)ent).inventory.setInventorySlotContents(par4, (ItemStack)null);
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean debug) {
      NBTTagCompound tag = initTag(stack);
   }

   public String getItemNameIS(ItemStack stack) {
      return super.getUnlocalizedName() + "." + "foil" + getLiquidStackAsItem(stack).getDisplayName();
   }

   public static NBTTagCompound initTag(ItemStack stack) {
      NBTTagCompound tag = stack.stackTagCompound;
      if(tag == null) {
         tag = new NBTTagCompound();
         stack.setTagCompound(tag);
      }

      return tag;
   }

   private static ItemStack getLiquidStackAsItem(ItemStack container) {
      LiquidStack liquid = getLiquidStack(container);
      return new ItemStack(liquid.itemID, liquid.amount, liquid.itemMeta);
   }

   private static LiquidStack getLiquidStack(ItemStack container) {
      return LiquidStack.loadLiquidStackFromNBT(initTag(container).getCompoundTag("liquid"));
   }

   private static void setLiquidStack(ItemStack container, LiquidStack liquid) {
      initTag(container).setCompoundTag("liquid", liquid.writeToNBT(new NBTTagCompound()));
   }

   public static ItemStack createLiquidContainer(int id, LiquidStack liquid) {
      ItemStack stack = new ItemStack(id, 1, 0);
      setLiquidStack(stack, liquid);
      return stack;
   }

   @SideOnly(Side.CLIENT)
   public void getSubItems(int id, CreativeTabs par2CreativeTabs, List list) {
      boolean i = false;
      if(id != super.itemID) {
         Map liquidMap = LiquidDictionary.getLiquids();
         Iterator i$ = liquidMap.entrySet().iterator();

         while(i$.hasNext()) {
            Entry liquid = (Entry)i$.next();
            List blackList = XyCraftLiquidRegistery.getContainerLevelList(XyCraftLiquidRegistery.EnumLiquidContainerLevel.NONE);
            if(!blackList.contains(liquid.getKey())) {
               list.add(createLiquidContainer(id, (LiquidStack)liquid.getValue()));
            }
         }

      }
   }
}
