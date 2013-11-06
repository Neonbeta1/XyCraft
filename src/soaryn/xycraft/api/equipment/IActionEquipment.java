package soaryn.xycraft.api.equipment;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IActionEquipment {

   String getActionName();

   int getActionEnergyUse();

   ItemStack onActionRightClick(ItemStack var1, World var2, EntityPlayer var3);

   boolean onActionPlace(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10);

   boolean onActionUseFirst(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10);

   String getActionDescription();

   List getCompatibleEquipmentID();
}
