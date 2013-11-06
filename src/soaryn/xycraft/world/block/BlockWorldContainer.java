package soaryn.xycraft.world.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import soaryn.xycraft.core.XyCraft;

public abstract class BlockWorldContainer extends BlockContainer {

   protected BlockWorldContainer(int id, int icon, Material material) {
      super(id, material);
      //this.setRequiresSelfNotify();
      ///this.setTextureFile("/soaryn/xycraft/world/sprites/sprite_blocks.png");
      this.setCreativeTab(XyCraft.tabBlocks);
   }
}
