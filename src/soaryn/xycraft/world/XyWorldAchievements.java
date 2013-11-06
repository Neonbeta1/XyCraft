package soaryn.xycraft.world;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import soaryn.util.Util;
import soaryn.xycraft.world.XyCraftWorldBlocks;
import soaryn.xycraft.world.XyCraftWorldItems;

public class XyWorldAchievements {

   public static Achievement crystal;
   public static Achievement xychorium;
   public static Achievement xychoridite;


   public static void init() {
      crystal = Util.registerAchievement(30000, "crystal", 2, 0, new ItemStack(XyCraftWorldBlocks.crystal), "Crystals", "Sparkly!", new Achievement[0]);
      xychorium = Util.registerAchievement(30001, "xychorium", 0, 0, new ItemStack(XyCraftWorldItems.blueXychorium), "Xychorium", "It is glowing...", new Achievement[0]);
      xychoridite = Util.registerAchievement(30002, "xychoridite", -1, 2, new ItemStack(XyCraftWorldItems.blueXychoridite), "Xychoridite", "Now it is glowing...and on Fire", new Achievement[]{xychorium});
   }
}
