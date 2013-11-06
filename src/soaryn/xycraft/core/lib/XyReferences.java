package soaryn.xycraft.core.lib;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;
import soaryn.xycraft.core.XyConfig;

public class XyReferences {

   public static final String VERSION = "@VERSION@";
   public static final String MODID = "XyCraft";
   public static final String FORGE_VERSION = "6.6.2.534";
   public static final String dir = "/soaryn/xycraft/";
   public static final String sounds = "/soaryn/xycraft/sounds/";
   public static final String res = XyConfig.HDTextures?"high":"low";
   public static final Colour[] mcColors = new Colour[]{new ColourRGBA(-1), new ColourRGBA(255, 130, 0, 255), new ColourRGBA(-16711681), new ColourRGBA(105, 195, 255, 255), new ColourRGBA(-65281), new ColourRGBA(16711935), new ColourRGBA(255, 135, 255, 255), new ColourRGBA(-2139062017), new ColourRGBA(-1061109505), new ColourRGBA(16777215), new ColourRGBA(150, 0, 250, 255), new ColourRGBA('\uffff'), new ColourRGBA(110, 65, 0, 255), new ColourRGBA(8388863), new ColourRGBA(-16776961), new ColourRGBA(741092607)};
   public static final String[] mcColorNames = new String[]{"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime Green", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};
   public static final Colour[] xyColors = new Colour[]{new ColourRGBA(0, 100, 255, 255), new ColourRGBA(16711935), new ColourRGBA(-16776961), new ColourRGBA(30, 30, 30, 255), new ColourRGBA(-1)};
   public static final Colour[] controlColor = new Colour[]{new ColourRGBA(16711935), new ColourRGBA(100, 130, 255, 255), new ColourRGBA(255, 130, 0, 255)};


   public static final boolean isDebugging() {
      return "@DEBUG@".replace("@", "").equals("DEBUG");
   }


   public static class FormatText {

      private static final String formatPrefix = "§";


      public static String formatS(String text, XyReferences.FormatText.EnumFormatting ... formats) {
         String result = "";
         XyReferences.FormatText.EnumFormatting[] arr$ = formats;
         int len$ = formats.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            XyReferences.FormatText.EnumFormatting f = arr$[i$];
            result = result.concat("§" + f.c);
         }

         return result + text + "§" + XyReferences.FormatText.EnumFormatting.RESET.c + "§" + XyReferences.FormatText.EnumFormatting.GREY.c;
      }

      public static String getPrefix(XyReferences.FormatText.EnumFormatting ... formats) {
         String result = "";
         XyReferences.FormatText.EnumFormatting[] arr$ = formats;
         int len$ = formats.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            XyReferences.FormatText.EnumFormatting f = arr$[i$];
            result = result.concat("§" + f.c);
         }

