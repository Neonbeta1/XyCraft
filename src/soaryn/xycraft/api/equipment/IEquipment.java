package soaryn.xycraft.api.equipment;

import net.minecraft.item.ItemStack;
import soaryn.xycraft.api.equipment.IActionEquipment;

public interface IEquipment {

   String getEquipmentName();

   float getEnergyUse();

   IActionEquipment getLoadedAction(ItemStack var1);
}
