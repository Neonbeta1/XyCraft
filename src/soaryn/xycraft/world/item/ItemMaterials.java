package soaryn.xycraft.world.item;

import codechicken.lib.colour.Colour;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import soaryn.xycraft.core.MetaMap;
import soaryn.xycraft.core.lib.XyReferences;
import soaryn.xycraft.core.lib.XyReferences.FormatText;
import soaryn.xycraft.core.lib.XyReferences.FormatText.EnumFormatting;

public class ItemMaterials extends ItemWorld
{
    private static MetaMap upgrades = new MetaMap();

    public ItemMaterials(int id)
    {
        super(id, 0, 0);
        setHasSubtypes(true);
        //setTextureFile("/soaryn/xycraft/machines/sprites/sprite_materials.png");
        setMaxDamage(0);
    }

    public List getItemInfo(ItemStack stack)
    {
        return Arrays.asList(getInfo(stack.getItemDamage()));
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    /*public int getIconFromDamage(int meta)
    {
        return getIndex(meta);
    }      */

   /* public int getIconIndex(ItemStack stack, int pass) {
        int meta = stack.getItemDamage();
        if (getColor(meta) != null) {
            return pass == 1 ? getIndex(meta) : 255;
        }
        return super.getIconIndex(stack, pass);
    }     */

    public int getRenderPasses(int metadata)
    {
        return getColor(metadata) != null ? 1 : 0;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        if (par2 == 1) return super.getColorFromItemStack(par1ItemStack, par2);
        Colour color = getColor(par1ItemStack.getItemDamage());
        return color != null ? color.rgb() : super.getColorFromItemStack(par1ItemStack, par2);
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + ((ItemData)upgrades.get(stack.getItemDamage())).itemName;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creative, List list)
    {
        java.util.Map.Entry set;
        for(Iterator i$ = upgrades.entrySet().iterator(); i$.hasNext(); list.add(new ItemStack(id, 1, ((Integer)set.getKey()).intValue())))
            set = (java.util.Map.Entry)i$.next();

    }

    public void registerNames()
    {
        java.util.Map.Entry set;
        for(Iterator i$ = upgrades.entrySet().iterator(); i$.hasNext(); LanguageRegistry.addName(new ItemStack(super.itemID, 1, ((Integer)set.getKey()).intValue()), ((ItemData)set.getValue()).displayName))
            set = (java.util.Map.Entry)i$.next();

    }

    public static int getMeta(String nameId)
    {
        return upgrades.getMeta(nameId);
    }

    public static int getIndex(int meta) {
        return ((ItemData)upgrades.get(meta)).index;
    }

    public static Colour getColor(int meta) {
        return ((ItemData)upgrades.get(meta)).colour;
    }

    public static String[] getInfo(int meta) {
        return ((ItemData)upgrades.get(meta)).info;
    }

    public static void registerData(int meta, String itemName, String itemDisplay, int index, Colour color, String[] info) {
        upgrades.add(meta, itemName, new ItemData(itemName, itemDisplay, index, color, info));
    }

    static
    {
        registerData(0, "xychoriumBlue", "Blue Xychorium", 0, null, new String[0]);
        registerData(1, "xychoriumGreen", "Green Xychorium", 1, null, new String[0]);
        registerData(2, "xychoriumRed", "Red Xychorium", 2, null, new String[0]);
        registerData(3, "xychoriumDark", "Dark Xychorium", 3, null, new String[0]);
        registerData(4, "xychoriumLight", "Light Xychorium", 4, null, new String[0]);

        registerData(5, "xychoriditeBlue", "Blue Xychoridite", 16, null, new String[0]);
        registerData(6, "xychoriditeGreen", "Green Xychoridite", 17, null, new String[0]);
        registerData(7, "xychoriditeRed", "Red Xychoridite", 18, null, new String[0]);
        registerData(8, "xychoriditeDark", "Dark Xychoridite", 19, null, new String[0]);
        registerData(9, "xychoriditeLight", "Light Xychoridite", 20, null, new String[0]);

        registerData(10, "aluminum", "Aluminum", 5, null, new String[0]);
        registerData(11, "sulfur", "Sulfur", 6, null, new String[0]);

        registerData(16, "silicate", "Silicate", 16, null, new String[] { "Hrmmm Seems " + XyReferences.FormatText.formatS("meltable", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.RED }) });
        registerData(17, "siliconBoule", "Silicon Boule", 17, null, new String[0]);
        registerData(18, "mulch", "Mulch", 18, null, new String[] { "Acts as a " + XyReferences.FormatText.formatS("Fertilizer", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.LIME_GREEN }) });
        registerData(19, "wireIron", "Iron Wire", 19, null, new String[0]);
        registerData(20, "wireAluminum", "Aluminum Wire", 20, null, new String[0]);
        registerData(21, "blankCard", "SD Card", 21, null, new String[] { "Requires " + XyReferences.FormatText.formatS("information ", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.YELLOW }) + "to run" });
        registerData(22, "cardReader", "Card Reader", 22, null, new String[] { XyReferences.FormatText.formatS("Reads information ", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.YELLOW }) + "for misc software utilities" });

        registerData(48, "hedronMold", "Hedron Mold", 64, null, new String[] { XyReferences.FormatText.formatS("Molds ", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.YELLOW }) + "molten Liquids Into a Hedron", "Used in a " + XyReferences.FormatText.formatS("Cooling Chamber", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.BLUE }) });
        registerData(49, "blockMold", "Block Mold", 64, null, new String[] { XyReferences.FormatText.formatS("Molds ", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.YELLOW }) + "molten Liquids Into a Block", "Used in a " + XyReferences.FormatText.formatS("Cooling Chamber", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.BLUE }) });
        registerData(50, "ingotMold", "Ingot Mold", 64, null, new String[] { XyReferences.FormatText.formatS("Molds ", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.YELLOW }) + "molten Liquids Into a Ingot", "Used in a " + XyReferences.FormatText.formatS("Cooling Chamber", new XyReferences.FormatText.EnumFormatting[] { XyReferences.FormatText.EnumFormatting.BLUE }) });
    }

    private static class ItemData
    {
        public int index;
        public Colour colour;
        public String[] info;
        public String itemName;
        public String displayName;

        public ItemData(String itemName, String itemDisplay, int index, Colour colour, String[] info)
        {
            this.itemName = itemName;
            this.displayName = itemDisplay;
            this.index = index;
            this.colour = colour;
            this.info = info;
        }
    }
}