         return result;
      }

      public static enum EnumFormatting {

         BLACK("BLACK", 0, '0'),
         DARK_BLUE("DARK_BLUE", 1, '1'),
         DARK_GREEN("DARK_GREEN", 2, '2'),
         DARK_CYAN("DARK_CYAN", 3, '3'),
         DARK_RED("DARK_RED", 4, '4'),
         PUPRLE("PUPRLE", 5, '5'),
         ORANGE("ORANGE", 6, '6'),
         GREY("GREY", 7, '7'),
         DARK_GREY("DARK_GREY", 8, '8'),
         BLUE("BLUE", 9, '9'),
         LIME_GREEN("LIME_GREEN", 10, 'a'),
         CYAN("CYAN", 11, 'b'),
         RED("RED", 12, 'c'),
         PINK("PINK", 13, 'd'),
         YELLOW("YELLOW", 14, 'e'),
         WHITE("WHITE", 15, 'f'),
         SCRAMBLED("SCRAMBLED", 16, 'k'),
         BOLD("BOLD", 17, 'l'),
         STRIKE("STRIKE", 18, 'm'),
         UNDERLINED("UNDERLINED", 19, 'n'),
         ITALIC("ITALIC", 20, 'o'),
         RESET("RESET", 21, 'r');
         public char c;
         // $FF: synthetic field
         private static final XyReferences.FormatText.EnumFormatting[] $VALUES = new XyReferences.FormatText.EnumFormatting[]{BLACK, DARK_BLUE, DARK_GREEN, DARK_CYAN, DARK_RED, PUPRLE, ORANGE, GREY, DARK_GREY, BLUE, LIME_GREEN, CYAN, RED, PINK, YELLOW, WHITE, SCRAMBLED, BOLD, STRIKE, UNDERLINED, ITALIC, RESET};


         private EnumFormatting(String var1, int var2, char c) {
            this.c = c;
         }

      }
   }

   public static class Textures {

      public static final int TRANSPARENT_TILE = 239;
      public static final String worldItemSprites = "/soaryn/xycraft/world/sprites/sprite_items.png";
      public static final String worldBlockSprites = "/soaryn/xycraft/world/sprites/sprite_blocks.png";
      public static final String worldModelDir = "/soaryn/xycraft/world/models/";
      public static final String animationSheet = "/soaryn/xycraft/world/sprites/animation.png";
      public static final String machinesItemSprites = "/soaryn/xycraft/machines/sprites/sprite_items_" + XyReferences.res + ".png";
      public static final String machinesItemUpgradeSprites = "/soaryn/xycraft/machines/sprites/sprite_upgrades.png";
      public static final String machinesItemMaterialSprites = "/soaryn/xycraft/machines/sprites/sprite_materials.png";
      public static final String machinesBlockSprites = "/soaryn/xycraft/machines/sprites/sprite_blocks.png";
      public static final String machinesMultiBlockSprites = "/soaryn/xycraft/machines/sprites/sprite_multiblocks.png";
      public static final String machinesModelDir = "/soaryn/xycraft/machines/models/";
      public static final String machinesHedron = "/soaryn/xycraft/machines/models/t_icosa_grey.png";
      public static final String machinesHedronSpace = "/soaryn/xycraft/machines/models/t_icosa_grey_space.png";
      public static final int xychoriumBlue = 0;
      public static final int xychoriumGreen = 1;
      public static final int xychoriumRed = 2;
      public static final int xychoriumDark = 3;
      public static final int xychoriumLight = 4;
      public static final int xychoriditeBlue = 16;
      public static final int xychoriditeGreen = 17;
      public static final int xychoriditeRed = 18;
      public static final int xychoriditeDark = 19;
      public static final int xychoriditeLight = 20;


   }

   public static class OreDictionaryNames {

      public static final String[] dyeNames = new String[]{"dyeWhite", "dyeOrange", "dyeMagenta", "dyeLightBlue", "dyeYellow", "dyeLime", "dyePink", "dyeGray", "dyeLightGray", "dyeCyan", "dyePurple", "dyeBlue", "dyeBrown", "dyeGreen", "dyeRed", "dyeBlack"};
      public static final String xychorium = "xychoriumGems";
      public static final String xychoriumBlue = "xychoriumBlue";
      public static final String xychoriumGreen = "xychoriumGreen";
      public static final String xychoriumRed = "xychoriumRed";
      public static final String xychoriumDark = "xychoriumDark";
      public static final String xychoriumLight = "xychoriumLight";
      public static final String xychoridite = "xychoriditeMetal";
      public static final String xychoriditeBlue = "xychoriditeBlue";
      public static final String xychoriditeGreen = "xychoriditeGreen";
      public static final String xychoriditeRed = "xychoriditeRed";
      public static final String xychoriditeDark = "xychoriditeDark";
      public static final String xychoriditeLight = "xychoriditeLight";
      public static final String oreXy = "oreXychorium";
      public static final String aluminumOre = "oreNaturalAluminum";
      public static final String aluminum = "naturalAluminum";
      public static final String dustSulfur = "dustSulfur";
      public static final String dyeRed = "dyeRed";
      public static final String crystalQuartz = "crystalQuartz";
      public static final String mulch = "woodMulch";
      public static final String sheetMetal = "sheetMetal";
      public static final String dustSilicate = "dustSilicate";
      public static final String shardSilicon = "shardSilicon";
      public static final String engineeringBrick = "brickXyEngineering";


   }

   public static class EquipmentReferences {

      public static final String GAUNTLET = "gauntlet";


   }

   public static class UpgradeReferences {

      public static final String ENERGY_USE = "energyUse";
      public static final String ENERGY_CURRENT = "energyCurrent";
      public static final String ENERGY_CAPACITY = "energyCapacity";
      public static final String ENERGY_OPTIMIZATION = "energyOptimize";
      public static final String SPEED = "speed";
      public static final String RED_COMPONENT = "redColor";
      public static final String GREEN_COMPONENT = "greenColor";
      public static final String BLUE_COMPONENT = "blueColor";


   }

   public static enum EnumXyGuiID {

      VALVE("VALVE", 0),
      GENERATOR("GENERATOR", 1),
      CUTTER("CUTTER", 2),
      CRUSHER("CRUSHER", 3),
      INFERNOCHAMBER("INFERNOCHAMBER", 4),
      COOLINGCHAMBER("COOLINGCHAMBER", 5),
      ENGINEERING_TABLE("ENGINEERING_TABLE", 6),
      FABRICATOR("FABRICATOR", 7);
      // $FF: synthetic field
      private static final XyReferences.EnumXyGuiID[] $VALUES = new XyReferences.EnumXyGuiID[]{VALVE, GENERATOR, CUTTER, CRUSHER, INFERNOCHAMBER, COOLINGCHAMBER, ENGINEERING_TABLE, FABRICATOR};


      private EnumXyGuiID(String var1, int var2) {}

   }
}
