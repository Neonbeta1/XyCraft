package soaryn.xycraft.api.equipment;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import soaryn.xycraft.api.equipment.EnumUpgradeType;
import soaryn.xycraft.api.equipment.IActionEquipment;
import soaryn.xycraft.api.equipment.IItemUpgradable;

public interface IItemUpgrade {

   EnumUpgradeType getUpgradeType();

   @Deprecated
   Item setAction(IActionEquipment var1);

   IActionEquipment getAction(ItemStack var1, IItemUpgradable var2, ItemStack var3);

   List getCompatibleEquipmentID(ItemStack var1);

   @Deprecated
   List getProperties(ItemStack var1);
}
