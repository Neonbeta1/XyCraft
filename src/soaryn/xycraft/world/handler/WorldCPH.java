package soaryn.xycraft.world.handler;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetClientHandler;
import soaryn.xycraft.world.XyCraftWorld;

public class WorldCPH implements PacketCustom.IClientPacketHandler {

   public static final Object channel = XyCraftWorld.instance;


   public void handlePacket(PacketCustom packet, NetClientHandler nethandler, Minecraft mc) {
      packet.getType();
   }

}
