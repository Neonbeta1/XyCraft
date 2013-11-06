package soaryn.xycraft.world.render;

import codechicken.lib.vec.Vector3;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import soaryn.util.UtilRender;
import soaryn.xycraft.world.block.BlockCrystal;
import soaryn.xycraft.world.render.ModelCrystalRender;
import soaryn.xycraft.world.render.RenderBlockHelper;

public class RenderCrystal extends RenderBlockHelper implements ISimpleBlockRenderingHandler {

   private static final ModelCrystalRender model = new ModelCrystalRender();


   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
      Tessellator tess = Tessellator.instance;
      tess.setBrightness(200);
      tess.startDrawingQuads();
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glDisable(2896);
      model.render(1, 1, new Vector3(-0.5D, -0.2D, -0.5D));
      GL11.glColor3f(0.0F, 0.9F, 0.9F);
      Tessellator.instance.draw();
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      GL11.glEnable(2896);
   }

   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
      Tessellator t = Tessellator.instance;
      t.setColorOpaque_I((new Color(0, 200, 200)).getRGB());
      t.setBrightness(200);
      model.render(world.getBlockMetadata(x, y, z), BlockCrystal.getQuantity(world, x, y, z), new Vector3((double)x, (double)y, (double)z));
      return true;
   }

   public boolean shouldRender3DInInventory() {
      return true;
   }

    public int getRenderId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

   /*public int getRenderId() {
      return UtilRender.RenderIds.renderCrystal;
   }   */

}
