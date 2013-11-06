package soaryn.xycraft.core;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.config.ConfigFile;
import codechicken.lib.config.ConfigTag;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class XyConfig {

   public static ConfigFile config;
   public static ConfigTag generalConfig;
   public static boolean autoAssign;
   public static boolean enableMachines;
   public static boolean HDTextures;
   public static boolean particles;
   public static boolean lairaBricks;
   public static Colour machineColor;
   public static int highlightId;


   public static void init(File configFile) {
      deleteOldConfig(configFile);
      config = (new ConfigFile(configFile)).setComment("XyCraft Configuration File");
      generalConfig = config.getTag("general").useBraces();
      autoAssign = generalConfig.getTag("AutoAssign").setComment("Resolves Id conflicts with any mod loaded before it. **WARNING: MAY CAUSE ERRORS IN OLD WORLDS**").getBooleanValue(false);
      enableMachines = generalConfig.getTag("EnableMachine").setComment("Set to false to disable the Machines Expansion. **WARNING: MAY CAUSE ERRORS IN OLD WORLDS**").getBooleanValue(true);
      HDTextures = generalConfig.getTag("HDTextures").setComment("Set to false if 16x textures are desired:: Doesn\'t make a difference currently").getBooleanValue(true);
      particles = generalConfig.getTag("Particles").setComment("Set to true if you would like to see Particle FX").getBooleanValue(true);
      generalConfig.getTag("colors").useBraces().setNewLine(true).setSortMode(1);
      lairaBricks = generalConfig.getTag("colors.LairaBricks").setComment("Set to true to see Laira\'s favorite Xychorium Bricks Texture").getBooleanValue(false);
      machineColor = generalConfig.getTag("colors.GenericMachineColour").setComment("The colour of the machine animation. Format 0xRRGGBB or R,G,B (0-255)").getColourRGB(new ColourRGBA(0, 100, 255, 0));
      highlightId = Math.abs(generalConfig.getTag("highlightId").setComment("0 = Circuit || 1 = Filled || 2 = Box").getIntValue(0)) % 3;
   }

   private static void deleteOldConfig(File configFile) {
      try {
         if(!configFile.exists()) {
            return;
         }

         boolean e = false;
         BufferedReader reader = new BufferedReader(new FileReader(configFile));
         if(reader.readLine().equals("# Configuration file")) {
            e = true;
         }

         reader.close();
         if(e) {
            configFile.delete();
         }
      } catch (Exception var3) {
         ;
      }

   }
}
