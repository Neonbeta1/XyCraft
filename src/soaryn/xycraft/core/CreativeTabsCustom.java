package soaryn.xycraft.core;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsCustom extends CreativeTabs {

   private int itemId;
   private int meta;


   public CreativeTabsCustom setImageItem(int id, int meta) {
      this.itemId = id;
      this.meta = meta;
      return this;
   }

   public CreativeTabsCustom(String label) {
      super(label.toLowerCase().replace(' ', '_'));
      LanguageRegistry.instance().addStringLocalization("itemGroup." + label.toLowerCase().replace(' ', '_'), label);
   }

   public int getTabIconItemIndex() {
      return this.itemId;
   }

   public ItemStack getIconItemStack() {
      return new ItemStack(this.getTabIconItem(), 1, this.meta);
   }
}
