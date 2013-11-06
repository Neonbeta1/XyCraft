package soaryn.util;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import soaryn.xycraft.core.DebugLogger;
import soaryn.xycraft.core.XyCraft;

public class UtilRecipe
{
    public static void addVanillaRecipe(EnumRecipeType type, Object result, Object[] recipe)
    {
        try
        {
            ItemStack stack = null;
            if ((result instanceof ItemStack))
                stack = (ItemStack)result;
            else if ((result instanceof Item))
                stack = new ItemStack((Item)result, 1);
            else if ((result instanceof Block)) {
                stack = new ItemStack((Block)result, 1);
            }
            if (stack == null) {
                if (DebugLogger.isDebugging()) {
                    XyCraft.logger.log(Level.WARNING, "Recipe had no result: " + type + ":: " + recipe);
                }
                return;
            }
            IRecipe iRecipe = null;
            switch (type.ordinal()) {
            case 1:
                iRecipe = new ShapedOreRecipe(stack, recipe);
                break;
            case 2:
                iRecipe = new ShapelessOreRecipe(stack, recipe);
        }

            GameRegistry.addRecipe(iRecipe);
        }
        catch (Exception e) {
            if (DebugLogger.isDebugging()) {
                ItemStack stack = null;
                if ((result instanceof ItemStack))
                    stack = (ItemStack)result;
                else if ((result instanceof Item))
                    stack = new ItemStack((Item)result);
                else if ((result instanceof Block)) {
                    stack = new ItemStack((Block)result);
                }
                XyCraft.logger.log(Level.WARNING, "Recipe was not valid: " + (stack != null ? stack : "Null Item") + " :: " + type + " :: " + recipe);
            }
        }
    }

    public static void addVanillaSmelting(Object input, Object result)
    {
        ItemStack stackI = handleObjectType(input);
        ItemStack stackR = handleObjectType(result);
        if ((stackI == null) || (stackR == null)) return;
        FurnaceRecipes.smelting().addSmelting(stackI.itemID, stackI.getItemDamage(), stackR, 0.0F);
    }

    private static ItemStack handleObjectType(Object obj) {
        return handleObjectType(obj, 0);
    }

    private static ItemStack handleObjectType(Object obj, int meta) {
        if ((obj instanceof Block))
            return new ItemStack((Block)obj, 1, meta);
        if ((obj instanceof Item))
            return new ItemStack((Item)obj, 1, meta);
        if ((obj instanceof ItemStack)) {
            return (ItemStack)obj;
        }
        return null;
    }

    public static void addLiquidStackRecipe(EnumRecipeType type, ItemStack result, Object[] recipe, LiquidStack stack)
    {
        for (LiquidContainerData data : LiquidContainerRegistry.getRegisteredLiquidContainerData())
            switch (type.ordinal()) {
        case 1:
            if (UtilLiquid.areLiquidStacksEqual(new LiquidStack[] { stack, data.stillLiquid }))
                addVanillaRecipe(EnumRecipeType.Shaped, result, mergeObjectArrays(recipe, new Object[] { Character.valueOf('#'), data.filled })); break;
        case 2:
            if (UtilLiquid.areLiquidStacksEqual(new LiquidStack[] { stack, data.stillLiquid }))
                addVanillaRecipe(EnumRecipeType.Shapeless, result, mergeObjectArrays(recipe, new Object[] { data.filled }));
            break;
    }
    }

    public static void addShapedLiquidStackRecipe(ItemStack result, Object[] recipe, LiquidStack stack)
    {
        for (LiquidContainerData data : LiquidContainerRegistry.getRegisteredLiquidContainerData())
            if (UtilLiquid.areLiquidStacksEqual(new LiquidStack[] { stack, data.stillLiquid }))
                addVanillaRecipe(EnumRecipeType.Shaped, result, mergeObjectArrays(recipe, new Object[] { Character.valueOf('#'), data.filled }));
    }

    public static void addShapelessLiquidStackRecipe(ItemStack result, Object[] recipe, LiquidStack stack)
    {
        for (LiquidContainerData data : LiquidContainerRegistry.getRegisteredLiquidContainerData())
            if (UtilLiquid.areLiquidStacksEqual(new LiquidStack[] { stack, data.stillLiquid }))
                addVanillaRecipe(EnumRecipeType.Shapeless, result, mergeObjectArrays(recipe, new Object[] { data.filled }));
    }

    private static Object[] mergeObjectArrays(Object[] obj1, Object[] obj2)
    {
        int size1 = obj1.length;
        int size2 = obj2.length;

        Object[] objMerged = new Object[size1 + size2];
        for (int i = 0; i < size1; i++) {
            objMerged[i] = obj1[i];
        }

        for (int i = 0; i < size2; i++) {
            objMerged[(i + size1)] = obj2[i];
        }
        return objMerged;
    }

    public static enum EnumRecipeType
    {
        Shaped, Shapeless;
    }
}