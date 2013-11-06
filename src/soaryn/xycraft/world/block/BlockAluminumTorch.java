package soaryn.xycraft.world.block;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import soaryn.xycraft.world.block.BlockSulfurTorch;

public class BlockAluminumTorch extends BlockSulfurTorch {

   public BlockAluminumTorch(int id, int icon) {
      super(id, icon);
   }

   public int tickRate() {
      return 10;
   }

   public void onBlockAdded(World world, int x, int y, int z) {
      super.onBlockAdded(world, x, y, z);
   }
    public void registerIcons(IconRegister icon) {
        blockIcon = icon.registerIcon("xycraft:aluminumtorch");
    }

   public void updateTick(World world, int x, int y, int z, Random rand) {}
}
