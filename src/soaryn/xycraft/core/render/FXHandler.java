package soaryn.xycraft.core.render;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.render.FXSparkle;

public class FXHandler {

   public static void crystalSparkleFx(World world, int x, int y, int z, int color) {
      byte puffs = 2;
      float crystalBorder = 0.4F;
      byte crystalWidth = 3;
      int orient = world.getBlockMetadata(x, y, z);
      if(XyConfig.particles) {
         for(int a = 0; a < puffs; ++a) {
            FXSparkle ef = null;
            float width = crystalBorder + (float)world.rand.nextInt(crystalWidth) / 10.0F;
            float length = crystalBorder + (float)world.rand.nextInt(crystalWidth) / 10.0F;
            float height = (float)world.rand.nextInt(7) / 10.0F + 0.2F;
            switch(orient) {
            case 0:
               ef = new FXSparkle(world, (double)((float)x + length), (double)((float)y + height), (double)((float)z + width), 0.3F, color, 3 + world.rand.nextInt(1));
               break;
            case 1:
               ef = new FXSparkle(world, (double)((float)x + length), (double)((float)y + height), (double)((float)z + width), 0.3F, color, 3 + world.rand.nextInt(1));
               break;
            case 2:
               ef = new FXSparkle(world, (double)((float)x + length), (double)((float)y + width), (double)((float)z + height), 0.3F, color, 3 + world.rand.nextInt(1));
               break;
            case 3:
               ef = new FXSparkle(world, (double)((float)x + length), (double)((float)y + width), (double)((float)z + height), 0.3F, color, 3 + world.rand.nextInt(1));
               break;
            case 4:
               ef = new FXSparkle(world, (double)((float)x + height), (double)((float)y + length), (double)((float)z + width), 0.3F, color, 3 + world.rand.nextInt(1));
               break;
            case 5:
               ef = new FXSparkle(world, (double)((float)x + height), (double)((float)y + length), (double)((float)z + width), 0.3F, color, 3 + world.rand.nextInt(1));
            }

            if(ef != null) {
               ef.setGravity(0.02F);
               Minecraft.getMinecraft().effectRenderer.addEffect(ef);
            }
         }

      }
   }

   public static void sulfurTorchFx(World world, float x, float y, float z) {
      byte puffs = 10;
      float flameBorder = 0.4F;
      byte flameWidth = 2;
      int meta = world.getBlockMetadata((int)x, (int)y, (int)z);
      if(XyConfig.particles) {
         double x1 = (double)(x + 0.5F);
         double y1 = (double)(y + 0.7F);
         double z1 = (double)(z + 0.5F);
         double modH1 = 0.2199999988079071D;
         double modH2 = 0.27000001072883606D;

         for(int a = 0; a < puffs; ++a) {
            FXSparkle ef = null;
            float width = flameBorder + (float)world.rand.nextInt(flameWidth) / 10.0F;
            float var10000 = flameBorder + (float)world.rand.nextInt(flameWidth) / 10.0F;
            float height = (float)world.rand.nextInt(7) / 10.0F + 0.2F;
            switch(meta) {
            case 1:
               ef = new FXSparkle(world, x1 - modH2 + (double)width, y1 + (double)height, z1 + (double)width, 0.3F, 0.2F, 1.0F, 1.0F, 3 + world.rand.nextInt(1));
               break;
            default:
               ef = new FXSparkle(world, x1 + (double)width, y1, z1, 0.3F, 0.2F, 1.0F, 1.0F, 3 + world.rand.nextInt(1));
            }

            if(ef != null) {
               ef.setGravity(-0.3F);
               Minecraft.getMinecraft().effectRenderer.addEffect(ef);
            }
         }

      }
   }
}
