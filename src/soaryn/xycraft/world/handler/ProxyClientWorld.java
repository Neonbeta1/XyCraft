package soaryn.xycraft.world.handler;

import codechicken.lib.packet.PacketCustom;
import soaryn.xycraft.world.handler.ProxyWorld;
import soaryn.xycraft.world.handler.WorldCPH;

public class ProxyClientWorld extends ProxyWorld {

   public void registerPacketHandler() {
      super.registerPacketHandler();
      PacketCustom.assignHandler(WorldCPH.channel, new WorldCPH());
   }
}
