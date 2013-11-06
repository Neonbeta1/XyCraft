package soaryn.xycraft.world.handler;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import soaryn.xycraft.world.handler.WorldSPH;

public class ProxyWorld implements IGuiHandler {

   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }

   public void registerPacketHandler() {
      PacketCustom.assignHandler(WorldSPH.channel, new WorldSPH());
   }
}
