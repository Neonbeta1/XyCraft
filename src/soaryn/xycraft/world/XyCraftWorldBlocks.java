package soaryn.xycraft.world;

import codechicken.lib.config.ConfigTag;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import soaryn.xycraft.core.HiddenID;
import soaryn.xycraft.core.TemplateItems;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.XyCraftRegistry;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.world.block.BlockAluminumTorch;
import soaryn.xycraft.world.block.BlockCorn;
import soaryn.xycraft.world.block.BlockCrystal;
import soaryn.xycraft.world.block.BlockGlassViewer;
import soaryn.xycraft.world.block.BlockHenequen;
import soaryn.xycraft.world.block.BlockOres;
import soaryn.xycraft.world.block.BlockOresMeta;
import soaryn.xycraft.world.block.BlockSulfurTorch;
import soaryn.xycraft.world.block.BlockXyLight;
import soaryn.xycraft.world.block.BlockXyLightInvMeta;
import soaryn.xycraft.world.block.BlockXyLightMeta;
import soaryn.xycraft.world.block.BlockXychoridite;
import soaryn.xycraft.world.block.BlockXychoridite2;
import soaryn.xycraft.world.block.BlockXychoridite2Meta;
import soaryn.xycraft.world.block.BlockXychoriditeDye;
import soaryn.xycraft.world.block.BlockXychoriditeDyeMeta;
import soaryn.xycraft.world.block.BlockXychoriditeMeta;
import soaryn.xycraft.world.block.ItemCrystal;

public class XyCraftWorldBlocks extends TemplateItems {

   public static ConfigTag config;
   public static Block xyOre;
   public static Block xyAesthetic;
   public static Block crystal;
   public static Block sulfurTorch;
   @HiddenID
   public static Block corn;
   @HiddenID
   public static Block henequen;
   public static Block aluminumTorch;
   public static Block xychoridite2;
   public static Block structureBlock;
   public static Block xyLight;
   public static Block xyLightInv;
   public static Block glassViewer;


   public void register(XyCraftRegistry registry) {
      config = XyConfig.config.getTag("world.blocks").useBraces().setSortMode(1).setBaseID(220);
      config.getTag("worldGen").useBraces().setNewLine(true).setSortMode(1);
      xyOre = registry.registerBlock(BlockOres.class, BlockOresMeta.class, config, "worldGen.xyOre", "XyOre", new Object[]{Integer.valueOf(0)});
      crystal = registry.registerBlock(BlockCrystal.class, ItemCrystal.class, config, "worldGen.quartzCrystal", "Quartz Crystal", new Object[0]);
      config = XyConfig.config.getTag("world.blocks").useBraces().setSortMode(1).setBaseID(1375);
      config.getTag("aesthetic").useBraces().setNewLine(true).setSortMode(1);
      xyAesthetic = registry.registerBlock(BlockXychoridite.class, BlockXychoriditeMeta.class, config, "aesthetic.xychoriditeBlocks", "Xychoridite Blocks", new Object[]{Integer.valueOf(48)});
      sulfurTorch = registry.registerBlock(BlockSulfurTorch.class, config, "aesthetic.sulfurTorch", "Sulfur Torch", new Object[]{Integer.valueOf(6)});
      corn = registry.registerBlock(BlockCorn.class, config, "aesthetic.cornStalk", "Corn Stalk", new Object[]{Integer.valueOf(128)});
      henequen = registry.registerBlock(BlockHenequen.class, config, "aesthetic.henequenPlant", "Henequen Plant", new Object[]{Integer.valueOf(144)});
      aluminumTorch = registry.registerBlock(BlockAluminumTorch.class, config, "aesthetic.aluminumTorch", "Aluminum Torch", new Object[]{Integer.valueOf(7)});
      config = XyConfig.config.getTag("world.blocks").useBraces().setSortMode(1).setBaseID(1381);
      config.getTag("aesthetic").useBraces().setNewLine(true).setSortMode(1);
      xychoridite2 = registry.registerBlock(BlockXychoridite2.class, BlockXychoridite2Meta.class, config, "aesthetic.xychoridite2Blocks", "Xychoridite2 Blocks", new Object[]{Integer.valueOf(34)});
      structureBlock = registry.registerBlock(BlockXychoriditeDye.class, BlockXychoriditeDyeMeta.class, config, "aesthetic.structureBlock", "Xychoridite Structure", new Object[]{Integer.valueOf(32)});
      xyLight = registry.registerBlock(BlockXyLight.class, BlockXyLightMeta.class, config, "aesthetic.xyLight", "Xychorium Light", new Object[]{Integer.valueOf(38), Boolean.valueOf(false)});
      xyLightInv = registry.registerBlock(BlockXyLight.class, BlockXyLightInvMeta.class, config, "aesthetic.xyLightInv", "Inverted Xychorium Light", new Object[]{Integer.valueOf(38), Boolean.valueOf(true)});
      if(XyConfig.config.getTag("machines").getId("blocks.glassViewer", 0) > 0) {
         glassViewer = registry.registerBlock(BlockGlassViewer.class, XyConfig.config.getTag("machines"), "blocks.glassViewer", "Glass Viewer", new Object[]{Integer.valueOf(51)});
      } else {
         glassViewer = registry.registerBlock(BlockGlassViewer.class, config, "aesthetic.glassViewer", "Glass Viewer", new Object[]{Integer.valueOf(51)});
      }

   }

