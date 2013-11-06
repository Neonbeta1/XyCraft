package soaryn.xycraft.world.item;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import soaryn.xycraft.core.MetaMap;
import soaryn.xycraft.core.XyConfig;

public class ItemLiquid extends ItemWorld
{
    private static MetaMap upgrades = new MetaMap();

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

   /* public int getIconIndex(ItemStack stack, int pass) {
        int meta = stack.getItemDamage();
        if (getColor(meta) != null) {
            return pass == 1 ? getIndex(meta) : 255;
        }
        return super.getIconIndex(stack);
    }   */

    public int getRenderPasses(int metadata)
    {
        return getColor(metadata) != null ? 1 : 0;
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

    public static String getName(int meta) {
        return ((ItemData)upgrades.get(meta)).displayName;
    }

    public static void registerData(int meta, String itemDisplay, Colour color, int index) {
        upgrades.add(meta, itemDisplay, new ItemData(itemDisplay, index, color));
    }

    public ItemLiquid(int id)
    {
        super(id, 0, 0);
        setCreativeTab(null);
        setHasSubtypes(true);
       // setTextureFile("/soaryn/xycraft/world/sprites/animation.png");
    }

   /* @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int meta)
    {
        return getIndex(meta);
    }    */

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int uh)
    {
        return getColor(stack.getItemDamage()).rgb();
    }

    public void registerLiquid()
    {
        java.util.Map.Entry liquid;
        for(Iterator i$ = upgrades.entrySet().iterator(); i$.hasNext(); LanguageRegistry.addName(new ItemStack(this, 1, ((Integer)liquid.getKey()).intValue()), ((ItemData)liquid.getValue()).displayName))
        {
            liquid = (java.util.Map.Entry)i$.next();
            LiquidStack liquidStack = LiquidDictionary.getOrCreateLiquid(((ItemData)liquid.getValue()).displayName, new LiquidStack(super.itemID, 0, ((Integer)liquid.getKey()).intValue()));
        }

    }

    public void overwriteTextures()
    {
    }

    public String getItemNameIS(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + ((ItemData)upgrades.get(stack.getItemDamage())).displayName.replace(" ", "");
    }

    static {
        int liquidMeta = 0;
        registerData(liquidMeta++, "Molten White Dye", new ColourRGBA(-1), 0);
        registerData(liquidMeta++, "Molten Orange Dye", new ColourRGBA(255, 140, 0, 255), 0);
        registerData(liquidMeta++, "Molten Magenta Dye", new ColourRGBA(-16711681), 0);
        registerData(liquidMeta++, "Molten Light Blue Dye", new ColourRGBA(105, 195, 255, 255), 0);
        registerData(liquidMeta++, "Molten Yellow Dye", new ColourRGBA(-65281), 0);
        registerData(liquidMeta++, "Molten Lime Green Dye", new ColourRGBA(16711935), 0);
        registerData(liquidMeta++, "Molten Pink Dye", new ColourRGBA(255, 145, 175, 255), 0);
        registerData(liquidMeta++, "Molten Gray Dye", new ColourRGBA(-2139062017), 0);
        registerData(liquidMeta++, "Molten Light Gray Dye", new ColourRGBA(-1061109505), 0);
        registerData(liquidMeta++, "Molten Cyan Dye", new ColourRGBA(16777215), 0);
        registerData(liquidMeta++, "Molten Purple Dye", new ColourRGBA(150, 0, 250, 255), 0);
        registerData(liquidMeta++, "Molten Blue Dye", new ColourRGBA(65535), 0);
        registerData(liquidMeta++, "Molten Brown Dye", new ColourRGBA(110, 65, 0, 255), 0);
        registerData(liquidMeta++, "Molten Green Dye", new ColourRGBA(8388863), 0);
        registerData(liquidMeta++, "Molten Red Dye", new ColourRGBA(-16776961), 0);
        registerData(liquidMeta++, "Molten Black Dye", new ColourRGBA(741092607), 0);
        registerData(liquidMeta++, "Primer", new ColourRGBA(210, 170, 95, 255), 0);

        if (XyConfig.enableMachines) {
            liquidMeta = 32;
            registerData(liquidMeta++, "Nitrogen", new ColourRGBA(16777215), 1);
            registerData(liquidMeta++, "Liquid Nitrogen", new ColourRGBA(8421631), 0);
        }
    }

    private static class ItemData
    {
        public int index;
        public Colour colour;
        public String displayName;

        public ItemData(String itemDisplay, int index, Colour colour)
        {
            this.displayName = itemDisplay;
            this.index = index;
            this.colour = colour;
        }
    }
}