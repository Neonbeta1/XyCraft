package soaryn.util;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class Util
{
    public static HashMap materialMap = null;

    private static List blockList = new ArrayList();
    private static List itemList = new ArrayList();
    private static List achievList = new ArrayList();

    public static HashMap initMatMap()
    {
        if (materialMap == null) {
            materialMap = new HashMap();
            materialMap.put(Integer.valueOf(0), Arrays.asList(new Material[] { Material.cloth, Material.dragonEgg, Material.cake, Material.circuits, Material.tnt, Material.sponge, Material.snow }));
            materialMap.put(Integer.valueOf(1), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), Arrays.asList(new Material[] { Material.rock, Material.iron, Material.piston, Material.glass, Material.ice }) }));
            materialMap.put(Integer.valueOf(2), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), Arrays.asList(new Material[] { Material.ground, Material.sand, Material.grass, Material.craftedSnow, Material.clay, Material.snow }) }));
            materialMap.put(Integer.valueOf(3), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(2)) }));
            materialMap.put(Integer.valueOf(4), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), Arrays.asList(new Material[] { Material.wood, Material.web, Material.cactus, Material.pumpkin, Material.leaves, Material.plants }) }));
            materialMap.put(Integer.valueOf(5), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(4)) }));
            materialMap.put(Integer.valueOf(6), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), (List)materialMap.get(Integer.valueOf(2)), (List)materialMap.get(Integer.valueOf(4)) }));
            materialMap.put(Integer.valueOf(7), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(2)), (List)materialMap.get(Integer.valueOf(4)) }));
            materialMap.put(Integer.valueOf(8), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), Arrays.asList(new Material[] { Material.plants }) }));
            materialMap.put(Integer.valueOf(9), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(8)) }) }));
            materialMap.put(Integer.valueOf(10), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(2)), (List)materialMap.get(Integer.valueOf(8)) }) }));
            materialMap.put(Integer.valueOf(11), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(2)), (List)materialMap.get(Integer.valueOf(8)) }) }));
            materialMap.put(Integer.valueOf(12), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(4)), (List)materialMap.get(Integer.valueOf(8)) }) }));
            materialMap.put(Integer.valueOf(13), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(4)), (List)materialMap.get(Integer.valueOf(8)) }) }));
            materialMap.put(Integer.valueOf(14), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(2)), (List)materialMap.get(Integer.valueOf(4)), (List)materialMap.get(Integer.valueOf(8)) }) }));
            materialMap.put(Integer.valueOf(15), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(0)), mergeLists(new List[] { (List)materialMap.get(Integer.valueOf(1)), (List)materialMap.get(Integer.valueOf(2)), (List)materialMap.get(Integer.valueOf(4)), (List)materialMap.get(Integer.valueOf(8)) }) }));
        }
        return materialMap;
    }

    public static List mergeLists(List[] list) {
        List result = new ArrayList();
        for (List l : list) {
            result.addAll(l);
        }
        return result;
    }

    public static int idCheck(int id, IDType type)
    {
        if (type == IDType.Block) {
            while ((net.minecraft.block.Block.blocksList[id] != null) || (blockList.contains(Integer.valueOf(id)))) {
                id++;
            }
            blockList.add(Integer.valueOf(id));
        }
        else if (type == IDType.Item) {
            while ((net.minecraft.item.Item.itemsList[(id + 256)] != null) || (itemList.contains(Integer.valueOf(id)))) {
                id++;
            }
            itemList.add(Integer.valueOf(id));
        } else if (type == IDType.Achievement) {
            while ((AchievementList.achievementList.get(id) != null) || (achievList.contains(Integer.valueOf(id)))) {
                id++;
            }
            achievList.add(Integer.valueOf(id));
        }

        return id;
    }

    @SideOnly(Side.CLIENT)
    public static Achievement registerAchievement(int id, String idName, int x, int y, ItemStack stack, String name, String desc, Achievement[] required)
    {
        Achievement achievement = new Achievement(id, idName, x, y, stack, required.length > 0 ? required[0] : null);

        String achievementName = achievement.getName();
        LanguageRegistry.instance().addStringLocalization(achievementName, name);
        LanguageRegistry.instance().addStringLocalization(achievementName + ".desc", desc);
        return achievement.registerAchievement();
    }

    public static enum IDType
    {
        Block, Item, Achievement;
    }
}