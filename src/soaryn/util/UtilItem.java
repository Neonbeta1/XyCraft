package soaryn.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class UtilItem {

   public static boolean emulateBonemeal(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, Random itemRand) {
      if(stack == null) {
         return false;
      } else {
         int id = world.getBlockId(x, y, z);
         BonemealEvent event = new BonemealEvent(player, world, id, x, y, z);
         if(MinecraftForge.EVENT_BUS.post(event)) {
            return false;
         } else if(event.getResult() == Result.ALLOW) {
            return true;
         } else if(id == Block.sapling.blockID) {
            if(!world.isRemote) {
               ((BlockSapling)Block.sapling).growTree(world, x, y, z, world.rand);
            }

            return true;
         } else if(id != Block.mushroomBrown.blockID && id != Block.mushroomRed.blockID) {
            if(id != Block.melonStem.blockID && id != Block.pumpkinStem.blockID) {
               if(id > 0 && Block.blocksList[id] instanceof BlockCrops) {
                  if(world.getBlockMetadata(x, y, z) == 7) {
                     return false;
                  } else {
                     if(!world.isRemote) {
                        ((BlockCrops)Block.blocksList[id]).fertilize(world, x, y, z);
                     }

                     return true;
                  }
               } else if(id == Block.cocoaPlant.blockID) {
                  if(!world.isRemote) {
                     world.setBlock(x, y, z, 8 | BlockDirectional.getDirection(world.getBlockMetadata(x, y, z)));
                  }

                  return true;
               } else if(id != Block.grass.blockID) {
                  return false;
               } else {
                  if(!world.isRemote) {
                     label91:
                     for(int i = 0; i < 128; ++i) {
                        int x1 = x;
                        int x2 = y + 1;
                        int x3 = z;

                        for(int j = 0; j < i / 16; ++j) {
                           x1 += itemRand.nextInt(3) - 1;
                           x2 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                           x3 += itemRand.nextInt(3) - 1;
                           if(world.getBlockId(x1, x2 - 1, x3) != Block.grass.blockID || world.isBlockNormalCube(x1, x2, x3)) {
                              continue label91;
                           }
                        }

                        if(world.getBlockId(x1, x2, x3) == 0) {
                           if(itemRand.nextInt(10) != 0) {
                              if(Block.tallGrass.canBlockStay(world, x1, x2, x3)) {
                                 world.setBlockMetadataWithNotify(x1, x2, x3, Block.tallGrass.blockID, 1);
                              }
                           } else {
                              ForgeHooks.plantGrass(world, x1, x2, x3);
                           }
                        }
                     }
                  }

                  return true;
               }
            } else if(world.getBlockMetadata(x, y, z) == 7) {
               return false;
            } else {
               if(!world.isRemote) {
                  ((BlockStem)Block.blocksList[id]).fertilizeStem(world, x, y, z);
               }

               return true;
            }
         } else {
            if(!world.isRemote && ((BlockMushroom)Block.blocksList[id]).fertilizeMushroom(world, x, y, z, world.rand)) {
               ;
            }

            return true;
         }
      }
   }

   public static int getPlayerStackInstance(EntityPlayer player, Class targetClass) {
      int i = -1;
      ItemStack[] arr$ = player.inventory.mainInventory;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack stack = arr$[i$];
         ++i;
         if(stack != null && targetClass.isInstance(stack.getItem())) {
            return i;
         }
      }

      return -1;
   }

   public static ItemStack getPlayerItemStack(EntityPlayer player, ItemStack stackTarget) {
      ItemStack[] arr$ = player.inventory.mainInventory;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         ItemStack stack = arr$[i$];
         if(stack != null && stack.getItem() == stackTarget.getItem() && stack.getItemDamage() == stackTarget.getItemDamage()) {
            return stack;
         }
      }

      return null;
   }
}