   public void init() {
      XyCraft.tabBlocks.setImageItem(xyAesthetic.blockID, 0);

      int i;
      for(i = 0; i < 5; ++i) {
         OreDictionary.registerOre("oreXychorium", new ItemStack(xyOre, 1, i));
      }

      OreDictionary.registerOre("oreNaturalAluminum", new ItemStack(xyOre, 1, 5));
      OreDictionary.registerOre("crystalQuartz", crystal);
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 0), "Blue Xychorium Ore");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 1), "Green Xychorium Ore");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 2), "Red Xychorium Ore");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 3), "Dark Xychorium Ore");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 4), "Light Xychorium Ore");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 5), "Aluminum Ore");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 6), "Blue Xychorium Block");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 7), "Green Xychorium Block");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 8), "Red Xychorium Block");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 9), "Dark Xychorium Block");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 10), "Light Xychorium Block");
      LanguageRegistry.addName(new ItemStack(xyOre, 0, 11), "Aluminum Block");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 0), "Blue Xychoridite Brick");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 1), "Green Xychoridite Brick");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 2), "Red Xychoridite Brick");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 3), "Dark Xychoridite Brick");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 4), "Light Xychoridite Brick");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 5), "Blue Xychoridite Plate");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 6), "Green Xychoridite Plate");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 7), "Red Xychoridite Plate");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 8), "Dark Xychoridite Plate");
      LanguageRegistry.addName(new ItemStack(xyAesthetic, 0, 9), "Light Xychoridite Plate");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 0), "Blue Xychoridite Platform");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 1), "Green Xychoridite Platform");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 2), "Red Xychoridite Platform");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 3), "Dark Xychoridite Platform");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 4), "Light Xychoridite Platform");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 5), "Blue Xychoridite Shield");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 6), "Green Xychoridite Shield");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 7), "Red Xychoridite Shield");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 8), "Dark Xychoridite Shield");
      LanguageRegistry.addName(new ItemStack(xychoridite2, 0, 9), "Light Xychoridite Shield");

      for(i = 0; i < 16; ++i) {
         LanguageRegistry.addName(new ItemStack(structureBlock, 0, i), XyReferences.mcColorNames[i] + " Structure");
         LanguageRegistry.addName(new ItemStack(xyLight, 1, i), XyReferences.mcColorNames[i] + " Xychoridite Light");
         LanguageRegistry.addName(new ItemStack(xyLightInv, 1, i), "Inverted " + XyReferences.mcColorNames[i] + " Xychoridite Light");
      }

   }
}
