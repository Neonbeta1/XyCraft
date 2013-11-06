package soaryn.xycraft.world.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.oredict.OreDictionary;
import soaryn.util.UtilRecipe;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.world.XyCraftWorldBlocks;
import soaryn.xycraft.world.XyCraftWorldItems;
import soaryn.xycraft.world.handler.FuelHandlerWorld;
import soaryn.xycraft.world.item.ItemLiquid;

public class XyWorldRecipe {

   private static boolean tempRecipeOverride = true;


   public static void init() {
      /*GameRegistry.registerFuelHandler(new FuelHandlerWorld());
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldItems.blueXychorium, new Object[]{XyCraftWorldItems.blueXychoridite});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldItems.greenXychorium, new Object[]{XyCraftWorldItems.greenXychoridite});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldItems.redXychorium, new Object[]{XyCraftWorldItems.redXychoridite});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldItems.darkXychorium, new Object[]{XyCraftWorldItems.darkXychoridite});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldItems.lightXychorium, new Object[]{XyCraftWorldItems.lightXychoridite});
      UtilRecipe.addVanillaSmelting(XyCraftWorldItems.blueXychorium, XyCraftWorldItems.blueXychoridite);
      UtilRecipe.addVanillaSmelting(XyCraftWorldItems.greenXychorium, XyCraftWorldItems.greenXychoridite);
      UtilRecipe.addVanillaSmelting(XyCraftWorldItems.redXychorium, XyCraftWorldItems.redXychoridite);
      UtilRecipe.addVanillaSmelting(XyCraftWorldItems.darkXychorium, XyCraftWorldItems.darkXychoridite);
      UtilRecipe.addVanillaSmelting(XyCraftWorldItems.lightXychorium, XyCraftWorldItems.lightXychoridite);
      UtilRecipe.addVanillaSmelting(new ItemStack(XyCraftWorldBlocks.xyOre, 1, 5), XyCraftWorldItems.aluminum);
      if(!XyConfig.enableMachines || tempRecipeOverride) {
         UtilRecipe.addVanillaSmelting(Item.gunpowder, XyCraftWorldItems.sulfur);
         UtilRecipe.addVanillaSmelting(XyCraftWorldItems.sulfur, XyCraftWorldItems.sulfurGoo);
      }

      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 6), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriumBlue"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 7), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriumGreen"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 8), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriumRed"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 9), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriumDark"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 10), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriumLight"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 11), new Object[]{"###", "###", "###", Character.valueOf('#'), "naturalAluminum"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 6), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriditeBlue"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 7), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriditeGreen"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 8), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriditeRed"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 9), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriditeDark"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyOre, 1, 10), new Object[]{"###", "###", "###", Character.valueOf('#'), "xychoriditeLight"});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.blueXychorium, 9), new Object[]{new ItemStack(XyCraftWorldBlocks.xyOre, 1, 6)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.greenXychorium, 9), new Object[]{new ItemStack(XyCraftWorldBlocks.xyOre, 1, 7)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.redXychorium, 9), new Object[]{new ItemStack(XyCraftWorldBlocks.xyOre, 1, 8)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.darkXychorium, 9), new Object[]{new ItemStack(XyCraftWorldBlocks.xyOre, 1, 9)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.lightXychorium, 9), new Object[]{new ItemStack(XyCraftWorldBlocks.xyOre, 1, 10)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.aluminum, 9), new Object[]{new ItemStack(XyCraftWorldBlocks.xyOre, 1, 11)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 0), new Object[]{"#B#", "BBB", "#B#", Character.valueOf('#'), "xychoriditeBlue", Character.valueOf('B'), Block.stoneBrick});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 1), new Object[]{"#B#", "BBB", "#B#", Character.valueOf('#'), "xychoriditeGreen", Character.valueOf('B'), Block.stoneBrick});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 2), new Object[]{"#B#", "BBB", "#B#", Character.valueOf('#'), "xychoriditeRed", Character.valueOf('B'), Block.stoneBrick});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 3), new Object[]{"#B#", "BBB", "#B#", Character.valueOf('#'), "xychoriditeDark", Character.valueOf('B'), Block.stoneBrick});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 4), new Object[]{"#B#", "BBB", "#B#", Character.valueOf('#'), "xychoriditeLight", Character.valueOf('B'), Block.stoneBrick});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 5), new Object[]{"#B#", "BOB", "#B#", Character.valueOf('#'), "xychoriditeBlue", Character.valueOf('B'), Block.stoneBrick, Character.valueOf('O'), Block.obsidian});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 6), new Object[]{"#B#", "BOB", "#B#", Character.valueOf('#'), "xychoriditeGreen", Character.valueOf('B'), Block.stoneBrick, Character.valueOf('O'), Block.obsidian});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 7), new Object[]{"#B#", "BOB", "#B#", Character.valueOf('#'), "xychoriditeRed", Character.valueOf('B'), Block.stoneBrick, Character.valueOf('O'), Block.obsidian});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 8), new Object[]{"#B#", "BOB", "#B#", Character.valueOf('#'), "xychoriditeDark", Character.valueOf('B'), Block.stoneBrick, Character.valueOf('O'), Block.obsidian});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyAesthetic, 4, 9), new Object[]{"#B#", "BOB", "#B#", Character.valueOf('#'), "xychoriditeLight", Character.valueOf('B'), Block.stoneBrick, Character.valueOf('O'), Block.obsidian});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.blueXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 0)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.greenXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 1)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.redXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 2)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.darkXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 3)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.lightXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 4)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.blueXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 5)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.greenXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 6)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.redXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 7)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.darkXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 8)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, new ItemStack(XyCraftWorldItems.lightXychoridite), new Object[]{new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, 9)});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xychoridite2, 4, 0), new Object[]{"#B#", "BIB", "#B#", Character.valueOf('#'), "xychoriditeBlue", Character.valueOf('B'), "naturalAluminum", Character.valueOf('I'), Item.ingotIron});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xychoridite2, 4, 1), new Object[]{"#B#", "BIB", "#B#", Character.valueOf('#'), "xychoriditeGreen", Character.valueOf('B'), "naturalAluminum", Character.valueOf('I'), Item.ingotIron});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xychoridite2, 4, 2), new Object[]{"#B#", "BIB", "#B#", Character.valueOf('#'), "xychoriditeRed", Character.valueOf('B'), "naturalAluminum", Character.valueOf('I'), Item.ingotIron});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xychoridite2, 4, 3), new Object[]{"#B#", "BIB", "#B#", Character.valueOf('#'), "xychoriditeDark", Character.valueOf('B'), "naturalAluminum", Character.valueOf('I'), Item.ingotIron});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xychoridite2, 4, 4), new Object[]{"#B#", "BIB", "#B#", Character.valueOf('#'), "xychoriditeLight", Character.valueOf('B'), "naturalAluminum", Character.valueOf('I'), Item.ingotIron});

      int i;
      for(i = 0; i < 5; ++i) {
         UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xychoridite2, 4, i + 5), new Object[]{"I#I", "#I#", "I#I", Character.valueOf('#'), new ItemStack(XyCraftWorldBlocks.xyAesthetic, 1, i + 5), Character.valueOf('I'), Item.ingotIron});
      }

      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.sulfurTorch, 4), new Object[]{"#", "S", Character.valueOf('#'), "dustSulfur", Character.valueOf('S'), Item.stick});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldBlocks.aluminumTorch, new Object[]{"naturalAluminum", XyCraftWorldBlocks.sulfurTorch});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, XyCraftWorldItems.twine, new Object[]{XyCraftWorldItems.henequenLeaf});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shapeless, Item.silk, new Object[]{XyCraftWorldItems.twine, XyCraftWorldItems.twine, XyCraftWorldItems.twine, XyCraftWorldItems.twine});
      GameRegistry.addSmelting(XyCraftWorldItems.kernel.itemID, new ItemStack(XyCraftWorldItems.popcorn), 2.0F);
      GameRegistry.addSmelting(XyCraftWorldItems.corn.itemID, new ItemStack(XyCraftWorldItems.cornCob), 3.0F);

      for(i = 0; i < 16; ++i) {
         UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.structureBlock, 4, ~i & 15), new Object[]{"X#X", "#D#", "X#X", Character.valueOf('#'), Block.stoneBrick, Character.valueOf('X'), "xychoriditeMetal", Character.valueOf('D'), XyReferences.OreDictionaryNames.dyeNames[15 - i]});
         UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.structureBlock, 8, ~i & 15), new Object[]{"XXX", "X#X", "XXX", Character.valueOf('#'), XyReferences.OreDictionaryNames.dyeNames[15 - i], Character.valueOf('X'), XyCraftWorldBlocks.structureBlock});
      }

      for(i = 0; i < 16; ++i) {
         UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyLight, 1, ~i & 15), new Object[]{"XRX", "GDG", "XRX", Character.valueOf('G'), Item.glowstone, Character.valueOf('R'), Item.redstone, Character.valueOf('X'), "xychoriditeMetal", Character.valueOf('D'), XyReferences.OreDictionaryNames.dyeNames[15 - i]});
         UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.xyLightInv, 1, ~i & 15), new Object[]{"XRX", "GDG", "XRX", Character.valueOf('G'), Item.glowstone, Character.valueOf('R'), Block.torchRedstoneActive, Character.valueOf('X'), "xychoriditeMetal", Character.valueOf('D'), XyReferences.OreDictionaryNames.dyeNames[15 - i]});
      }

      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.glassViewer, 8), new Object[]{"GGG", "GIG", "GGG", Character.valueOf('G'), new ItemStack(Block.glass, 1, 0), Character.valueOf('I'), Item.ingotIron});
      UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, new ItemStack(XyCraftWorldBlocks.glassViewer, 8), new Object[]{"GGG", "GIG", "GGG", Character.valueOf('G'), new ItemStack(Block.glass, 1, 0), Character.valueOf('I'), "refinedIron"});
      initPluginRecipes();      */
   }

