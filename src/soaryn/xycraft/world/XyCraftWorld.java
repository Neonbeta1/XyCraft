package soaryn.xycraft.world;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.client.TextureFXManager;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import soaryn.util.UtilRender;
import soaryn.xycraft.core.DamageSourceCustom;
import soaryn.xycraft.core.TemplateMod;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.handler.CoreEventHandler;
import soaryn.xycraft.core.render.GlassConnectedTexture;
import soaryn.xycraft.world.block.TileCrystal;
import soaryn.xycraft.world.block.TileCrystalOld;
import soaryn.xycraft.world.gen.WorldPopulator;
import soaryn.xycraft.world.handler.ProxyWorld;
import soaryn.xycraft.world.recipe.XyWorldRecipe;
import soaryn.xycraft.world.render.RenderCrystal;
import soaryn.xycraft.world.render.RenderItemLiquid;

@Mod(
   modid = "XyCraftWorld",
   name = "XyCraft World",
   version = "@VERSION@",
   dependencies = "required-after:XyCraft",
   acceptedMinecraftVersions = "[1.4.7,)"
)
@NetworkMod(
   clientSideRequired = true,
   serverSideRequired = false,
   tinyPacketHandler = PacketCustom.CustomTinyPacketHandler.class
)
public class XyCraftWorld extends TemplateMod {

   @SidedProxy(
      clientSide = "soaryn.xycraft.world.handler.ProxyClientWorld",
      serverSide = "soaryn.xycraft.world.handler.ProxyWorld"
   )
   public static ProxyWorld proxy;
   @Instance("XyCraftWorld")
   public static XyCraftWorld instance;
   public static DamageSource crystal = new DamageSourceCustom("crystal");


   @PreInit
   public void preInit(FMLPreInitializationEvent event) {
      proxy.registerPacketHandler();
      XyConfig.config.getTag("world").useBraces();
      this.registerBlocks(new XyCraftWorldBlocks());
      this.registerItems(new XyCraftWorldItems());
   }

   @Init
   public void init(FMLInitializationEvent event) {
      this.blocks.init();
      this.items.init();
      MinecraftForge.addGrassSeed(new ItemStack(XyCraftWorldItems.kernel), 8);
      MinecraftForge.addGrassSeed(new ItemStack(XyCraftWorldItems.henequenSeeds), 9);
      GameRegistry.registerWorldGenerator(new WorldPopulator());
   }

   @PostInit
   public void postInit(FMLPostInitializationEvent event) {
      XyWorldRecipe.init();
      this.setBlockHarvestLevels();
      this.initClient(event);
      LanguageRegistry.instance().addStringLocalization("death.crystal", "%1$s was slowly poked by a Crystal.");
      CoreEventHandler.achievAquireIds.put(Integer.valueOf(XyCraftWorldItems.blueXychorium.itemID), XyWorldAchievements.xychorium);
      CoreEventHandler.achievAquireIds.put(Integer.valueOf(XyCraftWorldItems.greenXychorium.itemID), XyWorldAchievements.xychorium);
      CoreEventHandler.achievAquireIds.put(Integer.valueOf(XyCraftWorldItems.redXychorium.itemID), XyWorldAchievements.xychorium);
      CoreEventHandler.achievAquireIds.put(Integer.valueOf(XyCraftWorldItems.darkXychorium.itemID), XyWorldAchievements.xychorium);
      CoreEventHandler.achievAquireIds.put(Integer.valueOf(XyCraftWorldItems.lightXychorium.itemID), XyWorldAchievements.xychorium);
      CoreEventHandler.achievAquireIds.put(Integer.valueOf(XyCraftWorldBlocks.crystal.blockID), XyWorldAchievements.crystal);
   }

   @SideOnly(Side.CLIENT)
   public void registerRenderInfo(FMLStateEvent state) {
     /* MinecraftForgeClient.preloadTexture("/soaryn/xycraft/particles.png");
      MinecraftForgeClient.preloadTexture("/soaryn/xycraft/world/sprites/sprite_blocks.png");
      MinecraftForgeClient.preloadTexture("/soaryn/xycraft/world/sprites/sprite_items.png");
      MinecraftForgeClient.preloadTexture("/soaryn/xycraft/world/sprites/animation.png");
      MinecraftForgeClient.preloadTexture("/soaryn/xycraft/world/sprites/connectedtextures_glass_viewer.png");  */
      UtilRender.RenderIds.renderCrystal = RenderingRegistry.getNextAvailableRenderId();
      RenderingRegistry.registerBlockHandler(new RenderCrystal());
      //TextureFXManager.instance().addAnimation(new FXAnimatedCloud(255, "/soaryn/xycraft/world/sprites/sprite_blocks.png"));
      //TextureFXManager.instance().addAnimation(new TextureLiquidsFX(new Color(255, 255, 255), 0, "/soaryn/xycraft/world/sprites/animation.png"));
      //TextureFXManager.instance().addAnimation(new FXAnimatedCloud(1, "/soaryn/xycraft/world/sprites/animation.png"));
      new GlassConnectedTexture(XyCraftWorldBlocks.glassViewer.blockID, "/soaryn/xycraft/world/sprites/connectedtextures_glass_viewer.png");
      MinecraftForgeClient.registerItemRenderer(XyCraftWorldItems.liquids.itemID, new RenderItemLiquid());
   }

   public void registerTileEntities(FMLStateEvent state) {
      GameRegistry.registerTileEntity(TileCrystalOld.class, "tileCrystal");
      GameRegistry.registerTileEntity(TileCrystal.class, "tileCrystalNew");
   }

   @SideOnly(Side.CLIENT)
   public void registerTileEntitiesClient(FMLStateEvent state) {}

   private void setBlockHarvestLevels() {
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyOre, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 0, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 1, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 2, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 3, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 4, "pickaxe", 1);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 5, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 6, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 7, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 8, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xyAesthetic, 9, "pickaxe", 2);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 0, "pickaxe", 0);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 1, "pickaxe", 0);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 2, "pickaxe", 0);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 3, "pickaxe", 0);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 4, "pickaxe", 0);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 5, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 6, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 7, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 8, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.xychoridite2, 9, "pickaxe", 3);
      MinecraftForge.setBlockHarvestLevel(XyCraftWorldBlocks.structureBlock, "pickaxe", 0);
   }

}
