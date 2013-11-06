package soaryn.xycraft.core.handler;

//import codechicken.lib.inventory.ContainerExtended;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.vec.BlockCoord;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.handler.ICustomPacketTile;

public class XycraftCPH implements PacketCustom.IClientPacketHandler {

   public static final Object channel = XyCraft.instance;


   public void handlePacket(PacketCustom packet, NetClientHandler nethandler, Minecraft mc) {
      switch(packet.getType()) {
      case 2:
         this.handleContainerPacket(mc.thePlayer, packet);
         break;
      case 5:
         this.handleTilePacket(mc.theWorld, packet, packet.readCoord());
      }

   }

    /*private void handleContainerPacket(EntityClientPlayerMP thePlayer, PacketCustom packet) {
        //To change body of created methods use File | Settings | File Templates.
    }     */

   private void handleContainerPacket(EntityClientPlayerMP player, PacketCustom packet) {
      if(player.openContainer instanceof Container) {
        // ((Container)player.openContainer).handleOutputPacket(packet);
      }

   }

   private void handleTilePacket(WorldClient world, PacketCustom packet, BlockCoord pos) {
      TileEntity tile = world.getBlockTileEntity(pos.x, pos.y, pos.z);
      if(tile instanceof ICustomPacketTile) {
         ((ICustomPacketTile)tile).handleDescriptionPacket(packet);
      }

   }

   public static void sendGuiChange(int ID, int value) {
      PacketCustom packet = new PacketCustom(channel, 6);
      packet.writeInt(ID);
      packet.writeInt(value);
      packet.sendToServer();
   }

}
