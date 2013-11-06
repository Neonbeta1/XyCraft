package soaryn.xycraft.core.handler;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLStateEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import soaryn.xycraft.core.TemplateMod;
import soaryn.xycraft.core.handler.Proxy;
import soaryn.xycraft.core.handler.XycraftCPH;

public class ProxyClient extends Proxy {

   public EntityPlayer getPlayer() {
      return Minecraft.getMinecraft().thePlayer;
   }

   public World getWorld() {
      return Minecraft.getMinecraft().theWorld;
   }

   public Tessellator getTessellator() {
      return Tessellator.instance;
   }

   public void registerRenderInfo(TemplateMod expansion, FMLStateEvent state) {
      expansion.registerRenderInfo(state);
   }

   public void registerTileEntities(TemplateMod expansion, FMLStateEvent state) {
      super.registerTileEntities(expansion, state);
      expansion.registerTileEntitiesClient(state);
   }

   public int addArmor(String armorString) {
      return RenderingRegistry.addNewArmourRendererPrefix(armorString);
   }

   public void registerPacketHandler() {
      super.registerPacketHandler();
      PacketCustom.assignHandler(XycraftCPH.channel, new XycraftCPH());
   }

   public double getBlockReachDistance(EntityPlayer player) {
      return (double)Minecraft.getMinecraft().playerController.getBlockReachDistance();
   }
}