   private static void initPluginRecipes() {
      /*try {
         if(XyCraft.thermalExpansion.isModLoaded()) {
            CraftingHelpers.addSmelterOreToIngotsRecipe(new ItemStack(XyCraftWorldBlocks.xyOre, 1, 5), new ItemStack(XyCraftWorldItems.aluminum));
            CraftingManagers.smelterManager.addRecipe(320, new ItemStack(XyCraftWorldItems.aluminum, 2), new ItemStack(Block.glass, 4), new ItemStack(XyCraftWorldBlocks.glassViewer, 4), false);

            for(int e = 0; e < 16; ++e) {
               Iterator i$ = OreDictionary.getOres(XyReferences.OreDictionaryNames.dyeNames[e]).iterator();

               while(i$.hasNext()) {
                  ItemStack dye = (ItemStack)i$.next();
                  if(dye != null) {
                     dye.stackSize = 1;
                     CraftingManagers.crucibleManager.addRecipe(240, dye, LiquidDictionary.getLiquid(ItemLiquid.getName(e), 125), false);
                     CraftingManagers.transposerManager.addFillRecipe(320, new ItemStack(Block.cloth), new ItemStack(Block.cloth, 1, e), LiquidDictionary.getLiquid(ItemLiquid.getName(e), 25), false, false);
                  }
               }
            }
         }
      } catch (Exception var5) {
         ;
      }

      try {
         if(XyCraft.forestry.isModLoaded()) {
            ItemStack var6 = XyCraft.forestry.getItemStack("canEmpty");
            if(var6 != null) {
               var6.stackSize = 4;
            }

            UtilRecipe.addVanillaRecipe(UtilRecipe.EnumRecipeType.Shaped, var6, new Object[]{" a ", "a a", " a ", Character.valueOf('a'), "naturalAluminum"});
            RecipeManagers.squeezerManager.addRecipe(20, new ItemStack[]{new ItemStack(XyCraftWorldItems.henequenSeeds)}, LiquidDictionary.getLiquid("seedoil", 50));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldItems.aluminum));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldBlocks.xyOre, 1, 5));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldItems.blueXychorium));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldItems.greenXychorium));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldItems.redXychorium));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldItems.darkXychorium));
            BackpackManager.backpackItems[0].add(new ItemStack(XyCraftWorldItems.lightXychorium));
         }
      } catch (Exception var4) {
         ;
      }

      try {
         if(XyCraft.ic2.isModLoaded()) {
            ;
         }
      } catch (Exception var3) {
         ;
      }  */

   }

}
