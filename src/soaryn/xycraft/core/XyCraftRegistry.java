package soaryn.xycraft.core;

import codechicken.lib.config.ConfigTag;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import soaryn.util.Util;
import soaryn.util.Util.IDType;

public class XyCraftRegistry
{
    private static HashMap classTypeMap = new HashMap();
    private static HashMap typeClassMap = new HashMap();

    private LinkedList conflicts = new LinkedList();

    private static void convertTypes(Class[] paramClasses)
    {
        for (int i = 0; i < paramClasses.length; i++)
        {
            Class clazz = paramClasses[i];
            Class type = (Class)classTypeMap.get(clazz);
            if (type != null)
                paramClasses[i] = type;
        }
    }

    private static void revertTypes(Class[] paramClasses)
    {
        for (int i = 0; i < paramClasses.length; i++)
        {
            Class clazz = paramClasses[i];
            Class type = (Class)typeClassMap.get(clazz);
            if (type != null)
                paramClasses[i] = type;
        }
    }

    public Block registerBlock(Class blockClass, Class metaClass, ConfigTag tag, String blockName, String languageName, Object[] params)
    {
        int blockID = tag.getId(blockName);
        if (XyConfig.autoAssign)
            tag.setIntValue(blockID = Util.idCheck(blockID, Util.IDType.Block));
        blockName = tag.qualifiedname + "." + blockName;
        try
        {
            if (Block.blocksList[blockID] != null)
            {
                this.conflicts.add(new IdConflict(Util.IDType.Block, blockName, blockID));
                return null;
            }

            Object[] allParams = new Object[params.length + 1];
            allParams[0] = Integer.valueOf(blockID);
            System.arraycopy(params, 0, allParams, 1, params.length);

            Block block = (Block)findConstructor(blockClass, allParams).newInstance(allParams);
            block.setUnlocalizedName(blockName);
            if (metaClass != null)
            {
                GameRegistry.registerBlock(block, metaClass, blockName);
            }
            else
            {
                GameRegistry.registerBlock(block, blockName);
            }
            if (languageName != null)
                LanguageRegistry.addName(block, languageName);
            return block;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Block registerBlock(Class blockClass, ConfigTag tag, String blockName, String languageName, Object[] params)
    {
        return registerBlock(blockClass, null, tag, blockName, languageName, params);
    }

    public Item registerItem(Class itemClass, ConfigTag tag, String itemName, String languageName, Object[] params)
    {
        int itemID = tag.getId(itemName);
        if (XyConfig.autoAssign)
            tag.setIntValue(itemID = Util.idCheck(itemID, Util.IDType.Item));
        itemName = tag.qualifiedname + "." + itemName;
        try
        {
            if (Item.itemsList[(itemID + 256)] != null)
            {
                this.conflicts.add(new IdConflict(Util.IDType.Item, itemName, itemID + 256));
                return null;
            }

            Object[] allParams = new Object[params.length + 1];
            allParams[0] = Integer.valueOf(itemID);
            System.arraycopy(params, 0, allParams, 1, params.length);

            Item item = (Item)findConstructor(itemClass, allParams).newInstance(allParams);
            item.setUnlocalizedName(itemName);
            if (languageName != null)
                LanguageRegistry.addName(item, languageName);
            return item;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Constructor findConstructor(Class clazz, Object[] params)
            throws NoSuchMethodException
    {
        label86: for (Constructor constructor : clazz.getDeclaredConstructors())
        {
            Class[] paramTypes = constructor.getParameterTypes();

            if (paramTypes.length == params.length)
            {
                revertTypes(paramTypes);
                for (int i = 0; i < paramTypes.length; i++) {
                    if (!paramTypes[i].isInstance(params[i]))
                        break label86;
                }
                return constructor;
            }
        }
        Class[] paramTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++)
            paramTypes[i] = params[i].getClass();
        convertTypes(paramTypes);

        return clazz.getDeclaredConstructor(paramTypes);
    }

    public void throwConflicts()
    {
        if(conflicts.isEmpty())
            return;
        try
        {
            File outputFile = new File("XycraftConflicts.txt");
            if(!outputFile.exists())
                outputFile.createNewFile();
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            IdConflict conflict;
            for(Iterator i$ = conflicts.iterator(); i$.hasNext(); writer.println(conflict.toString()))
                conflict = (IdConflict)i$.next();

            writer.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
        String s = "A list of xycraft id conflicts have been written to the file 'XycraftConflicts.txt' in your minecraft directory";
        FMLCommonHandler.instance().raiseException(new IllegalStateException(s), s, true);
    }


    static
    {
        classTypeMap.put(Integer.class, Integer.TYPE);
        classTypeMap.put(Double.class, Double.TYPE);
        classTypeMap.put(Float.class, Float.TYPE);
        classTypeMap.put(Long.class, Long.TYPE);
        classTypeMap.put(Short.class, Short.TYPE);
        classTypeMap.put(Byte.class, Byte.TYPE);
        classTypeMap.put(Boolean.class, Boolean.TYPE);

        java.util.Map.Entry entry;
        for(Iterator i$ = classTypeMap.entrySet().iterator(); i$.hasNext(); typeClassMap.put(entry.getValue(), entry.getKey()))
            entry = (java.util.Map.Entry)i$.next();


    }

    private static class IdConflict
    {
        Util.IDType type;
        String name;
        int id;

        public IdConflict(Util.IDType type, String name, int id)
        {
            this.type = type;
            this.name = name;
            this.id = id;
        }

        public String toString()
        {
            switch (this.type.ordinal())
            {
                case 1:
                    return "BlockID conflict at " + this.id + " between " + this.name + " and " + Block.blocksList[this.id];
                case 2:
                    return "ItemID conflict at " + this.id + " between " + this.name + " and " + Item.itemsList[this.id];
                case 3:
                    return "AchieivementID conflict at " + this.id + " between " + this.name + " and " + Block.blocksList[this.id];
            }
            return "um... what?";
        }
    }
}