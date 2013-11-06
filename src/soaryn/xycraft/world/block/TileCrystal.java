package soaryn.xycraft.world.block;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import soaryn.xycraft.core.handler.ICustomPacketTile;
import soaryn.xycraft.core.handler.XycraftSPH;

public class TileCrystal extends TileEntity implements ICustomPacketTile {

   public int quantity = 1;


   public boolean canUpdate() {
      return false;
   }

   public void readFromNBT(NBTTagCompound tag) {
      super.readFromNBT(tag);
      this.quantity = tag.getInteger("quantity");
   }

   public void writeToNBT(NBTTagCompound tag) {
      super.writeToNBT(tag);
      tag.setInteger("quantity", this.quantity);
   }

   public Packet getDescriptionPacket() {
      PacketCustom packet = new PacketCustom(XycraftSPH.channel, 5);
      packet.setChunkDataPacket();
      packet.writeCoord(super.xCoord, super.yCoord, super.zCoord);
      packet.writeByte(this.quantity);
      return packet.toPacket();
   }

   public void handleDescriptionPacket(PacketCustom packet) {
      //this.quantity = packet.readUnsignedByte();
   }
}
