package soaryn.xycraft.core;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLStateEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import soaryn.xycraft.core.TemplateItems;
import soaryn.xycraft.core.XyCraft;

public abstract class TemplateMod {

   public TemplateItems blocks;
   public TemplateItems items;


   @PreInit
   public abstract void preInit(FMLPreInitializationEvent var1);

   @Init
   public abstract void init(FMLInitializationEvent var1);

   @PostInit
   public abstract void postInit(FMLPostInitializationEvent var1);

   public abstract void registerTileEntities(FMLStateEvent var1);

   @SideOnly(Side.CLIENT)
   public abstract void registerTileEntitiesClient(FMLStateEvent var1);

   @SideOnly(Side.CLIENT)
   public abstract void registerRenderInfo(FMLStateEvent var1);

   public void initClient(FMLStateEvent state) {
      XyCraft var10000 = XyCraft.instance;
      XyCraft.proxy.registerRenderInfo(this, state);
      var10000 = XyCraft.instance;
      XyCraft.proxy.registerTileEntities(this, state);
   }

   public void registerBlocks(TemplateItems list) {
      this.blocks = list;
      this.blocks.register(XyCraft.registry);
   }

   public void registerItems(TemplateItems list) {
      this.items = list;
      list.register(XyCraft.registry);
   }
}
