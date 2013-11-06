package soaryn.xycraft.core.handler;

//import codechicken.lib.inventory.ContainerExtended;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.NetServerHandler;
import soaryn.xycraft.core.XyCraft;

public class XycraftSPH implements PacketCustom.IServerPacketHandler {

   public static final Object channel = XyCraft.instance;


   public void handlePacket(PacketCustom packet, NetServerHandler nethandler, EntityPlayerMP sender) {
      switch(packet.getType()) {
      case 6:
         if(sender.openContainer instanceof Container) {
           // ((Container)sender.openContainer).handleGuiChange(packet.readInt(), packet.readInt());
         }
      default:
      }
   }

}
