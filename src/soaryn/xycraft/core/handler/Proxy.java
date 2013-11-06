package soaryn.xycraft.core.handler;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.common.event.FMLStateEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import soaryn.xycraft.core.TemplateMod;
import soaryn.xycraft.core.handler.XycraftSPH;

public class Proxy implements IGuiHandler {

   public EntityPlayer getPlayer() {
      return null;
   }

   public World getWorld() {
      return null;
   }

   public Tessellator getTessellator() {
      return null;
   }

   public void registerRenderInfo(TemplateMod expansion, FMLStateEvent state) {}

   public void registerTileEntities(TemplateMod expansion, FMLStateEvent state) {
      expansion.registerTileEntities(state);
   }

   public int addArmor(String armorString) {
      return -1;
   }

   public void registerPacketHandler() {
      PacketCustom.assignHandler(XycraftSPH.channel, new XycraftSPH());
   }

   public double getBlockReachDistance(EntityPlayer player) {
      return ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance();
   }

   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }
}
