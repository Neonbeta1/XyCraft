package soaryn.xycraft.core.plugin;

import cpw.mods.fml.common.Loader;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.item.ItemStack;
import soaryn.xycraft.core.XyCraft;

public abstract class PluginHandler
{
    public PluginHandler()
    {
        XyCraft.logger.log(Level.INFO, "Mod Plugin Handler " + (isModLoaded() ? " Loaded" : "not Loaded") + ": " + getModName());
    }

    public boolean isModLoaded() {
        return Loader.isModLoaded(getModName());
    }

    public ItemStack getItemStack(Object itemIdentifier)
    {
        try
        {
            if ((!Loader.isModLoaded(getModName())) || (getAccessor() == null)) return null;
            return getAccessor().getItemStack(itemIdentifier); } catch (Exception e) {
        }
        return null;
    }

    public void init()
    {
        if (!isModLoaded()) return; try
    {
        initRecipes();
    } catch (Exception e) {
        XyCraft.logger.log(Level.INFO, getModName() + " was skipped");
    }
    }

    public abstract String getModName();

    public abstract IStackAccessor getAccessor()
            throws Exception;

    protected abstract void initRecipes()
            throws Exception;

    public static abstract interface IStackAccessor
    {
        public abstract ItemStack getItemStack(Object paramObject)
                throws Exception;
    }
}