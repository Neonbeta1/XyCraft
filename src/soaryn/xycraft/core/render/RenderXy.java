package soaryn.xycraft.core.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import soaryn.util.UtilRender;
import soaryn.xycraft.world.render.RenderBlockHelper;

public class RenderXy extends RenderBlockHelper implements ISimpleBlockRenderingHandler {

   private static final float minSize = 0.01F;
   private static final float maxSize = 0.99F;


   public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
      try {
         DrawAnimation(renderer, block, meta);
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         DrawFaces(renderer, block, meta);
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
      } catch (Exception var6) {
         ;
      }

   }

   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
      renderAnimation(renderer, world, block, x, y, z);
      renderer.renderStandardBlock(block, x, y, z);
      //renderer.overrideBlockTexture = -1;
      return true;
   }

   public boolean shouldRender3DInInventory() {
      return true;
   }

   public int getRenderId() {
      return UtilRender.RenderIds.renderXy;
   }
}
