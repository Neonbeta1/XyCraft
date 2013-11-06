package soaryn.xycraft.core.render;

import codechicken.lib.colour.ColourRGBA;
import cpw.mods.fml.common.FMLCommonHandler;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Icon;

public class CustomGradient {

   private int[] gradient;


   /*public CustomGradient(Icon textureFile) {
      try {
         BufferedImage var5 = ImageIO.read(Minecraft.getMinecraft().mcResourcePackRepository.getSelectedTexturePack().getResourceAsStream(textureFile));
         int[] data = new int[var5.getWidth()];
         var5.getRGB(0, 0, var5.getWidth(), 1, data, 0, var5.getWidth());
         this.gradient = new int[var5.getWidth()];

         for(int i = 0; i < data.length; ++i) {
            this.gradient[i] = data[i] << 8 | data[i] >> 24 & 255;
         }
      } catch (IOException var51) {
         FMLCommonHandler.instance().raiseException(var51, "Error while reading gradient: " + textureFile, true);
      }
   }     */

   public ColourRGBA getColour(double position) {
      int off = (int)((double)this.gradient.length * position);
      if(off >= this.gradient.length) {
         off = this.gradient.length - 1;
      } else if(off < 0) {
         off = 0;
      }

      return new ColourRGBA(this.gradient[off]);
   }
}
