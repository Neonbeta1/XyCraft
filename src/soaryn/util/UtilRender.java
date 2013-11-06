package soaryn.util;

import codechicken.lib.vec.Quat;
import codechicken.lib.vec.Vector3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.handler.Proxy;

import java.util.Iterator;

public class UtilRender
{
    public static Quat[] rotationQuats = { Quat.aroundAxis(1.0D, 0.0D, 0.0D, 3.141592653589793D), Quat.aroundAxis(1.0D, 0.0D, 0.0D, 0.0D), Quat.aroundAxis(1.0D, 0.0D, 0.0D, -1.570796326794897D), Quat.aroundAxis(1.0D, 0.0D, 0.0D, 1.570796326794897D), Quat.aroundAxis(0.0D, 0.0D, 1.0D, 1.570796326794897D), Quat.aroundAxis(0.0D, 0.0D, 1.0D, -1.570796326794897D) };

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

    public static Vector3[] AABBtoCorners(AxisAlignedBB aabb)
    {
        return new Vector3[] { new Vector3(aabb.minX, aabb.minY, aabb.minZ), new Vector3(aabb.minX, aabb.minY, aabb.maxZ), new Vector3(aabb.maxX, aabb.minY, aabb.minZ), new Vector3(aabb.maxX, aabb.minY, aabb.maxZ), new Vector3(aabb.minX, aabb.maxY, aabb.minZ), new Vector3(aabb.minX, aabb.maxY, aabb.maxZ), new Vector3(aabb.maxX, aabb.maxY, aabb.minZ), new Vector3(aabb.maxX, aabb.maxY, aabb.maxZ) };
    }

    public static AxisAlignedBB cornersToAABB(Vector3[] corners) {
        Vector3 min = corners[0].copy();
        Vector3 max = corners[0].copy();
        for (int i = 1; i < corners.length; i++) {
            Vector3 vec = corners[i];
            if (vec.x < min.x)
                min.x = vec.x;
            else if (vec.x > max.x) max.x = vec.x;
            if (vec.y < min.y)
                min.y = vec.y;
            else if (vec.y > max.y) max.y = vec.y;
            if (vec.z < min.z)
                min.z = vec.z;
            else if (vec.z > max.z) max.z = vec.z;
        }
        return AxisAlignedBB.getBoundingBox(min.x, min.y, min.z, max.x, max.y, max.z);
    }

    public static void rotateModelSide(ModelRenderer model, int side)
    {
        model.rotateAngleX = (model.rotateAngleY = model.rotateAngleZ = 0.0F);
        switch (side) {
            case 0:
                model.rotateAngleX = 3.141593F;
                break;
            case 2:
                model.rotateAngleX = -1.570796F;
                break;
            case 3:
                model.rotateAngleX = 1.570796F;
                break;
            case 4:
                model.rotateAngleZ = 1.570796F;
                break;
            case 5:
                model.rotateAngleZ = -1.570796F;
            case 1:
        }
    }

    public static void rotateVertsSide(Vector3[] verts, int side, Vector3 add, double scale) {
        Quat rotationQuat = rotationQuats[side];
        for (Vector3 vert : verts) {
            rotationQuat.rotate(vert);
            vert.multiply(scale);
            vert.add(add);
        }
    }

    public static void setBlockModelBounds(Block block, ModelRenderer model, int side)
    {
        AxisAlignedBB aabb = null;
        for(Iterator i$ = model.cubeList.iterator(); i$.hasNext();)
        {
            ModelBox box = (ModelBox)i$.next();
            aabb = expandBox(aabb, box);
        }

        Vector3 verts[] = AABBtoCorners(aabb);
        rotateVertsSide(verts, side, new Vector3(0.5D, 0.5D, 0.5D), 0.0625D);
        aabb = cornersToAABB(verts);
        setBlockBounds(block, aabb);
    }


    public static void setBlockBounds(Block block, AxisAlignedBB aabb) {
        block.setBlockBounds((float)aabb.minX, (float)aabb.minY, (float)aabb.minZ, (float)aabb.maxX, (float)aabb.maxY, (float)aabb.maxZ);
    }

    public static AxisAlignedBB expandBox(AxisAlignedBB aabb, ModelBox box) {
        if (aabb == null) {
            aabb = AxisAlignedBB.getBoundingBox(box.posX1, box.posY1, box.posZ1, box.posX2, box.posY2, box.posZ2);
        } else {
            if (box.posX1 < aabb.minX) aabb.minX = box.posX1;
            if (box.posX2 > aabb.maxX) aabb.maxX = box.posX2;
            if (box.posY1 < aabb.minY) aabb.minY = box.posY1;
            if (box.posY2 > aabb.maxY) aabb.maxY = box.posY2;
            if (box.posZ1 < aabb.minZ) aabb.minZ = box.posZ1;
            if (box.posZ2 > aabb.maxZ) aabb.maxZ = box.posZ2;
        }
        return aabb;
    }

    public static AxisAlignedBB blockBoundsToAABB(Block block) {
        return AxisAlignedBB.getBoundingBox(block.getBlockBoundsMinX(), block.getBlockBoundsMinY(), block.getBlockBoundsMinZ(), block.getBlockBoundsMaxX(), block.getBlockBoundsMaxY(), block.getBlockBoundsMaxZ());
    }

    public static MovingObjectPosition retraceBlock(World world, EntityPlayer player, int x, int y, int z) {
        Vec3 headVec = Vec3.createVectorHelper(player.posX, player.posY + 1.62D - player.yOffset, player.posZ);
        Vec3 lookVec = player.getLook(1.0F);
        double reach = XyCraft.proxy.getBlockReachDistance(player);
        Vec3 endVec = headVec.addVector(lookVec.xCoord * reach, lookVec.yCoord * reach, lookVec.zCoord * reach);
        return Block.blocksList[world.getBlockId(x, y, z)].collisionRayTrace(world, x, y, z, headVec, endVec);
    }

    public static abstract interface ISidedModelOperator
    {
        public abstract boolean operate(ModelRenderer paramModelRenderer, int paramInt1, int paramInt2);
    }

    public static class RenderIds
    {
        public static int renderXy = -1;
        public static int renderCrystal = -1;
        public static int renderEngineeringTable = -1;
    }
}