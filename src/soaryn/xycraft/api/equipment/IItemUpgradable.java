package soaryn.xycraft.api.equipment;

import net.minecraft.item.ItemStack;
import soaryn.xycraft.api.equipment.EnumUpgradeType;

public interface IItemUpgradable {

   ItemStack getUpgradeStack(ItemStack var1, int var2);

   void setUpgradeStack(ItemStack var1, int var2, ItemStack var3);

   boolean hasUpgradeStack(ItemStack var1, ItemStack var2);

   boolean hasAnUpgradeInSlot(ItemStack var1, int var2);

   boolean hasUpgradeType(ItemStack var1, EnumUpgradeType var2);

   int getUpgradeCount(ItemStack var1);

   int getUpgradeCountStack(ItemStack var1, ItemStack var2);

   String getEquipmentID();
}
