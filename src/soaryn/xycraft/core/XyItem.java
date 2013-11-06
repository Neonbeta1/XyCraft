package soaryn.xycraft.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.lib.XyReferences;

public abstract class XyItem extends Item {

   public XyItem(int id) {
      super(id);
      this.setCreativeTab(XyCraft.tabItems);
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
      List itemInfo = this.getItemInfo(stack);
      super.addInformation(stack, player, list, show);
      if(itemInfo != null && itemInfo.size() != 0) {
         if(itemInfo.size() > 1) {
            if(!GuiScreen.isShiftKeyDown() && !show) {
               list.add(XyReferences.FormatText.formatS("Press ", new XyReferences.FormatText.EnumFormatting[]{XyReferences.FormatText.EnumFormatting.GREY}) + XyReferences.FormatText.formatS("Shift ", new XyReferences.FormatText.EnumFormatting[]{XyReferences.FormatText.EnumFormatting.LIME_GREEN, XyReferences.FormatText.EnumFormatting.ITALIC}) + XyReferences.FormatText.formatS("for info", new XyReferences.FormatText.EnumFormatting[]{XyReferences.FormatText.EnumFormatting.GREY}));
            } else {
               Iterator i$ = itemInfo.iterator();

               while(i$.hasNext()) {
                  String info = (String)i$.next();
                  if(info != null && !info.equals("")) {
                     list.add(info);
                  }
               }
            }
         } else {
            list.add(this.getItemInfo(stack).get(0));
         }

      }
   }

   public abstract List getItemInfo(ItemStack var1);
}
