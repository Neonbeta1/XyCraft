package soaryn.xycraft.world.handler;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetServerHandler;
import soaryn.xycraft.world.XyCraftWorld;

public class WorldSPH implements PacketCustom.IServerPacketHandler {

   public static final Object channel = XyCraftWorld.instance;


   public void handlePacket(PacketCustom packet, NetServerHandler nethandler, EntityPlayerMP sender) {
      packet.getType();
   }

}
