package soaryn.xycraft.world.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import soaryn.xycraft.core.XyConfig;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.core.render.FXSparkle;
import soaryn.xycraft.world.XyCraftWorldItems;

public class BlockSulfurTorch extends BlockTorch {

   private int ticker = 0;
   private long time;


   public BlockSulfurTorch(int id, int icon) {
      super(id);
      this.setLightValue(1.0F);
      this.setCreativeTab(XyCraft.tabBlocks);
      //this.setTextureFile("/soaryn/xycraft/world/sprites/sprite_blocks.png");
   }

    public void registerIcons(IconRegister icon) {
        blockIcon = icon.registerIcon("xycraft:sulfurtorch");
    }
   @SideOnly(Side.CLIENT)
   public void randomDisplayTick(World world, int x, int y, int z, Random r) {
      if(XyConfig.particles) {
         double modY = 0.2199999988079071D;
         double modX = 0.27000001072883606D;
         int meta = world.getBlockMetadata(x, y, z);
         float x1 = (float)x + 0.5F;
         float y1 = (float)y + 0.6F;
         float z1 = (float)z + 0.49F;
         if(meta >= 1 && meta <= 4) {
            y1 = (float)((double)y1 + modY);
         }

         switch(meta) {
         case 1:
            x1 = (float)((double)x1 - modX);
            break;
         case 2:
            x1 = (float)((double)x1 + modX);
            break;
         case 3:
            z1 = (float)((double)z1 - modX);
            break;
         case 4:
            z1 = (float)((double)z1 + modX);
         }

         float offset = r.nextFloat() - r.nextFloat();
         FXSparkle ef2 = new FXSparkle(world, (double)x1, (double)y1, (double)z1, (double)(x1 + (r.nextFloat() - r.nextFloat()) / 4.0F), (double)(y1 + (r.nextFloat() - r.nextFloat()) / 4.0F), (double)(z1 + (r.nextFloat() - r.nextFloat()) / 4.0F), 0.75F, 7, 22);
         ef2.setGravity(-0.013F);
         ef2.setParticle(3);
         ef2.tinkle = true;
         ef2.shrink = true;
         Minecraft.getMinecraft().effectRenderer.addEffect(ef2);
      }
   }

   public int tickRate() {
      return 720;
   }

   public void onBlockAdded(World world, int x, int y, int z) {
      super.onBlockAdded(world, x, y, z);
      world.scheduleBlockUpdate(x, y, z, super.blockID, this.tickRate());
   }

   public void updateTick(World world, int x, int y, int z, Random rand) {
      if(rand.nextInt(257) == 256) {
         this.torchDrop(world, x, y, z);
      }

      world.scheduleBlockUpdate(x, y, z, super.blockID, this.tickRate());
   }

   private void torchDrop(World world, int x, int y, int z) {
      world.getBlockMetadata(x, y, z);
      EntityItem goo = new EntityItem(world, (double)x, (double)y, (double)z, new ItemStack(XyCraftWorldItems.sulfurGoo));
      world.spawnEntityInWorld(goo);
   }
}
