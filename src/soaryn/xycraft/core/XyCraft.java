package soaryn.xycraft.core;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLStateEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraftforge.common.MinecraftForge;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.XyCraftRegistry;
import soaryn.xycraft.core.handler.CoreEventHandler;
import soaryn.xycraft.core.handler.Proxy;
import soaryn.xycraft.core.handler.RenderTickHandler;
import soaryn.xycraft.core.plugin.PluginHandler;
import soaryn.xycraft.core.render.RenderXy;

@Mod(
   modid = "XyCraft",
   name = "XyCraft",
   version = "@VERSION@",
   dependencies = "required-after:Forge@[6.6.2.534,)",
   acceptedMinecraftVersions = "[1.4.7,)"
)
@NetworkMod(
   clientSideRequired = true,
   serverSideRequired = false,
   tinyPacketHandler = PacketCustom.CustomTinyPacketHandler.class
)
public class XyCraft extends TemplateMod {

   @SidedProxy(
      clientSide = "soaryn.xycraft.core.handler.ProxyClient",
      serverSide = "soaryn.xycraft.core.handler.Proxy"
   )
   public static Proxy proxy;
   @Instance("XyCraft")
   public static XyCraft instance;
   public static Logger logger = Logger.getLogger("XyCraft");
   public static CoreEventHandler eventHandler = new CoreEventHandler();
   public static XyCraftRegistry registry = new XyCraftRegistry();
   //public static PluginHandler thermalExpansion = new PluginHandlerThermalExpansion();
   //public static PluginHandler forestry = new PluginHandlerForestry();
   //public static PluginHandler ic2 = new PluginHandlerIC2();
   //public static PluginHandler railcraft = new PluginHandlerRailcraft();
   public static final CreativeTabsCustom tabBlocks = new CreativeTabsCustom("XyCraft Blocks");
   public static final CreativeTabsCustom tabItems = new CreativeTabsCustom("XyCraft Items");


   @PreInit
   public void preInit(FMLPreInitializationEvent event) {
      logger.setParent(FMLLog.getLogger());
      NetworkRegistry.instance().registerGuiHandler(this, proxy);
      XyConfig.init(event.getSuggestedConfigurationFile());
      if(XyConfig.autoAssign) {
         logger.log(Level.INFO, "Auto Assign is turned on");
      }

      proxy.registerPacketHandler();
      MinecraftForge.EVENT_BUS.register(eventHandler);
      this.initClient(event);
   }

   @Init
   public void init(FMLInitializationEvent event) {
      registry.throwConflicts();
   }

   @PostInit
   public void postInit(FMLPostInitializationEvent event) {
      if(XyConfig.autoAssign) {
         XyConfig.generalConfig.getTag("AutoAssign").setBooleanValue(false);
         logger.log(Level.INFO, "Auto Assign Completed");
      }

      //thermalExpansion.init();
      //forestry.init();
      //ic2.init();
      //railcraft.init();
   }

   @SideOnly(Side.CLIENT)
   public void registerRenderInfo(FMLStateEvent state) {
      TickRegistry.registerTickHandler(new RenderTickHandler(), Side.CLIENT);
      UtilRender.RenderIds.renderXy = RenderingRegistry.getNextAvailableRenderId();
      RenderingRegistry.registerBlockHandler(new RenderXy());
   }

   public void registerTileEntities(FMLStateEvent state) {}

   @SideOnly(Side.CLIENT)
   public void registerTileEntitiesClient(FMLStateEvent state) {}

}
