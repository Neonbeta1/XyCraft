package soaryn.xycraft.world.block;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import soaryn.xycraft.core.XyCraft;

public class BlockWorld extends Block {

    protected BlockWorld(int id, int icon, Material material) {
        super(id, material);
       // this.setRequiresSelfNotify();
        this.setCreativeTab(XyCraft.tabBlocks);
        //this.setTextureFile("/soaryn/xycraft/world/sprites/sprite_blocks.png");
        this.setHardness(2.0F);
    }

    public void addCreativeItems(ArrayList itemList) {
        itemList.add(new ItemStack(this));
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(this, 1, world.getBlockMetadata(x, y, z));
    }
}
