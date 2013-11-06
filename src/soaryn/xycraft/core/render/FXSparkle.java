package soaryn.xycraft.core.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import soaryn.xycraft.core.XyConfig;

public class FXSparkle extends EntityFX {

   public int multiplier;
   public boolean shrink;
   public int particle;
   public boolean tinkle;
   public int blendmode;
    private static final ResourceLocation textur = new ResourceLocation("/xycraft/textures/particles.png");


   public FXSparkle(World world, double d, double d1, double d2, float f, float f1, float f2, float f3, int m) {
      super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
      this.multiplier = 4;
      this.shrink = false;
      this.particle = 0;
      this.tinkle = true;
      this.blendmode = 1;
      if(f1 == 0.0F) {
         f1 = 1.0F;
      }

      super.particleRed = f1;
      super.particleGreen = f2;
      super.particleBlue = f3;
      super.particleGravity = 0.0F;
      super.motionX = super.motionY = super.motionZ = 0.0D;
      super.particleScale *= f;
      super.particleMaxAge = 4 * m;
      this.multiplier = m;
      super.noClip = false;
   }

   public FXSparkle(World world, double x, double y, double z, float scale, int type, int multiplier) {
      this(world, x, y, z, scale, 0.0F, 0.0F, 0.0F, multiplier);
      switch(type) {
      case 0:
         super.particleRed = 0.75F + world.rand.nextFloat() * 0.25F;
         super.particleGreen = 0.25F + world.rand.nextFloat() * 0.25F;
         super.particleBlue = 0.75F + world.rand.nextFloat() * 0.25F;
         break;
      case 1:
         super.particleRed = 0.5F + world.rand.nextFloat() * 0.3F;
         super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
         super.particleBlue = 0.2F;
         break;
      case 2:
         super.particleRed = 0.2F;
         super.particleGreen = 0.2F;
         super.particleBlue = 0.7F + world.rand.nextFloat() * 0.3F;
         break;
      case 3:
         super.particleRed = 0.2F;
         super.particleGreen = 0.7F + world.rand.nextFloat() * 0.3F;
         super.particleBlue = 0.2F;
         break;
      case 4:
         super.particleRed = 0.7F + world.rand.nextFloat() * 0.3F;
         super.particleGreen = 0.2F;
         super.particleBlue = 0.2F;
         break;
      case 5:
         this.blendmode = 771;
         super.particleRed = world.rand.nextFloat() * 0.1F;
         super.particleGreen = world.rand.nextFloat() * 0.1F;
         super.particleBlue = world.rand.nextFloat() * 0.1F;
         break;
      case 6:
         super.particleRed = 0.8F + world.rand.nextFloat() * 0.2F;
         super.particleGreen = 0.8F + world.rand.nextFloat() * 0.2F;
         super.particleBlue = 0.8F + world.rand.nextFloat() * 0.2F;
         break;
      case 7:
         super.particleRed = 0.2F;
         super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
         super.particleBlue = 0.6F + world.rand.nextFloat() * 0.3F;
      }

   }

   public FXSparkle(World world, double d, double d1, double d2, double x, double y, double z, float f, int type, int m) {
      this(world, d, d1, d2, f, type, m);
      double dx = x - super.posX;
      double dy = y - super.posY;
      double dz = z - super.posZ;
      super.motionX = dx / (double)super.particleMaxAge;
      super.motionY = dy / (double)super.particleMaxAge;
      super.motionZ = dz / (double)super.particleMaxAge;
   }

   public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
      tessellator.draw();
      GL11.glPushMatrix();
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, this.blendmode);
       FMLClientHandler.instance().getClient().renderEngine.bindTexture(textur);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
      int part = this.particle + super.particleAge / this.multiplier;
      float var8 = (float)(part % 8) / 8.0F;
      float var9 = var8 + 0.124875F;
      float var10 = (float)(part / 8) / 8.0F;
      float var11 = var10 + 0.124875F;
      float var12 = 0.1F * super.particleScale * ((float)(super.particleMaxAge - super.particleAge + 1) / (float)super.particleMaxAge);
      float var13 = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)f - EntityFX.interpPosX);
      float var14 = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)f - EntityFX.interpPosY);
      float var15 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)f - EntityFX.interpPosZ);
      float var16 = 1.0F;
      tessellator.startDrawingQuads();
      tessellator.setBrightness(240);
      tessellator.setColorRGBA_F(super.particleRed * var16, super.particleGreen * var16, super.particleBlue * var16, 1.0F);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
      tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
      tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
      tessellator.draw();
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glPopMatrix();
    //  GL11.glBindTexture(3553, Minecraft.getMinecraft().renderEngine.getTexture("/particles.png"));
      tessellator.startDrawingQuads();
   }

   public void onUpdate() {
      try {
         EntityClientPlayerMP e = Minecraft.getMinecraft().thePlayer;
         byte visibleDistance = 50;
         if(!Minecraft.getMinecraft().gameSettings.fancyGraphics && !XyConfig.HDTextures) {
            visibleDistance = 25;
         }

         if(e.getDistance(super.posX, super.posY, super.posZ) > (double)visibleDistance) {
            this.setDead();
         }

         super.prevPosX = super.posX;
         super.prevPosY = super.posY;
         super.prevPosZ = super.posZ;
         if(super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(10) == 0) {
            super.worldObj.playSoundAtEntity(this, "random.orb", 0.02F, 0.7F * ((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
         }

         if(super.particleAge++ >= super.particleMaxAge) {
            this.setDead();
         }

         super.motionY -= 0.04D * (double)super.particleGravity;
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         super.motionX *= 0.9800000190734863D;
         super.motionY *= 0.9800000190734863D;
         super.motionZ *= 0.9800000190734863D;
         if(super.onGround) {
            super.motionX *= 0.699999988079071D;
            super.motionZ *= 0.699999988079071D;
         }
      } catch (Exception var3) {
         ;
      }

   }

   public void setGravity(float value) {
      super.particleGravity = value;
   }

   public void setParticle(int part) {
      this.particle = part;
   }
}
