package soaryn.xycraft.world;

import codechicken.lib.config.ConfigTag;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.oredict.OreDictionary;
import soaryn.xycraft.core.HiddenID;
import soaryn.xycraft.core.TemplateItems;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.XyCraftRegistry;
import soaryn.xycraft.world.item.ItemHenequen;
import soaryn.xycraft.world.item.ItemKernel;
import soaryn.xycraft.world.item.ItemLiquid;
import soaryn.xycraft.world.item.ItemLiquidGenericContainer;
import soaryn.xycraft.world.item.ItemSulfurGoo;
import soaryn.xycraft.world.item.ItemWorld;
import soaryn.xycraft.world.item.ItemXyFood;

public class XyCraftWorldItems extends TemplateItems {

   public static ConfigTag config;
   public static Item blueXychorium;
   public static Item greenXychorium;
   public static Item redXychorium;
   public static Item darkXychorium;
   public static Item lightXychorium;
   public static Item blueXychoridite;
   public static Item greenXychoridite;
   public static Item redXychoridite;
   public static Item darkXychoridite;
   public static Item lightXychoridite;
   public static Item aluminum;
   public static Item corn;
   public static Item kernel;
   public static Item henequenSeeds;
   public static Item henequenLeaf;
   public static Item twine;
   public static Item sulfurGoo;
   public static Item sulfur;
   public static Item popcorn;
   public static Item cornCob;
   @HiddenID
   public static Item aluminumFoil;
   public static ItemLiquid liquids;


   public void register(XyCraftRegistry registry) {
      config = XyConfig.config.getTag("world.items").useBraces().setSortMode(1).setBaseID(24000);
      blueXychorium = registry.registerItem(ItemWorld.class, config, "blueXychorium", "Blue Xychorium", new Object[]{Integer.valueOf(0), Integer.valueOf(0)});
      greenXychorium = registry.registerItem(ItemWorld.class, config, "greenXychorium", "Green Xychorium", new Object[]{Integer.valueOf(1), Integer.valueOf(0)});
      redXychorium = registry.registerItem(ItemWorld.class, config, "redXychorium", "Red Xychorium", new Object[]{Integer.valueOf(2), Integer.valueOf(0)});
      darkXychorium = registry.registerItem(ItemWorld.class, config, "darkXychorium", "Dark Xychorium", new Object[]{Integer.valueOf(3), Integer.valueOf(0)});
      lightXychorium = registry.registerItem(ItemWorld.class, config, "lightXychorium", "Light Xychorium", new Object[]{Integer.valueOf(4), Integer.valueOf(0)});
      blueXychoridite = registry.registerItem(ItemWorld.class, config, "blueXychoridite", "Blue Xychoridite", new Object[]{Integer.valueOf(0), Integer.valueOf(1)});
      greenXychoridite = registry.registerItem(ItemWorld.class, config, "greenXychoridite", "Green Xychoridite", new Object[]{Integer.valueOf(1), Integer.valueOf(1)});
      redXychoridite = registry.registerItem(ItemWorld.class, config, "redXychoridite", "Red Xychoridite", new Object[]{Integer.valueOf(2), Integer.valueOf(1)});
      darkXychoridite = registry.registerItem(ItemWorld.class, config, "darkXychoridite", "Dark Xychoridite", new Object[]{Integer.valueOf(3), Integer.valueOf(1)});
      lightXychoridite = registry.registerItem(ItemWorld.class, config, "lightXychoridite", "Light Xychoridite", new Object[]{Integer.valueOf(4), Integer.valueOf(1)});
      aluminum = registry.registerItem(ItemWorld.class, config, "aluminum", "Aluminum", new Object[]{Integer.valueOf(7), Integer.valueOf(0)});
      sulfur = registry.registerItem(ItemWorld.class, config, "sulfur", "Sulfur", new Object[]{Integer.valueOf(8), Integer.valueOf(0)});
      sulfurGoo = registry.registerItem(ItemSulfurGoo.class, config, "sulfurGoo", "Sulfur Goo", new Object[]{Integer.valueOf(8), Integer.valueOf(1)});
      corn = ((ItemXyFood)registry.registerItem(ItemXyFood.class, config, "corn", "Corn", new Object[]{Integer.valueOf(3), Float.valueOf(0.4F)}));
      kernel = registry.registerItem(ItemKernel.class, config, "kernel", "Corn Kernel", new Object[0]);
      henequenSeeds = registry.registerItem(ItemHenequen.class, config, "henequenSeeds", "Henequen Seeds", new Object[0]);
      henequenLeaf = registry.registerItem(ItemWorld.class, config, "henequenLeaf", "Henequen Leaf", new Object[]{Integer.valueOf(1), Integer.valueOf(6)});
      twine = registry.registerItem(ItemWorld.class, config, "twine", "Twine", new Object[]{Integer.valueOf(2), Integer.valueOf(6)});
      popcorn = ((ItemXyFood)registry.registerItem(ItemXyFood.class, config, "popcorn", "Popcorn", new Object[]{Integer.valueOf(3), Float.valueOf(0.6F), Boolean.valueOf(false), new PotionEffect[]{new PotionEffect(Potion.moveSpeed.id, 200, 0)}}));
      cornCob = ((ItemXyFood)registry.registerItem(ItemXyFood.class, config, "cornCob", "Cob O\'Corn", new Object[]{Integer.valueOf(5), Float.valueOf(0.7F), Boolean.valueOf(true), new PotionEffect[0]}));
      aluminumFoil = registry.registerItem(ItemLiquidGenericContainer.class, config, "aluminumFoil", "Aluminum Foil", new Object[0]);
      config = XyConfig.config.getTag("world.items.liquid").useBraces().setSortMode(1).setNewLine(true).setBaseID(24200);
      liquids = (ItemLiquid)registry.registerItem(ItemLiquid.class, config, "xyliquid", "Liquids", new Object[0]);
   }

