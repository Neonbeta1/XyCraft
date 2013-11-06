package soaryn.xycraft.world.render;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import org.lwjgl.opengl.GL11;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.handler.Proxy;

public class RenderItemLiquidContainer
        implements IItemRenderer
{
    private Random random = new Random();
    private ItemStack containerItem;

    public RenderItemLiquidContainer(ItemStack containerItem)
    {
        this.containerItem = containerItem;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
    {
        if (type == IItemRenderer.ItemRenderType.ENTITY) return true;
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object[] data)
    {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        switch (type.ordinal()) {
        case 1:
            this.random.setSeed(187L);
            GL11.glTranslated(-0.5D, -0.375D, 0.0D);
            for (int var20 = 0; var20 < getMiniItemCountForItemStack(stack); var20++)
            {
                if (var20 > 0) {
                    float x = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                    float y = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                    float z = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                    GL11.glTranslatef(x, y, 0.084375F);
                }

                renderLiquidStackEntity(stack);
                renderStackEntity(this.containerItem);
            }
            return;
        case 2:
            GL11.glTranslated(0.0D, 0.0D, -0.0D);
            renderLiquidStackEntity(stack);
            renderStackEntity(this.containerItem);
            return;
        case 3:
            renderLiquidStackInv(stack);
            renderStackInv(this.containerItem);
            return;
    }
    }

    private void renderLiquidStackEntity(ItemStack stack)
    {
        LiquidStack liquidStack = LiquidContainerRegistry.getLiquidForFilledItem(stack);
        if (liquidStack != null) {
            GL11.glPushMatrix();
            GL11.glScalef(0.49F, 0.745F, 0.98F);
            GL11.glTranslatef(0.52F, 0.17F, -0.001F);
            renderStackEntity(liquidStack.asItemStack());
            GL11.glPopMatrix();
        }
    }

    private void renderStackEntity(ItemStack stack) {
        /*RenderEngine engine = Minecraft.getMinecraft().renderEngine;
        engine.bindTexture(engine.getTexture(stack.getItem().getTextureFile())); */
        int color = Item.itemsList[stack.itemID].getColorFromItemStack(stack, 0);
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
       /* int index = stack.getIconIndex();
        float x = (index % 16 * 16 + 0.0F) / 256.0F;
        float y = (index % 16 * 16 + 15.99F) / 256.0F;
        float u = (index / 16 * 16 + 0.0F) / 256.0F;
        float v = (index / 16 * 16 + 15.99F) / 256.0F;
        ItemRenderer.renderItemIn2D(XyCraft.proxy.getTessellator(), y, u, x, v, 0.0625F);  */
    }

    private void renderLiquidStackInv(ItemStack stack) {
        LiquidStack liquidStack = LiquidContainerRegistry.getLiquidForFilledItem(stack);
        if (liquidStack != null) {
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.75F, 0.98F);
            GL11.glTranslatef(8.0F, 2.75F, 0.0F);
            renderStackInv(liquidStack.asItemStack());
            GL11.glPopMatrix();
        }
    }

    private void renderStackInv(ItemStack stack) {
        GL11.glDisable(2896);
      /*  RenderEngine engine = Minecraft.getMinecraft().renderEngine;
        engine.bindTexture(engine.getTexture(stack.getItem().getTextureFile()));  */

        int color = Item.itemsList[stack.itemID].getColorFromItemStack(stack, 0);
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, 1.0F);
       // int var8 = stack.getIconIndex();

       // RenderBlockHelper.renderTexturedQuad(0, 0, var8 % 16 * 16, var8 / 16 * 16, 16, 16, 0.0F);
        GL11.glEnable(2896);
    }

    public byte getMiniItemCountForItemStack(ItemStack stack)
    {
        int var19 = stack.stackSize;
        byte var24;
        if(var19 < 2)
            var24 = 1;
        else
        if(var19 < 16)
            var24 = 2;
        else
        if(var19 < 32)
            var24 = 3;
        else
            var24 = 4;
        return var24;
    }

}