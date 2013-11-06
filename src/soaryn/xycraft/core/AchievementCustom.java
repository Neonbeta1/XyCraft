package soaryn.xycraft.core;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public class AchievementCustom extends Achievement {

   public static List achievList = null;
   public static int baseAchievement = 3000;


   public AchievementCustom(String name, String title, String description, int x, int y, Object obj, Achievement ... prereq) {
      super(baseAchievement++, name, x, y, obj instanceof Block?new ItemStack((Block)obj):new ItemStack((Item)obj), prereq.length > 0?prereq[0]:null);
      this.registerAchievement();
      achievList.add(this);
   }

}