   public void init() {
      XyCraft.tabItems.setImageItem(blueXychorium.itemID, 0);
      config = XyConfig.config.getTag("world.items").useBraces().setSortMode(1);
      OreDictionary.registerOre("xychoriumBlue", blueXychorium);
      OreDictionary.registerOre("xychoriumGems", blueXychorium);
      OreDictionary.registerOre("xychoriumGreen", greenXychorium);
      OreDictionary.registerOre("xychoriumGems", greenXychorium);
      OreDictionary.registerOre("xychoriumRed", redXychorium);
      OreDictionary.registerOre("xychoriumGems", redXychorium);
      OreDictionary.registerOre("xychoriumDark", darkXychorium);
      OreDictionary.registerOre("xychoriumGems", darkXychorium);
      OreDictionary.registerOre("xychoriumLight", lightXychorium);
      OreDictionary.registerOre("xychoriumGems", lightXychorium);
      OreDictionary.registerOre("xychoriditeBlue", blueXychoridite);
      OreDictionary.registerOre("xychoriditeMetal", blueXychoridite);
      OreDictionary.registerOre("xychoriditeGreen", greenXychoridite);
      OreDictionary.registerOre("xychoriditeMetal", greenXychoridite);
      OreDictionary.registerOre("xychoriditeRed", redXychoridite);
      OreDictionary.registerOre("xychoriditeMetal", redXychoridite);
      OreDictionary.registerOre("xychoriditeDark", darkXychoridite);
      OreDictionary.registerOre("xychoriditeMetal", darkXychoridite);
      OreDictionary.registerOre("xychoriditeLight", lightXychoridite);
      OreDictionary.registerOre("xychoriditeMetal", lightXychoridite);
      OreDictionary.registerOre("naturalAluminum", aluminum);
      OreDictionary.registerOre("dustSulfur", sulfur);
      OreDictionary.registerOre("dyeRed", sulfurGoo);
      liquids.registerLiquid();
   }
}
