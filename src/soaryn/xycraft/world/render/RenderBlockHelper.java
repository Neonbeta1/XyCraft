package soaryn.xycraft.world.render;

import codechicken.lib.colour.Colour;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.FloatBuffer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.handler.Proxy;
import soaryn.xycraft.core.render.IAnimationSideHandler;

public class RenderBlockHelper
{

    @SideOnly(Side.CLIENT)
    private static final Vec3 field_82884_b = Vec3.createVectorHelper(0.2000000029802322D, 1.0D, -0.699999988079071D).normalize();

    @SideOnly(Side.CLIENT)
    private static final Vec3 field_82885_c = Vec3.createVectorHelper(-0.2000000029802322D, 1.0D, 0.699999988079071D).normalize();

    @SideOnly(Side.CLIENT)
    private static final FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);

    @SideOnly(Side.CLIENT)
    public static void DrawFaces(RenderBlocks renderblocks, Block block, Icon blockIcon2, Icon icon2, Icon blockIcon1, Icon icon1, Icon blockIcon, Icon icon, boolean solidtop)
    {
//        DrawFaces(renderblocks, block, 0);
    }

    @SideOnly(Side.CLIENT)
    public static void DrawFaces(RenderBlocks renderblocks, Block block, int meta) {
        int color = block.getRenderColor(meta);
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor3f(red, green, blue);
//        DrawFaces(renderblocks, block, block.getIcon(0, meta), block.getIcon(1, meta), block.getIcon(2, meta), block.getIcon(3, meta), block.getIcon(4, meta), block.getIcon(5, meta), false);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public static void DrawFaces(RenderBlocks renderblocks, Block block, int i, boolean solidTop) {
        DrawFaces(renderblocks, block, i, i, i, i, i, i, solidTop);
    }

    @SideOnly(Side.CLIENT)
    public static void DrawFaces(RenderBlocks renderblocks, Block block, int i1, int i2, int i3, int i4, int i5, int i6, boolean solidtop) {
        Tessellator tessellator = Tessellator.instance;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        //renderblocks.renderBottomFace(block, 0.0D, 0.0D, 0.0D, i1);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        //renderblocks.renderTopFace(block, 0.0D, 0.0D, 0.0D, i2);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        //renderblocks.renderWestFace(block, 0.0D, 0.0D, 0.0D, i3);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
       // renderblocks.renderEastFace(block, 0.0D, 0.0D, 0.0D, i4);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
      //  renderblocks.renderSouthFace(block, 0.0D, 0.0D, 0.0D, i5);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      //  renderblocks.renderNorthFace(block, 0.0D, 0.0D, 0.0D, i6);
        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @SideOnly(Side.CLIENT)
    public static void DrawAnimation(RenderBlocks renderer, Block block, int meta)
    {
        DrawAnimation(renderer, block, meta, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public static void DrawAnimation(RenderBlocks renderer, Block block, int meta, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (!(block instanceof IAnimationSideHandler)) return;
        IAnimationSideHandler animatedBlock = (IAnimationSideHandler)block;
        Tessellator tessellator = XyCraft.proxy.getTessellator();

        float scale = 0.002F;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        ForgeDirection side = ForgeDirection.DOWN;
        int animatedIcon = animatedBlock.getAnimationIndex(meta, side);
        Colour animatedColor = animatedBlock.getItemColor(meta, side);
        int animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
        if (animatedColor != null) {
            setColorWithBrightness(animatedColor, animatedBrightness);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
           // renderer.renderBottomFace(block, -side.offsetX * scale, minY - side.offsetY * scale, -side.offsetZ * scale, animatedIcon);
            tessellator.draw();
        }

        side = ForgeDirection.UP;
        animatedIcon = animatedBlock.getAnimationIndex(meta, side);
        animatedColor = animatedBlock.getItemColor(meta, side);
        animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
        if (animatedColor != null) {
            setColorWithBrightness(animatedColor, animatedBrightness);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
          //  renderer.renderTopFace(block, -side.offsetX * scale, 0.0F - side.offsetY * scale, -side.offsetZ * scale, animatedIcon);
            tessellator.draw();
        }

        side = ForgeDirection.SOUTH;
        animatedIcon = animatedBlock.getAnimationIndex(meta, side);
        animatedColor = animatedBlock.getItemColor(meta, side);
        animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
        if (animatedColor != null) {
            setColorWithBrightness(animatedColor, animatedBrightness);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
          ///  renderer.renderWestFace(block, -side.offsetX * scale, -side.offsetY * scale, -side.offsetZ * scale, animatedIcon);
            tessellator.draw();
        }

        side = ForgeDirection.NORTH;
        animatedIcon = animatedBlock.getAnimationIndex(meta, side);
        animatedColor = animatedBlock.getItemColor(meta, side);
        animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
        if (animatedColor != null) {
            setColorWithBrightness(animatedColor, animatedBrightness);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1.0F);
        //    renderer.renderEastFace(block, -side.offsetX * scale, -side.offsetY * scale, -side.offsetZ * scale, animatedIcon);
            tessellator.draw();
        }

        side = ForgeDirection.EAST;
        animatedIcon = animatedBlock.getAnimationIndex(meta, side);
        animatedColor = animatedBlock.getItemColor(meta, side);
        animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
        if (animatedColor != null) {
            setColorWithBrightness(animatedColor, animatedBrightness);
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
         //   renderer.renderSouthFace(block, -side.offsetX * scale, -side.offsetY * scale, -side.offsetZ * scale, animatedIcon);
            tessellator.draw();
        }

        side = ForgeDirection.WEST;
        animatedIcon = animatedBlock.getAnimationIndex(meta, side);
        animatedColor = animatedBlock.getItemColor(meta, side);
        animatedBrightness = animatedBlock.getAnimationBrightness(meta, side);
        if (animatedColor != null) {
            setColorWithBrightness(animatedColor, animatedBrightness);
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0.0F, 0.0F);
         //   renderer.renderNorthFace(block, -side.offsetX * scale, -side.offsetY * scale, -side.offsetZ * scale, animatedIcon);
            tessellator.draw();
        }
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        RenderHelper.enableStandardItemLighting();
    }

    @SideOnly(Side.CLIENT)
    public static void renderAnimation(RenderBlocks renderer, IBlockAccess world, Block block, int x, int y, int z)
    {
        renderAnimation(renderer, world, block, x, y, z, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public static void renderAnimation(RenderBlocks renderer, IBlockAccess iBlockAccess, Block block, int x, int y, int z, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (!(block instanceof IAnimationSideHandler)) return;
        IAnimationSideHandler animatedBlock = (IAnimationSideHandler)block;
        Tessellator tess = XyCraft.proxy.getTessellator();
        renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
        for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
            renderFace(tess, renderer, block, animatedBlock, iBlockAccess, x, y, z, side, false);
    }

    @SideOnly(Side.CLIENT)
    public static boolean renderFace(Tessellator tess, RenderBlocks renderer, Block block, IAnimationSideHandler animatedBlock, IBlockAccess iBlockAccess, int x, int y, int z, ForgeDirection side, boolean override)
    {
        int index = animatedBlock.getAnimationIndex(iBlockAccess, x, y, z, side);
        Colour color = animatedBlock.getColor(iBlockAccess, x, y, z, side);
        int brightness = animatedBlock.getAnimationBrightness(iBlockAccess, x, y, z, side);

        if ((color == null) || ((!override) && (!block.shouldSideBeRendered(iBlockAccess, x + side.offsetX, y + side.offsetY, z + side.offsetZ, side.ordinal())))) return false;

        tess.setColorOpaque_I(color.rgb());

        tess.setBrightness(brightness);

       /* switch (side.ordinal()) {
        case 1:
            renderer.renderBottomFace(block, x, y, z, index);
            break;
        case 2:
            renderer.renderTopFace(block, x, y, z, index);
            break;
        case 3:
            renderer.renderEastFace(block, x, y, z, index);
            break;
        case 4:
            renderer.renderWestFace(block, x, y, z, index);
            break;
        case 5:
            renderer.renderNorthFace(block, x, y, z, index);
            break;
        case 6:
            renderer.renderSouthFace(block, x, y, z, index);
            break;
    }        */

        return true;
    }

    @SideOnly(Side.CLIENT)
    public static void renderTexturedQuad(int par1, int par2, int par3, int par4, int par5, int par6, float zLevel)
    {
        float var7 = 0.0039063F;
        float var8 = 0.0039063F;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, zLevel, (par3 + 0) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, zLevel, (par3 + par5) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, zLevel, (par3 + par5) * var7, (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, zLevel, (par3 + 0) * var7, (par4 + 0) * var8);
        var9.draw();
    }

    @SideOnly(Side.CLIENT)
    public static void setColorWithBrightness(Colour animatedColor, int brightness)
    {
        animatedColor.glColour(255);
        GL11.glEnable(2896);
        GL11.glEnable(16384);
        GL11.glEnable(16385);
        GL11.glEnable(2903);
        GL11.glColorMaterial(1032, 5634);
        float var0 = brightness / 255.0F;
        float var1 = 0.1F;
        GL11.glLight(16384, 4611, setColorBuffer(field_82884_b.xCoord, field_82884_b.yCoord, field_82884_b.zCoord, 0.0D));
        GL11.glLight(16384, 4609, setColorBuffer(var1, var1, var1, 1.0F));
        GL11.glLight(16384, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(16384, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(16385, 4611, setColorBuffer(field_82885_c.xCoord, field_82885_c.yCoord, field_82885_c.zCoord, 0.0D));
        GL11.glLight(16385, 4609, setColorBuffer(var1, var1, var1, 1.0F));
        GL11.glLight(16385, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(16385, 4610, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glShadeModel(7424);
        GL11.glLightModel(2899, setColorBuffer(var0, var0, var0, 1.0F));
    }

    @SideOnly(Side.CLIENT)
    private static FloatBuffer setColorBuffer(double par0, double par2, double par4, double par6)
    {
        return setColorBuffer((float)par0, (float)par2, (float)par4, (float)par6);
    }

    @SideOnly(Side.CLIENT)
    private static FloatBuffer setColorBuffer(float par0, float par1, float par2, float par3)
    {
        if (colorBuffer == null) return null;
        colorBuffer.clear();
        colorBuffer.put(par0).put(par1).put(par2).put(par3);
        colorBuffer.flip();
        return colorBuffer;
    }
}